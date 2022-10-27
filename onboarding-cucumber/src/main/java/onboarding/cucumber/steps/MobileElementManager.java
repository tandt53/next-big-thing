package onboarding.cucumber.steps;


import onboarding.cucumber.exceptions.ElementSyntaxException;
import onboarding.mobile.element.MobileElementInfo;
import onboarding.ui.element.Element;

import java.util.HashMap;
import java.util.Map;

public class MobileElementManager implements ElementManager<Element, MobileElementInfo> {

    private Map<String, Map<String, MobileElementInfo>> eMap = new HashMap<>();

    @Override
    public Element get(String element) {
        if(element.split(".").length != 2){
            throw new ElementSyntaxException("Element " + element + " is not valid.");
        }
        String page = element.substring(0, element.charAt('.'));
        String elementKey = element.substring(element.charAt('.'));
        return null;
    }

    @Override
    public Element initBaseElement(MobileElementInfo elementInfo) {
        return null;
    }

}
