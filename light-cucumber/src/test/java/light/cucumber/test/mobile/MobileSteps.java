package light.cucumber.test.mobile;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import light.cucumber.test.mobile.bloc.HomeBloc;
import org.junit.Assert;

import javax.inject.Singleton;

@Singleton
public class MobileSteps {

    @Inject
    private HomePage homePage;

    @Inject
    HomeBloc homeBloc;

    @Given("I open application")
    public void iOpenApplication() {
    }

    @And("I login with email {string} and password {string}")
    public void iLoginWithEmailAndPassword(String arg0, String arg1) {
        System.out.println("I login with email " + arg0 + " and password " + arg1);
        homePage.login(arg0, arg1);
    }

    @Then("I verify message {string}")
    public void iVerifyMessage(String arg0) {
        Assert.assertEquals(arg0, homePage.getMessage());
    }
}
