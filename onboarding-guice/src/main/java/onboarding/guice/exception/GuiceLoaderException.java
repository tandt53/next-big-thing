package onboarding.guice.exception;

public class GuiceLoaderException extends RuntimeException{

    public GuiceLoaderException() {
        super();
    }

    public GuiceLoaderException(String message) {
        super(message);
    }

    public GuiceLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
