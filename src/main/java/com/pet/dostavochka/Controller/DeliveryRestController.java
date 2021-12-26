package com.pet.dostavochka.Controller;

import com.pet.dostavochka.DTO.AccountProductDTO;
import com.pet.dostavochka.DTO.OrderDTO;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Services.AccountService;
import com.pet.dostavochka.Services.CartSevice;
import com.pet.dostavochka.Services.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/delivery")
public class DeliveryRestController {
    @Autowired
    CartSevice cartSevice;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    AccountService accountService;

    @PostMapping("create")
    public ResponseEntity createDelivery(RequestEntity<OrderDTO> order) {
        Delivery delivery = deliveryService.create(order.getBody().ToDelivery());
        Long accountId = Long.parseLong(order.getBody().getAccountId());
        Account account = accountService.findById(accountId);
        List<Cart> accountCartItems = cartSevice.getAccountProducts(account);
        accountCartItems.forEach(cart -> {
            cart.setDelivery(delivery);
        });
        cartSevice.updateAllCartItems(accountCartItems);
        log.info("Post request : /api/v1/delivery/create");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
