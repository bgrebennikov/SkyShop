package org.skypro.skyshop.exception;

public class InvalidProductPriceException extends IllegalArgumentException {
    public InvalidProductPriceException(String message) {
        super(message);
    }
}
