package light.guice.exception;

public class GuiceScannerException extends RuntimeException{
    public GuiceScannerException() {
    }

    public GuiceScannerException(String message) {
        super(message);
    }

    public GuiceScannerException(String message, Throwable cause) {
        super(message, cause);
    }
}
