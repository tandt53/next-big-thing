package light.ui.exception;

public class ElementActionException extends RuntimeException{

    public ElementActionException() {
    }

    public ElementActionException(String message) {
        super(message);
    }

    public ElementActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
