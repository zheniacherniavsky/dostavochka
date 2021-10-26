package com.pet.dostavochka.Helpers.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AllFieldsRequiredException extends RuntimeException {

    public AllFieldsRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllFieldsRequiredException(String message) {
        super(message);
    }

    public AllFieldsRequiredException(Throwable cause) {
        super(cause);
    }
}
