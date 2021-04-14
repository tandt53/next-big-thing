package ui.exception;

public class DriverInitException extends RuntimeException{

    public DriverInitException() {
        super();
    }

    public DriverInitException(String message) {
        super(message);
    }

    public DriverInitException(String message, Throwable cause) {
        super(message, cause);
        cause.printStackTrace();
    }
}
