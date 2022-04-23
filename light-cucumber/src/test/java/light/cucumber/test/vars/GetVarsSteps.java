package light.cucumber.test.vars;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.When;
import org.junit.Assert;

@ScenarioScoped
public class GetVarsSteps {

    @Inject
    private TestVariable vars;

    @When("I check variable in anther step definition class is equals to {string}")
    public void iCheckVarsInAntherStepDefinitionClassIsEqualsTo(String arg0) {
        Assert.assertEquals(arg0, vars.getVar());

    }


}
