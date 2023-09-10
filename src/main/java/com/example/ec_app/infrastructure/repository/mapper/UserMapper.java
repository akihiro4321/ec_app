package com.example.ec_app.infrastructure.repository.mapper;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.example.ec_app.entity.UserDto;

@Mapper
public interface UserMapper {

    public Optional<UserDto> findByEmail(String email);

    public void save(UserDto newUser);
}
