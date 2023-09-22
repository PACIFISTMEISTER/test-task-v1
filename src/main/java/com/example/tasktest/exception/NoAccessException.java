package com.example.tasktest.exception;

public class NoAccessException extends Exception {
    private NoAccessException(String message) {
        super(message);
    }

    public static NoAccessException createExceptionByToken(String token) {
        return new NoAccessException("invalid token " + token);
    }
}
