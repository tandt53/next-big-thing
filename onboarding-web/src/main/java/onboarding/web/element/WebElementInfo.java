package onboarding.web.element;

import onboarding.ui.element.ElementInfo;

public class WebElementInfo extends ElementInfo {
    private WebLocatorType webLocatorType;

    public WebElementInfo() {

    }

    public WebElementInfo(WebElementInfo elementInfo) {
        this.setName(elementInfo.getName());
        this.setLocatorValue(elementInfo.getLocatorValue());
        this.setLocatorType(elementInfo.getLocatorType());
        this.setStrategy(elementInfo.getStrategy());
    }

    public WebLocatorType getLocatorType() {
        return webLocatorType;
    }

    public void setLocatorType(WebLocatorType webLocatorType) {
        this.webLocatorType = webLocatorType;
    }
}
