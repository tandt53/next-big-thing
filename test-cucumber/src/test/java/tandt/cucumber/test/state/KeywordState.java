package tandt.cucumber.test.state;

import io.cucumber.guice.ScenarioScoped;

@ScenarioScoped
public class KeywordState {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
