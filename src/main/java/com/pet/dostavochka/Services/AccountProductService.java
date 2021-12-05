package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.AccountProduct;
import com.pet.dostavochka.Repository.AccountProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountProductService {
    @Autowired
    AccountProductRepository accountProductRepository;

    public AccountProduct createOrder(AccountProduct order) {
        accountProductRepository.save(order);
        return order;
    }

    public List<AccountProduct> getAccountProducts(Account account) {
        return accountProductRepository.findAccountProductsByAccount(account);
    }
}
