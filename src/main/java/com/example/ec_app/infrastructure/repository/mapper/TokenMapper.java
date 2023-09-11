package com.example.ec_app.infrastructure.repository.mapper;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.ec_app.entity.TokenDto;

@Mapper
public interface TokenMapper {

    public void save(final TokenDto token);

    Optional<TokenDto> findByToken(@Param("token") String token);
}
