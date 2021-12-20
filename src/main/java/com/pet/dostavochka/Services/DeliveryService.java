package com.pet.dostavochka.Services;

import com.pet.dostavochka.DTO.OrderDTO;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Repository.CartRepository;
import com.pet.dostavochka.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    CartRepository cartRepository;

    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public void acceptOrder(Long orderId) {
        Delivery order = deliveryRepository.getById(orderId);
        cartRepository.deleteAllByDelivery(order);
        // send email
        deliveryRepository.deleteById(orderId);
    }

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }
}
