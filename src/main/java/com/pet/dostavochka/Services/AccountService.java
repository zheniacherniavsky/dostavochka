package com.pet.dostavochka.Services;

import com.pet.dostavochka.Helpers.Exceptions.AccountAlreadyExistsException;
import com.pet.dostavochka.Helpers.Exceptions.AllFieldsRequiredException;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Value("${account.errorMessages.allFieldsRequired}")
    String allFieldsRequired;
    @Value("${account.errorMessages.invalidData}")
    String invalidData;
    @Value("${account.errorMessages.accountAlreadyExists}")
    String accountAlreadyExists;

    @Autowired
    AccountRepository accountRepository;

    public void signup(String login, String password) throws AccountAlreadyExistsException, AllFieldsRequiredException {
        if(login != null && login.length() > 0 && password != null && password.length() > 0) {
            Account account = accountRepository.findAccountByLogin(login);
            if(account == null) {
                Account newAccount = new Account(login, password);
                accountRepository.save(newAccount);
            } else throw new AccountAlreadyExistsException(accountAlreadyExists);
        } else throw new AllFieldsRequiredException(allFieldsRequired);
    }
}
