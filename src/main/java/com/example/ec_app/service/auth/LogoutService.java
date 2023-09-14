package com.example.ec_app.service.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import com.example.ec_app.constant.AuthConstants;
import com.example.ec_app.infrastructure.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication) {
        final String authHeader = request.getHeader(AuthConstants.AUTHORIZATION_HEADER);
        final String jwt;
        if (authHeader == null || !authHeader.startsWith(AuthConstants.BEARER)) {
            return;
        }
        jwt = authHeader.substring(AuthConstants.JWT_START_INDEX);
        final var storedToken = tokenRepository.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }


}
