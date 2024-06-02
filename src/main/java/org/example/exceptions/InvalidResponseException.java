package org.example.exceptions;

public class InvalidResponseException extends RuntimeException{
    public InvalidResponseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
