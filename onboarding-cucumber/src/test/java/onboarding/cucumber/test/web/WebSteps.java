package onboarding.cucumber.test.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onboarding.cucumber.test.DriverHooks;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

public class WebSteps extends PageFactory {


    @Inject
    private HomePage homePage;

    @Inject
    private SearchResultPage searchResultPage;

    @Given("I go to {string}")
    public void iGoTo(String url) {
        homePage.open(url);
    }

    @When("I type and search keyword {string}")
    public void iTypeAndSearchKeyword(String keyword) {
        homePage.search(keyword);
    }

    @Then("I should see the result number")
    public void iShouldSeeTheResultNumber() {
        Assert.assertTrue(searchResultPage.getResult().contains("kết quả"));
    }
}
