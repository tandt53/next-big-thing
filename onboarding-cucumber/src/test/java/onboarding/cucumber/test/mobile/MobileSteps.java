package onboarding.cucumber.test.mobile;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

@ScenarioScoped
public class MobileSteps {

    @Inject
    private HomePage homePage;

    @Given("I open application")
    public void iOpenApplication() {
    }

    @And("I login with email {string} and password {string}")
    public void iLoginWithEmailAndPassword(String arg0, String arg1) {
        homePage.login(arg0, arg1);
    }

    @Then("I verify message {string}")
    public void iVerifyMessage(String arg0) {
        Assert.assertEquals(arg0, homePage.getMessage());
    }
}
