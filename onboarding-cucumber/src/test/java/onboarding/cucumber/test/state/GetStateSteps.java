package onboarding.cucumber.test.state;

import com.google.inject.Inject;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GetStateSteps {

    @Inject
    private KeywordState state;

    @When("I check state in anther step definition class is equals to {string}")
    public void iCheckStateInAntherStepDefinitionClassIsEqualsTo(String arg0) {
        Assert.assertEquals(arg0, state.getState());
    }
}
