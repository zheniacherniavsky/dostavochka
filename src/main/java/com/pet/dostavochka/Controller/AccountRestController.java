package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountRestController {
    @Autowired
    AccountService accountService;
}