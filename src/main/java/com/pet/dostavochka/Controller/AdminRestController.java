package com.pet.dostavochka.Controller;

import com.pet.dostavochka.DTO.AuthAccountDTO;
import com.pet.dostavochka.DTO.ProductDTO;
import com.pet.dostavochka.Helpers.Exceptions.AccountValidationException;
import com.pet.dostavochka.Helpers.Exceptions.ProductValidationException;
import com.pet.dostavochka.Helpers.Validation.ProductValidator;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Services.CartSevice;
import com.pet.dostavochka.Services.DeliveryService;
import com.pet.dostavochka.Services.MailService;
import com.pet.dostavochka.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminRestController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    CartSevice cartSevice;
    @Autowired
    ProductService productService;
    @Autowired
    ProductValidator productValidator;

    @GetMapping(value = "orders")
    public ResponseEntity<List[]> getCart() {
        List<Delivery> orders = deliveryService.getAll();
        List<Cart> carts = cartSevice.getCartItemsByDeliveryIds(orders);
        Map<List<Delivery>,List<Cart>> map = new HashMap();
        List[] result = new ArrayList[2];
        result[0] = orders;
        result[1] = carts;
        log.info("Get request : /api/v1/admin/orders");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "acceptOrder")
    public ResponseEntity acceptOrder(@RequestParam Map<String, String> mapParam) throws MessagingException {
        Long orderId = Long.parseLong(mapParam.get("orderId"));
        deliveryService.acceptOrder(orderId);
        log.info("Patch request : /api/v1/admin/acceptOrder");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDTO productDetails, BindingResult errors)
    {

        productValidator.validate(productDetails, errors);

        if(errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }

        Product product = productDetails.toProduct();

        productService.create(product);
        log.info("Post request : /api/v1/admin/addProduct");
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}