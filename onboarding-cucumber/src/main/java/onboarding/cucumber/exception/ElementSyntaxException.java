package onboarding.cucumber.exception;

public class ElementSyntaxException extends RuntimeException{

    public ElementSyntaxException() {
        super();
    }

    public ElementSyntaxException(String message) {
        super(message);
    }

    public ElementSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementSyntaxException(Throwable cause) {
        super(cause);
    }

    protected ElementSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
