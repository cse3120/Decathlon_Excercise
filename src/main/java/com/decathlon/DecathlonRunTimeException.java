package com.decathlon;

public class DecathlonRunTimeException extends RuntimeException{

    public DecathlonRunTimeException(String message) {
        super(message);
    }

    public DecathlonRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
