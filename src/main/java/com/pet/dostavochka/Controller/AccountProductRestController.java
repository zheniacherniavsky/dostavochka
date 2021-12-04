package com.pet.dostavochka.Controller;

import com.pet.dostavochka.DTO.AccountProductDTO;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.AccountProduct;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Services.AccountProductService;
import com.pet.dostavochka.Services.AccountService;
import com.pet.dostavochka.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/order")
public class AccountProductRestController {
    @Autowired
    AccountProductService accountProductService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;

    @PostMapping("add")
    public ResponseEntity addToCart(RequestEntity<AccountProductDTO> accountProductDTO) {
        Account account = accountService.findById(accountProductDTO.getBody().getAccountId());
        Product product = productService.findById(accountProductDTO.getBody().getProductId());
        AccountProduct order = new AccountProduct(accountProductDTO.getBody().getQuantity(), account, product);
        accountProductService.createOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
