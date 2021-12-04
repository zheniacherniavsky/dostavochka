package com.pet.dostavochka.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AccountProduct")
public class AccountProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "quantity")
    private int quantity;

    public AccountProduct(int quantity, Account account, Product product) {
        this.quantity = quantity;
        this.account = account;
        this.product = product;
    }
}
