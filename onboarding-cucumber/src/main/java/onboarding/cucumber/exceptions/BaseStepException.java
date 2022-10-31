package onboarding.cucumber.exceptions;

public class BaseStepException extends RuntimeException{

    public BaseStepException() {
    }

    public BaseStepException(String message) {
        super(message);
    }

    public BaseStepException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseStepException(Throwable cause) {
        super(cause);
    }

    public BaseStepException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
