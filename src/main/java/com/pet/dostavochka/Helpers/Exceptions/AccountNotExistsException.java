package com.pet.dostavochka.Helpers.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountNotExistsException extends RuntimeException {

    public AccountNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotExistsException(String message) {
        super(message);
    }

    public AccountNotExistsException(Throwable cause) {
        super(cause);
    }
}
