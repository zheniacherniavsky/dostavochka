package com.pet.dostavochka.Controller;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Services.CartSevice;
import com.pet.dostavochka.Services.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/admin")
public class AdminRestController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    CartSevice cartSevice;

    @GetMapping(value = "orders")
    public ResponseEntity<List[]> getCart() {
        List<Delivery> orders = deliveryService.getAll();
        List<Cart> carts = cartSevice.getCartItemsByDeliveryIds(orders);
        Map<List<Delivery>,List<Cart>> map = new HashMap();
        List[] result = new ArrayList[2];
        result[0] = orders;
        result[1] = carts;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "acceptOrder")
    public ResponseEntity acceptOrder(@RequestParam Map<String, String> mapParam) {
        Long orderId = Long.parseLong(mapParam.get("orderId"));
        deliveryService.acceptOrder(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}