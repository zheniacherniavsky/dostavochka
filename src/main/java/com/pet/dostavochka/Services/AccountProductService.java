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

    public AccountProduct createCartOrder(AccountProduct cartOrder) {
        accountProductRepository.save(cartOrder);
        return cartOrder;
    }

    public List<AccountProduct> getAccountProducts(Account account) {
        return accountProductRepository.findAccountProductsByAccount(account);
    }

    public boolean changeCartProductQuantity(Long cartOrderId, int quantity) {
        AccountProduct cartOrder = accountProductRepository.findAccountProductById(cartOrderId);
        cartOrder.setQuantity(quantity);
        accountProductRepository.save(cartOrder);
        return true;
    }

    public boolean removeCartProductOrder(Long cartOrderId) {
        accountProductRepository.deleteById(cartOrderId);
        return true;
    }
}
