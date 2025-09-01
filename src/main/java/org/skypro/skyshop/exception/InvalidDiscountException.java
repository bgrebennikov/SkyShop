package org.skypro.skyshop.exception;

public class InvalidDiscountException extends IllegalArgumentException{
    public InvalidDiscountException(String message) {
        super(message);
    }
}
