package org.skypro.skyshop.exception;

public class InvalidProductTitleException extends IllegalArgumentException {

    public InvalidProductTitleException(String message) {
        super(message);
    }

}