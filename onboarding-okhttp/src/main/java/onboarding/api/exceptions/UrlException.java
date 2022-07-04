package onboarding.api.exceptions;

public class UrlException extends Exception{

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
