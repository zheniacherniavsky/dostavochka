package com.pet.dostavochka.Controller;

import com.pet.dostavochka.DTO.AccountProductDTO;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Services.CartSevice;
import com.pet.dostavochka.Services.AccountService;
import com.pet.dostavochka.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/cart")
public class CartRestController {
    @Autowired
    CartSevice cartSevice;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;

    @PostMapping("add")
    public ResponseEntity addToCart(RequestEntity<AccountProductDTO> accountProductDTO) {
        Account account = accountService.findById(accountProductDTO.getBody().getAccountId());
        Product product = productService.findById(accountProductDTO.getBody().getProductId());
        Cart order = new Cart(accountProductDTO.getBody().getQuantity(), account, product);
        cartSevice.createCartOrder(order);
        log.info("Post request : /api/v1/cart/add");
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cart>> getCart(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        List<Cart> cart = cartSevice.getAccountProducts(account);
        log.info("Get request : /api/v1/cart/cart");
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PatchMapping(value = "updateQuantity")
    public ResponseEntity updateCartProductQuantity(@RequestParam Map<String, String> mapParam) {
        Long cartOrderId = Long.parseLong(mapParam.get("cartOrderId"));
        int quantity = Integer.parseInt(mapParam.get("quantity"));
        cartSevice.changeCartProductQuantity(cartOrderId, quantity);
        log.info("Patch request : /api/v1/cart/updateQuantity");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity deleteCartProductOrder(@RequestParam Map<String, String> mapParam) {
        Long cartOrderId = Long.parseLong(mapParam.get("cartOrderId"));
        cartSevice.removeCartProductOrder(cartOrderId);
        log.info("Delete request : /api/v1/cart/delete");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
