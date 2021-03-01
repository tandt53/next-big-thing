package tandt.common.event.example;


import tandt.common.event.EventDispatcher;
import tandt.common.event.LoggerEvent;
import tandt.common.event.type.EventType;
import tandt.common.event.type.LoggerEventType;

public class MyCustomLogger extends EventDispatcher<LoggerEvent> {
    public void error(String message) {
        System.err.println("ERROR: " + message);
        dispatchEvent(LoggerEventType.ERROR, message);
    }

    public void error(String message, Exception e) {
        System.err.println("ERROR: " + message);
        dispatchEvent(LoggerEventType.ERROR, message, e);
    }

    public void warning(String message) {
        System.out.println("WARN: " + message);
        dispatchEvent(LoggerEventType.WARNING, message);
    }

    public void info(String message) {
        System.out.println("INFO: " + message);
        dispatchEvent(LoggerEventType.INFO, message);
    }

    @Override
    protected LoggerEvent buildEvent(EventType eventType, Object... eventData) {
        LoggerEvent loggerEvent;
        if (LoggerEventType.ERROR.equals(eventType) && eventData.length > 1) {
            loggerEvent = new LoggerEvent(eventType, this, (String) eventData[0], (Exception) eventData[1]);
        } else {
            loggerEvent = new LoggerEvent(eventType, this, (String) eventData[0]);
        }

        return loggerEvent;
    }
}
