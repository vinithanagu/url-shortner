package com.example.url.shortner.exceptions;

public class UrlNotFoundException extends RuntimeException{
  public UrlNotFoundException(final String message) { super(message); }
}
