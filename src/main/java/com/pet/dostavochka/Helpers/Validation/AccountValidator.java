package com.pet.dostavochka.Helpers.Validation;

import com.pet.dostavochka.DTO.AuthAccountDTO;
import com.pet.dostavochka.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthAccountDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuthAccountDTO account = (AuthAccountDTO) o;

        if(accountService.findByLogin(account.getLogin()) != null) {
            errors.rejectValue("login", "", "This login is already in use");
        }
    }
}
