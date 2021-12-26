package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/buyer")
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @GetMapping(value = "info")
    public ResponseEntity<Account> getAccountInfo(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        account.setPassword(null);
        log.info("Get request : /api/v1/buyer/info");
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        Boolean isAdmin = "ROLE_ADMIN".equals(account.getRole().getName());
        log.info("Get request : /api/v1/buyer/isAdmin");
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }
}