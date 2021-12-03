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

    // @GetMapping(value = {"/signup"})
    // public ModelAndView signUpPage(Model model) {
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("signup");
    //     AccountForm accountForm = new AccountForm();
    //     model.addAttribute("accountForm", accountForm);
    //     return modelAndView;
    // }

    // @PostMapping(value = {"/signup"})
    // public ModelAndView signUp(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
    //     ModelAndView modelAndView = new ModelAndView();
    //     String login = accountForm.getLogin();
    //     String password = accountForm.getPassword();
    //     try {
    //         accountService.signup(login, password);
    //         modelAndView.setViewName("index");
    //     } catch (AccountAlreadyExistsException | AllFieldsRequiredException exception) {
    //         modelAndView.setViewName("signup");
    //         String errorMessage = exception.getMessage();
    //         log.info(errorMessage);
    //         model.addAttribute("errorMessage", errorMessage);
    //     }
    //     return modelAndView;
    // }

    // @GetMapping(value = {"/signin"})
    //     public ModelAndView signInPage(Model model) {
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("signin");
    //     AccountForm accountForm = new AccountForm();
    //     model.addAttribute("accountForm", accountForm);
    //     return modelAndView;
    // }

    // @PostMapping(value = {"/signin"})
    // public ModelAndView signIn(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
    //     ModelAndView modelAndView = new ModelAndView();
    //     String login = accountForm.getLogin();
    //     String password = accountForm.getPassword();
    //     try {
    //         accountService.signin(login, password);
    //         modelAndView.setViewName("index");
    //     } catch (AccountNotExistsException | AllFieldsRequiredException exception) {
    //         modelAndView.setViewName("signin");
    //         String errorMessage = exception.getMessage();
    //         log.info(errorMessage);
    //         model.addAttribute("errorMessage", errorMessage);
    //     }
    //     return modelAndView;
    // }
}