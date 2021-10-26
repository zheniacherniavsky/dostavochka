package com.pet.dostavochka.Helpers.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
