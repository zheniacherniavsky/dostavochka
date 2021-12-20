package com.pet.dostavochka.Repository;

import com.pet.dostavochka.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    void deleteById(Long id);
    Delivery getById(Long id);
}
