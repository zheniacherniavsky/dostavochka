package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account findByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }

    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if(account != null) {
            if(passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    public Account findById(Long id) { return accountRepository.findAccountById(id); }
}
