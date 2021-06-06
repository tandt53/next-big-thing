package tandt.common.event;


import tandt.common.event.type.EventType;

import java.util.Optional;

public class LoggerEvent extends Event {
    private String message;
    private Optional<Exception> exception;

    public LoggerEvent(EventType eventType, EventDispatcher<?> source, String message, Exception exception) {
        super(eventType, source, message, exception);
        this.message = message;
        this.exception = Optional.of(exception);
    }

    public LoggerEvent(EventType eventType, EventDispatcher<?> source, String message) {
        super(eventType, source, message);
        this.message = message;
        this.exception = Optional.empty();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Optional<Exception> getException() {
        return exception;
    }

    public void setException(Optional<Exception> exception) {
        this.exception = exception;
    }
}

