package com.example.ec_app.infrastructure.repository;

import java.util.List;
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
        if (token.getId() != null) {
            tokenMapper.updateToken(token);
        } else {
            tokenMapper.insertToken(token);
        }
    }

    public Optional<TokenDto> findByToken(final String token) {
        return tokenMapper.findByToken(token);
    };

    public List<TokenDto> findAllValidTokenByUser(final Integer id) {
        return tokenMapper.findAllValidTokenByUser(id);
    };

    public void saveAll(final List<TokenDto> tokens) {
        tokens.stream().forEach(token -> {
            tokenMapper.updateToken(token);
        });
    }
}
