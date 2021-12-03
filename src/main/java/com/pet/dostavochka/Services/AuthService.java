package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AccountRepository accountRepository;

    public Account signup(Account account) {

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(userRoles);
//        user.setStatus(Status.ACTIVE);
//        user.setCreated(new Date());
//        user.setUpdated(new Date());
        accountRepository.save(account);
        return account;
    }
}
