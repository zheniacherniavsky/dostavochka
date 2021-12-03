package com.pet.dostavochka.Configurations.security;

import com.pet.dostavochka.Configurations.security.jwt.JwtAccount;
import com.pet.dostavochka.Configurations.security.jwt.JwtAccountFactory;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(login);

        if(account == null) {
            throw new UsernameNotFoundException("User with login: " + login + " not found!");
        }

        return JwtAccountFactory.create(account);
    }
}
