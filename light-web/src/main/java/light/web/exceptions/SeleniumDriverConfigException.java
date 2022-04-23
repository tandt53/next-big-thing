package light.web.exceptions;

public class SeleniumDriverConfigException extends RuntimeException{

    public SeleniumDriverConfigException() {
    }

    public SeleniumDriverConfigException(String message) {
        super(message);
    }

    public SeleniumDriverConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeleniumDriverConfigException(Throwable cause) {
        super(cause);
    }
}
