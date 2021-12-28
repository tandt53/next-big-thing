package tandt.cucumber.exceptions;

public class ScenarioScopeModelException extends RuntimeException {

    public ScenarioScopeModelException() {
    }

    public ScenarioScopeModelException(String message) {
        super(message);
    }

    public ScenarioScopeModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScenarioScopeModelException(Throwable cause) {
        super(cause);
    }
}
