package onboarding.cucumber.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class BasicMobileSteps {
    @Inject
    private MobilePageObject page;

    @When("I click on mobile element {string}")
    public void iClickOn(String element) {
        page.click(element);
    }

    @And("I type {string} on mobile element {string}")
    public void iTypeOn(String text, String element) {
        page.type(text, element);
    }
}
