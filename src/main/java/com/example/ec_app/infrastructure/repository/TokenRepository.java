package com.example.ec_app.infrastructure.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.ec_app.entity.TokenDto;
import com.example.ec_app.infrastructure.repository.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRepository {

    private final TokenMapper tokenMapper;

    public void save(final TokenDto token) {
        tokenMapper.save(token);
    }

    public Optional<TokenDto> findByToken(final String token) {
        return tokenMapper.findByToken(token);
    };
}
