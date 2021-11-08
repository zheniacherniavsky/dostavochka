package com.pet.dostavochka.Services;

import com.pet.dostavochka.Helpers.Exceptions.AccountAlreadyExistsException;
import com.pet.dostavochka.Helpers.Exceptions.AccountNotExistsException;
import com.pet.dostavochka.Helpers.Exceptions.AllFieldsRequiredException;
import com.pet.dostavochka.Helpers.Validation.Validation;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Value("${account.errorMessages.allFieldsRequired}")
    String allFieldsRequired;
    @Value("${account.errorMessages.invalidData}")
    String invalidData;
    @Value("${account.errorMessages.accountAlreadyExists}")
    String accountAlreadyExists;
    @Value("${account.errorMessages.accountNotExists}")
    String accountNotExists;


    @Autowired
    AccountRepository accountRepository;

    public void signup(String login, String password) throws AccountAlreadyExistsException, AllFieldsRequiredException
    {
        if(Validation.validateFieldsForExisting(List.of(login, password))) {
            Account account = accountRepository.findAccountByLogin(login);
            if(account == null) {
                Account newAccount = new Account(login, password);
                accountRepository.save(newAccount);
            } else throw new AccountAlreadyExistsException(accountAlreadyExists);
        } else throw new AllFieldsRequiredException(allFieldsRequired);
    }

    public boolean signin(String login, String password) throws AccountAlreadyExistsException, AllFieldsRequiredException
    {
        if(Validation.validateFieldsForExisting(List.of(login, password))) {
            Account account = accountRepository.findAccountByLogin(login);
            if(account != null) {
                if(account.getPassword().equals(password)) {
                    return true;
                } else throw new AccountNotExistsException(accountNotExists);
            } else throw new AccountNotExistsException(accountNotExists);
        } else throw  new AllFieldsRequiredException(allFieldsRequired);
    }
}
