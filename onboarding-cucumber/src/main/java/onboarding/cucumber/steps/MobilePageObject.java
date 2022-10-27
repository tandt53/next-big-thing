package onboarding.cucumber.steps;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import onboarding.ui.element.Element;

public class MobilePageObject {

    @Inject
    @Named("mobile")
    private ElementManager manager;


    public void click(String element) {
        Element e = manager.get(element);
        e.click();
    }

    public void type(String text, String element) {
//        if (VariableUtils.isVariable(text)) {
//            value = testVariables.getVariable(text).toString();
//        }
        Element e = manager.get(element);

        e.clearText();
        e.setText(text);
    }
}
