package tandt.cucumber;


import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import tandt.common.Log;


public class TestListener implements ConcurrentEventListener {
    public Log log = new Log(TestListener.class);

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::handleTestRunStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::handleTestRunFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);

    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            final PickleStepTestStep ev = (PickleStepTestStep) event.getTestStep();
            String testDescription = ev.getStep().getText();
            log.info("STEP: " + testDescription);
        }
    }

    private void handleTestRunStarted(TestRunStarted event) {


    }

    private void handleTestRunFinished(TestRunFinished event) {

    }

}
