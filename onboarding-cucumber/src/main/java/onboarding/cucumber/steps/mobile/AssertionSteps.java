package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import onboarding.cucumber.steps.TestVariables;
import org.junit.Assert;

public class AssertionSteps {
    @Inject
    private MobilePageObject page;

    @Inject
    private TestVariables vars;

    @And("I should see {string} displayed")
    public void iShouldSeeDisplayed(String element) {
        page.isDisplayed(element);
    }

    @And("I should see {string} displaying {string}")
    public void iShouldSeeDisplaying(String element, String text) {
        text = vars.formatVariable(text);
        Assert.assertEquals(page.getText(element), text);
    }


    @And("I should see {string} contains {string}")
    public void iShouldSeeContains(String element, String text) {
        text = vars.formatVariable(text);
        Assert.assertTrue(page.getText(element).contains(text));
    }
}