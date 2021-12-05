package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartSevice {
    @Autowired
    CartRepository cartRepository;

    public Cart createCartOrder(Cart cartOrder) {
        cartRepository.save(cartOrder);
        return cartOrder;
    }

    public List<Cart> updateAllCartItems(List<Cart> items) {
        return cartRepository.saveAll(items);
    }

    public List<Cart> getAccountProducts(Account account) {
        return cartRepository.findAccountProductsByAccountAndDeliveryIsNull(account);
    }

    public List<Cart> getCartItemsByDeliveryIds(List<Delivery> deliveries) {
        return cartRepository.findAllByDeliveryIn(deliveries);
    }

    public boolean changeCartProductQuantity(Long cartOrderId, int quantity) {
        Cart cartOrder = cartRepository.findAccountProductById(cartOrderId);
        cartOrder.setQuantity(quantity);
        cartRepository.save(cartOrder);
        return true;
    }

    public boolean removeCartProductOrder(Long cartOrderId) {
        cartRepository.deleteById(cartOrderId);
        return true;
    }
}
