package com.example.ec_app.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean disabled;
    private List<String> roleList;
}
