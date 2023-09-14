package com.example.ec_app.service.auth;

import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ec_app.constant.TokenType;
import com.example.ec_app.entity.TokenDto;
import com.example.ec_app.entity.UserDto;
import com.example.ec_app.infrastructure.repository.TokenRepository;
import com.example.ec_app.infrastructure.repository.UserRepository;
import com.example.ec_app.payload.request.auth.AuthenticationRequest;
import com.example.ec_app.payload.request.auth.RegisterRequest;
import com.example.ec_app.payload.response.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;

	@Transactional
	public AuthenticationResponse register(final RegisterRequest request) {
		final var user = UserDto.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roleList(List.of("ROLE_GENERAL"))
				.build();
		userRepository.save(user);

		final var jwtToken = jwtService.generateToken(user);
		final var refreshToken = jwtService.generateRefreshToken(user);
		saveNewToken(user, jwtToken);

		return AuthenticationResponse.builder().accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();
	}

	public AuthenticationResponse authenticate(final AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()));
		final UserDto user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		final String jwtToken = jwtService.generateToken(user);
		final String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);// ユーザーに紐づく他の有効なトークンは無効化(expire, revoke)
		saveNewToken(user, jwtToken);// 新しいトークンを登録
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();
	}

	public boolean existsEmail(final String email) {
		return userRepository.existsEmail(email);
	}


	private void saveNewToken(final UserDto user, final String jwtToken) {
		final var token = TokenDto.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(final UserDto user) {
		final var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
		if (validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	};
}
