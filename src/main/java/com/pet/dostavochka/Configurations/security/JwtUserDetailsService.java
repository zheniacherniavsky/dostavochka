package com.pet.dostavochka.Configurations.security;

import com.pet.dostavochka.Configurations.security.jwt.JwtAccount;
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
    public JwtAccount loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(login);
        return JwtAccount.fromAccountToJwtAccount(account);
    }
}
