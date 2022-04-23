package light.cucumber.test.vars;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Given;

@ScenarioScoped
public class SetVarsSteps {

    @Inject
    private TestVariable vars;

    @Given("I set variable value is {string}")
    public void iSetVarsValueIs(String arg0) {
        vars.setVar(arg0);
    }


}
