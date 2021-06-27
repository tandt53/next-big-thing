package tandt.cucumber.test.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import tandt.cucumber.test.DriverHooks;

@Singleton
public class WebSteps extends PageFactory {

    @Inject
    private PageObject pageObject;

    @Inject
    private HomePage homePage;

    @Inject
    private DriverHooks driver;

    @Given("I go to {string}")
    public void iGoTo(String url) {
        pageObject.openPage(url);
    }

    @When("I type and search keyword {string}")
    public void iTypeAndSearchKeyword(String keyword) {
        homePage.search(keyword);
    }

    @Given("I open browser")
    public void iOpenBrowser()  {
        driver.initWeb();
    }


}
