package com.example.ec_app.config;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.ec_app.constant.AuthConstants;
import com.example.ec_app.infrastructure.repository.TokenRepository;
import com.example.ec_app.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/auth")) {
            // 登録・認証用のパスは対象外
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(AuthConstants.AUTHORIZATION_HEADER);
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(AuthConstants.BEARER)) {
            // 認証前の場合は対象外
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(AuthConstants.JWT_START_INDEX);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            final var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                final UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
