package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.AccountProduct;
import com.pet.dostavochka.Repository.AccountProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountProductService {
    @Autowired
    AccountProductRepository accountProductRepository;

    public AccountProduct createOrder(AccountProduct order) {
        accountProductRepository.save(order);
        return order;
    }
}
