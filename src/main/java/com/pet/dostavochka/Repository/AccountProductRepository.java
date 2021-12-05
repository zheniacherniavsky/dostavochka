package com.pet.dostavochka.Repository;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.AccountProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountProductRepository extends JpaRepository<AccountProduct, Long> {
    List<AccountProduct> findAccountProductsByAccount(Account account);
}
