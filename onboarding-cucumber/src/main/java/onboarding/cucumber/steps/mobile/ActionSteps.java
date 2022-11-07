package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import onboarding.cucumber.steps.TestVariables;

public class ActionSteps {

    @Inject
    private MobilePageObject page;

    @Inject
    private TestVariables vars;

    @When("I click on mobile element {string}")
    public void iClickOn(String element) {
        element = vars.formatVariable(element);
        page.click(element);
    }

    @And("I type {string} on mobile element {string}")
    public void iTypeOn(String text, String element) {
        element = vars.formatVariable(element);
        text = vars.formatVariable(text);
        page.type(text, element);
    }

    @And("I get text from mobile element {string} and set to variable {string}")
    public void iGetTextFromAndSetTo(String element, String varName) {
        element = vars.formatVariable(element);
        String text = page.getText(element);
        vars.setVariable(varName, text);
    }

    @And("I scroll down")
    public void iScrollDownInListView() {
        page.scrollDown();
    }

    @And("I scroll up")
    public void iScrollUpInListView() {
        page.scrollUp();
    }

    @And("I swipe left")
    public void iSwipeLeft() {
        page.swipeLeft();
    }

    @And("I swipe right")
    public void iSwipeRight() {
        page.swipeRight();
    }

    @And("I open deep link {string}")
    public void iOpenDeepLink(String url) {
        page.open(url);
    }

    @And("I switch to native view")
    public void iSwitchToNativeView() {
        page.switchContextToNative();
    }

    @And("I switch to web view")
    public void iSwitchToWebView() {
        page.switchContextToWebView();
    }
}
