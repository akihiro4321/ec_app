package com.example.ec_app.model.auth;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.ec_app.entity.UserDto;

public class LoginUserDetails implements UserDetails {

    private final UserDto userDto;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(final UserDto userDto) {
        this.userDto = userDto;
        this.authorities = userDto.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role)).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !userDto.isDisabled();
    }

}
