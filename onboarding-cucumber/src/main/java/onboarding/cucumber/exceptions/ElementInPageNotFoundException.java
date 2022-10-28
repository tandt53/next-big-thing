package onboarding.cucumber.exceptions;

public class ElementInPageNotFoundException extends RuntimeException{
    public ElementInPageNotFoundException() {
        super();
    }

    public ElementInPageNotFoundException(String message) {
        super(message);
    }

    public ElementInPageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementInPageNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ElementInPageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
