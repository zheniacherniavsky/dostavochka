package com.pet.dostavochka.DTO;

import lombok.Getter;

@Getter
public class AccountProductDTO {

    private int quantity;
    private Long accountId;
    private Long productId;

    public AccountProductDTO(int quantity, Long accountId, Long productId) {
        this.quantity = quantity;
        this.accountId = accountId;
        this.productId = productId;
    }
}
