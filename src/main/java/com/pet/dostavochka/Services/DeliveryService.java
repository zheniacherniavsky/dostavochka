package com.pet.dostavochka.Services;

import com.pet.dostavochka.DTO.OrderDTO;
import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
