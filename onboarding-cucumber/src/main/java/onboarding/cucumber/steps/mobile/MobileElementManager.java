package onboarding.cucumber.steps.mobile;


import io.appium.java_client.AppiumDriver;
import onboarding.cucumber.exceptions.ElementInPageNotFoundException;
import onboarding.cucumber.exceptions.ElementSyntaxException;
import onboarding.cucumber.steps.PageLoader;
import onboarding.mobile.element.ElementInvocationHandler;
import onboarding.mobile.element.MobileElementInfo;
import onboarding.ui.element.Element;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static onboarding.cucumber.steps.ElementManagerUtils.formatLocatorValueWithArgs;
import static onboarding.cucumber.steps.ElementManagerUtils.getName;

public class MobileElementManager {

    private static Map<String, Map<String, MobileElementInfo>> locators = new HashMap<>();

    public static Element get(String element, AppiumDriver driver) {
        if (element.split("\\.").length != 2) {
            throw new ElementSyntaxException("Element '" + element + "' is not valid.");
        }
        String page = element.substring(0, element.indexOf('.'));
        String elementName = element.substring(element.indexOf('.') + 1);

        Map<String, MobileElementInfo> pageLocators;
        if (locators.containsKey(page)) {
            pageLocators = locators.get(page);
        } else {
            pageLocators = new PageLoader().load(page, driver);
            locators.put(page, pageLocators);
        }

        String elementKey = getName(elementName);
        long getName = System.currentTimeMillis();

        MobileElementInfo loadedInfo = pageLocators.get(elementKey);
        if (loadedInfo == null) {
            throw new ElementInPageNotFoundException("Element " + element + " not found.");
        }
        MobileElementInfo info = new MobileElementInfo(loadedInfo);
        info.setValue(formatLocatorValueWithArgs(info.getValue(), elementKey));
        return (Element) Proxy.newProxyInstance(Element.class.getClassLoader(),
                new Class[]{Element.class}, new ElementInvocationHandler(driver, info));
    }


}
