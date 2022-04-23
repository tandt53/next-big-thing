package light.restassured.exceptions;

public class JsonCreationException extends RuntimeException{


    public JsonCreationException() {
    }

    public JsonCreationException(String message) {
        super(message);
    }

    public JsonCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
