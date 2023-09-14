package com.example.ec_app.infrastructure.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.ec_app.entity.UserDto;
import com.example.ec_app.infrastructure.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public Optional<UserDto> findByEmail(final String email) {
        return userMapper.findByEmail(email);
    };

    public void save(final UserDto newUser) {
        userMapper.saveUser(newUser);
        userMapper.saveRoles(newUser.getUserId(), newUser.getRoleList());
    }

    public boolean existsEmail(final String email) {
        return userMapper.existsEmail(email) != 0;
    }
}
