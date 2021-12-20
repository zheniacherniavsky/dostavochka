package com.pet.dostavochka.Helpers.Validation;

import com.pet.dostavochka.DTO.AuthAccountDTO;
import com.pet.dostavochka.DTO.ProductDTO;
import com.pet.dostavochka.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
