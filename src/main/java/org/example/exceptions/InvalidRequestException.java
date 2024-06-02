package org.example.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}

