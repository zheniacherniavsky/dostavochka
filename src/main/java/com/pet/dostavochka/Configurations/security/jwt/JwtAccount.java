package com.pet.dostavochka.Configurations.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtAccount implements UserDetails {

    private final Long id;
    private final String login;
    private final String password;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtAccount(
            Long id,
            String login,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
