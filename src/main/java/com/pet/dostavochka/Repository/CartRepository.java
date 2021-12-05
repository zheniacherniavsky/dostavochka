package com.pet.dostavochka.Repository;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAccountProductsByAccountAndDeliveryIsNull(Account account);

    Cart findAccountProductById(Long id);

    List<Cart> findAllByDeliveryIn(List<Delivery> deliveries);
}
