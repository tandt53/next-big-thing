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

    @And("I should see mobile element {string} displayed")
    public void iShouldSeeDisplayed(String element) {
        page.isDisplayed(vars.formatVariable(element));
    }

    @And("I should see mobile element {string} displaying {string}")
    public void iShouldSeeDisplaying(String element, String text) {
        text = vars.formatVariable(text);
        Assert.assertEquals(text, page.getText(element));
    }

    @And("I should see mobile element {string} contains {string}")
    public void iShouldSeeContains(String element, String text) {
        element = vars.formatVariable(element);
        text = vars.formatVariable(text);
        Assert.assertTrue(page.getText(element).contains(text));
    }
}
