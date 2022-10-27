package onboarding.cucumber.steps;


import onboarding.mobile.element.MobileElementInfo;
import onboarding.ui.element.Element;
import onboarding.web.element.WebElementInfo;

public class WebElementManager implements ElementManager<Element, WebElementInfo> {
    @Override
    public Element get(String elementName) {
        return null;
    }

    @Override
    public Element initBaseElement(WebElementInfo elementInfo) {
        return null;
    }

}
