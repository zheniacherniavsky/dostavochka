package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Forms.AccountForm;
import com.pet.dostavochka.Helpers.Exceptions.AccountAlreadyExistsException;
import com.pet.dostavochka.Helpers.Exceptions.AccountNotExistsException;
import com.pet.dostavochka.Helpers.Exceptions.AllFieldsRequiredException;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping(value = {"/signup"})
    public ModelAndView signUpPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return modelAndView;
    }

    @PostMapping(value = {"/signup"})
    public ModelAndView signUp(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
        ModelAndView modelAndView = new ModelAndView();
        String login = accountForm.getLogin();
        String password = accountForm.getPassword();
        try {
            accountService.signup(login, password);
            modelAndView.setViewName("index");
        } catch (AccountAlreadyExistsException | AllFieldsRequiredException exception) {
            modelAndView.setViewName("signup");
            model.addAttribute("errorMessage", exception.getMessage());
        }
        return modelAndView;
    }

    @GetMapping(value = {"/signin"})
        public ModelAndView signInPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return modelAndView;
    }

    @PostMapping(value = {"/signin"})
    public ModelAndView signIn(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
        ModelAndView modelAndView = new ModelAndView();
        String login = accountForm.getLogin();
        String password = accountForm.getPassword();
        try {
            accountService.signin(login, password);
            modelAndView.setViewName("index");
        } catch (AccountNotExistsException | AllFieldsRequiredException exception) {
            modelAndView.setViewName("signin");
            model.addAttribute("errorMessage", exception.getMessage());
        }
        return modelAndView;
    }

}