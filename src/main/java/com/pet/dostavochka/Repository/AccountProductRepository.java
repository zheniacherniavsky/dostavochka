package com.pet.dostavochka.Repository;

import com.pet.dostavochka.Model.AccountProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProductRepository extends JpaRepository<AccountProduct, Long> {
}
