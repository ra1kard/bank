package org.example.fileSystem.task6_v2;

public class NotEnoughAmountAccountException extends RuntimeException {

    NotEnoughAmountAccountException() {
        super();
    }

    NotEnoughAmountAccountException(String message) {
        super(message);
    }

}

