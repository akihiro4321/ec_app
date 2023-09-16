package com.example.ec_app.infrastructure.repository.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.ec_app.entity.TokenDto;

@Mapper
public interface TokenMapper {

    public void insertToken(final TokenDto token);

    public void updateToken(final TokenDto token);

    public Optional<TokenDto> findByToken(@Param("token") String token);

    public List<TokenDto> findAllValidTokenByUser(Integer userId);
}
