package com.pet.dostavochka.Repository;

import com.pet.dostavochka.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByLogin(String name);
    Account findAccountById(Long id);
}
