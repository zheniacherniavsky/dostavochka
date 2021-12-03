package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account signup(Account account) {

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }
}
