package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import onboarding.cucumber.steps.TestVariables;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.gesture.JsGesture;
import onboarding.mobile.page.BasePage;
import onboarding.ui.element.Element;

import static onboarding.cucumber.steps.mobile.MobileElementManager.get;

public class MobilePageObject extends BasePage {

    private AppiumDriver driver;

    @Inject
    private JsGesture gesture;

    @Inject
    public MobilePageObject(Injector injector) {
        driver = injector.getInstance(DriverManager.class).getDriver();
    }

    public void click(String element) {
        Element e = get(element, driver);
        e.click();
    }

    public void type(String text, String element) {
        Element e = get(element, driver);
        e.clearText();
        e.setText(text);
    }

    public boolean isDisplayed(String element) {
        return get(element, driver).isDisplayed();
    }

    public String getText(String element) {
        return get(element, driver).getText();
    }

    public void scrollDown() {
        gesture.scrollDown();
    }

    public void scrollUp() {
        gesture.scrollUp();
    }

    public void swipeLeft() {
        gesture.swipeLeft();
    }

    public void swipeRight() {
        gesture.swipeRight();
    }


    public void open(String url) {
        driver.get(url);
    }

}
