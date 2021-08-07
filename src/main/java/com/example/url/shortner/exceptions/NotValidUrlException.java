package com.example.url.shortner.exceptions;

public class NotValidUrlException extends RuntimeException {
    public NotValidUrlException(final String message) {
      super(message);
    }
}
