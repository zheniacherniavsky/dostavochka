package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Forms.AccountForm;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AccountController {
    private static List<Account> accounts = new ArrayList<>();

    @Autowired
    AccountRepository accountRepository;

    @Value("${account.errorMessages.allFieldsRequired}")
    String allFieldsRequired;
    @Value("${account.errorMessages.invalidData}")
    String invalidData;
    @Value("${account.errorMessages.accountAlreadyExists}")
    String accountAlreadyExists;

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
        modelAndView.setViewName("signup");
        String login = accountForm.getLogin();
        String password = accountForm.getPassword();

        if( login != null && login.length() > 0 ) {
            Account account = accountRepository.findAccountByLogin(login);
            if(account == null) {
                Account newAccount = new Account(login, password);
                accountRepository.save(newAccount);
                modelAndView.setViewName("index");
                return modelAndView;
            }
            model.addAttribute("errorMessage", accountAlreadyExists);
            return modelAndView;
        }
        else {
            model.addAttribute("errorMessage", allFieldsRequired);
        }

        return modelAndView;
    }
}
