package com.tandt53.api.exceptions;

public class UrlException extends Throwable{

    public UrlException() {
        super();
    }

    public UrlException(String message) {
        super(message);
    }

    public UrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
