package onboarding.cucumber.steps.mobile;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.ui.element.Element;

import static onboarding.cucumber.steps.mobile.MobileElementManager.get;

public class MobilePageObject {

    private AppiumDriver driver;

    @Inject
    public MobilePageObject(Injector injector) {
        driver = injector.getInstance(DriverManager.class).getDriver();
    }

    public void click(String element) {
        Element e = get(element, driver);
        e.click();
    }

    public void type(String text, String element) {
//        if (VariableUtils.isVariable(text)) {
//            value = testVariables.getVariable(text).toString();

        Element e = get(element, driver);

        e.clearText();
        e.setText(text);
    }
}
