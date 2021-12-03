package com.pet.dostavochka.Controller;

import com.pet.dostavochka.DTO.AccountDTO;
import com.pet.dostavochka.Helpers.Exceptions.AccountValidationException;
import com.pet.dostavochka.Helpers.Validation.AccountValidator;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Services.AccountService;
import com.pet.dostavochka.Services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/auth")
public class AuthRestController {

    private final AuthService authService;
    private final AccountValidator accountValidator;

    @Autowired
    public AuthRestController
            (AuthService authService,
             AccountValidator accountValidator) {
        this.authService = authService;
        this.accountValidator = accountValidator;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> Register(@Valid @RequestBody AccountDTO accountDetails, BindingResult errors)
    {
        accountValidator.validate(accountDetails, errors);

        if(errors.hasErrors()) {
            throw new AccountValidationException(errors);
        }

        Account account = accountDetails.ToAccount();
        authService.signup(account);
//        log.info("Get request : /api/v1/auth/registerStudent");
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
