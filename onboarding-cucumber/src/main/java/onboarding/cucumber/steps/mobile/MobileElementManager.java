package onboarding.cucumber.steps.mobile;


import io.appium.java_client.AppiumDriver;
import onboarding.cucumber.exceptions.ElementInPageNotFoundException;
import onboarding.cucumber.steps.ElementManagerUtils;
import onboarding.cucumber.steps.PageLoader;
import onboarding.mobile.element.ElementInvocationHandler;
import onboarding.mobile.element.MobileElementInfo;
import onboarding.ui.element.Element;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MobileElementManager {

    private static Map<String, Map<String, MobileElementInfo>> locators = new HashMap<>();
    private static Map<String, Element> elements = new HashMap<>();

    public static Element get(String element, AppiumDriver driver) {
        if (elements.containsKey(element)) {
            return elements.get(element);
        }

        List<String> eComponents = ElementManagerUtils.parse(element);
        String page = eComponents.get(0);
        String elementName = eComponents.get(1);
        String formatText = eComponents.get(2);

        Map<String, MobileElementInfo> pageLocators;
        if (locators.containsKey(page)) {
            pageLocators = locators.get(page);
        } else {
            pageLocators = new PageLoader().load(page, driver);
            locators.put(page, pageLocators);
        }

        MobileElementInfo loadedInfo = pageLocators.get(elementName);
        if (loadedInfo == null) {
            throw new ElementInPageNotFoundException("Element " + element + " not found.");
        }
        MobileElementInfo info = new MobileElementInfo(loadedInfo);
        info.setValue(String.format(info.getValue(), formatText));
        Element initElement = (Element) Proxy.newProxyInstance(Element.class.getClassLoader(),
                new Class[]{Element.class}, new ElementInvocationHandler(driver, info));
        elements.put(element, initElement);
        return initElement;
    }


}
