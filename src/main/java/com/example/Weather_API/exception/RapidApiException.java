package com.example.Weather_API.exception;

public class RapidApiException extends RuntimeException {
    public RapidApiException(String message) {
        super(message);
    }

    public RapidApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
