package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account findByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }

    public Account findById(Long id) { return accountRepository.findAccountById(id); }
}
