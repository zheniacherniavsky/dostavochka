package com.pet.dostavochka.Helpers.Validation;

import java.util.List;

public class Validation {
    public static boolean validateFieldsForExisting(List<String> array) {
        boolean result = true;

        for (String field : array) {
            if (!(field != null && field.length() > 0)) {
                result = false;
                break;
            }
        }

        return result;
    }
}
