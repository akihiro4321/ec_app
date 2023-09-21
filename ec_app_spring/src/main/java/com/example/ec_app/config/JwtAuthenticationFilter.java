package com.example.ec_app.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ec_app.constant.AuthConstants;
import com.example.ec_app.infrastructure.repository.TokenRepository;
import com.example.ec_app.model.auth.LoginUserDetails;
import com.example.ec_app.payload.response.error.AuthorizationErrorResponse;
import com.example.ec_app.service.auth.JwtService;
import com.example.ec_app.service.auth.LoginUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final LoginUserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/auth")
                || request.getServletPath().contains("/api/products")) {
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
        try {
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                final LoginUserDetails loginUserDetails = this.userDetailsService.loadUserByUsername(userEmail);
                final var isTokenValid = tokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
                if (jwtService.isTokenValid(jwt, loginUserDetails) && isTokenValid) {
                    final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            loginUserDetails,
                            null,
                            loginUserDetails.getAuthorities());
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (final ExpiredJwtException e) {
            final AuthorizationErrorResponse errorRes = new AuthorizationErrorResponse(request.getRequestURI());
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // final ResponseEntity<ApiErrorResponse> errorEntity = new
            // ResponseEntity<>(errorRes, headers,
            // HttpStatus.UNAUTHORIZED);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            final PrintWriter out = response.getWriter();
            out.println(new ObjectMapper().writeValueAsString(errorRes));
            out.flush();
            return;
        }

        filterChain.doFilter(request, response);
    }
}
