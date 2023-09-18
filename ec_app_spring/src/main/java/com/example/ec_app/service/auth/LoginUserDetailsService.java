package com.example.ec_app.service.auth;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.ec_app.entity.UserDto;
import com.example.ec_app.infrastructure.repository.UserRepository;
import com.example.ec_app.model.auth.LoginUserDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public LoginUserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        final Optional<UserDto> userDto = userRepository.findByEmail(email);
        return userDto.map(user -> new LoginUserDetails(user)).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
    }

}
