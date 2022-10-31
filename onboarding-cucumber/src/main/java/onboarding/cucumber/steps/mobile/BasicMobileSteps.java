package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import onboarding.cucumber.exceptions.BaseStepException;
import onboarding.cucumber.steps.TestVariables;

import java.util.Map;

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

    @And("I scroll down")
    public void iScrollDownInListView() {
        page.scrollDown();
    }

    @And("I scroll up")
    public void iScrollUpInListView() {
        page.scrollUp();
    }

    @And("I set variable with {string} and {string}")
    public void iSetVariableWithAnd(String key, String value) {
        vars.setVariable(key, value);
    }

    @And("I set list variables")
    public void iSetListVariables(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        vars.setVariable(map);

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
