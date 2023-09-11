package com.example.ec_app.infrastructure.repository.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.ec_app.entity.UserDto;

@Mapper
public interface UserMapper {

    public Optional<UserDto> findByEmail(String email);

    public Integer saveUser(UserDto newUser);

    public void saveRoles(@Param("userId") int userId,
            @Param("roleList") List<String> roleList);
}
