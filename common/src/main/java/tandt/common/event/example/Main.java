package tandt.common.event.example;


import tandt.common.event.type.LoggerEventType;

public class Main {
    public static void main(String[] argv) {
        MyCustomLogger logger = new MyCustomLogger();
        logger.addEventListener(LoggerEventType.ERROR, new ErrorEmailSender());
        logger.addEventListener(LoggerEventType.INFO, l -> System.out.println("From Warning Listener: " + l.getEventData()[0]));

        logger.info("Only from logger will show");
        logger.warning("A warning message");
        logger.error("An error message", new NullPointerException());
    }
}

