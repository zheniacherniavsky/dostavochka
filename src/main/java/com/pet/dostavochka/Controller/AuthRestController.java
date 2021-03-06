package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Configurations.security.jwt.JwtTokenProvider;
import com.pet.dostavochka.DTO.AuthAccountDTO;
import com.pet.dostavochka.Helpers.Exceptions.AccountAuthException;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthRestController {

    private final AuthService authService;
    private final AccountValidator accountValidator;
    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController
            (AuthService authService,
             AccountValidator accountValidator,
             AccountService accountService,
             JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.accountValidator = accountValidator;
        this.accountService = accountService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> Register(@Valid @RequestBody AuthAccountDTO accountDetails, BindingResult errors)
    {
        accountValidator.validate(accountDetails, errors);

        if(errors.hasErrors()) {
            throw new AccountValidationException(errors);
        }

        Account account = accountDetails.ToAccount();
        authService.signup(account);
        log.info("Post request : /api/v1/auth/signup");
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signin(@RequestBody AuthAccountDTO accountDetails) {
        String login = accountDetails.getLogin();
        String password = accountDetails.getPassword();
        Account account = accountService.findByLoginAndPassword(login, password);
        if(account == null) throw new AccountAuthException("Wrong Credentials!");
        String token = jwtTokenProvider.createToken(login);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("accountId", account.getId());
        response.put("role", account.getRole().getName());
        log.info("Post request : /api/v1/auth/signin");
        return ResponseEntity.ok(response);
    }
}
