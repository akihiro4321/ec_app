package com.example.ec_app.service.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.ec_app.entity.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String generateToken(final UserDto user) {
        return generateToken(new HashMap<>(), user);
    }

    public String generateToken(final Map<String, Object> extraClaims,
            final UserDto user) {
        return buildToken(extraClaims, user, jwtExpiration);
    }

    public String generateRefreshToken(final UserDto user) {
        return buildToken(new HashMap<>(), user, refreshExpiration);
    }

    private String buildToken(final Map<String, Object> extraClaims,
            final UserDto user, final long expiration) {
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
