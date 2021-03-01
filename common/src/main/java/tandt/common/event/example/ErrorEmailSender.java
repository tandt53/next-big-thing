package tandt.common.event.example;


import tandt.common.event.Event;
import tandt.common.event.EventListener;

public class ErrorEmailSender implements EventListener {

    @Override
    public void onEvent(Event event) {
        sendEmail((String) event.getEventData()[0]);
    }

    private void sendEmail(String errorMessage) {
        System.out.println("Sending email with: " + errorMessage);
    }
}

