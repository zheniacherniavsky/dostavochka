package com.pet.dostavochka.Configurations.security.jwt;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Role;
import com.pet.dostavochka.Model.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtAccountFactory {
    public JwtAccountFactory() {}

    public static  JwtAccount create(Account account) {
        return new JwtAccount(
                account.getId(),
                account.getLogin(),
                account.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(account.getRoles())),
                account.getStatus().equals(Status.ACTIVE),
                account.getUpdated());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
