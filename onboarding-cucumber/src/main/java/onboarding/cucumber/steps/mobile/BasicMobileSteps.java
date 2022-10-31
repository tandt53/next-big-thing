package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import onboarding.cucumber.steps.TestVariables;

public class BasicMobileSteps {

    @Inject
    private MobilePageObject page;

    @Inject
    private TestVariables vars;

    @When("I click on mobile element {string}")
    public void iClickOn(String element) {
        page.click(element);
    }

    @And("I type {string} on mobile element {string}")
    public void iTypeOn(String text, String element) {
        text = vars.formatVariable(text);
        page.type(text, element);
    }

    @And("I get text from {string} and set to {string}")
    public void iGetTextFromAndSetTo(String element, String varName) {
        String text = page.getText(element);
        vars.setVariable(varName, text);
    }

    @And("I scroll down in list view")
    public void iScrollDownInListView() {
        page.scrollDown();
    }

    @And("I scroll up in list view")
    public void iScrollUpInListView() {
        page.scrollUp();
    }
}
