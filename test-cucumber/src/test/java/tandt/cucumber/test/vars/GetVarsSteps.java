package tandt.cucumber.test.vars;

import com.google.inject.Inject;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GetVarsSteps {

    @Inject
    TestVariable vars;

    @When("I check variable in anther step definition class is equals to {string}")
    public void iCheckVarsInAntherStepDefinitionClassIsEqualsTo(String arg0) {
        Assert.assertEquals(arg0, vars.getVar());

    }


}
