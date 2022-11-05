package onboarding.cucumber.listener;


import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import onboarding.common.Log;

public class CucumberListener implements ConcurrentEventListener {
    public Log log = new Log(CucumberListener.class);
    private boolean isAnyStepFailed = false;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            Result result = event.getResult();
            if (result.getStatus() != Status.PASSED) {
                isAnyStepFailed = true;
            }
        }

    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep && !isAnyStepFailed) {
            final PickleStepTestStep ev = (PickleStepTestStep) event.getTestStep();
            String testDescription = ev.getStep().getText();
            log.debug("STEP: " + testDescription);
        }
    }

}
