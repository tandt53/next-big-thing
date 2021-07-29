package tandt.dataprovider.exceptions;

public class JsonParserException extends RuntimeException{

    public JsonParserException() {
    }

    public JsonParserException(String message) {
        super(message);
    }

    public JsonParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
