package onboarding.cucumber.steps;


import io.cucumber.guice.ScenarioScoped;
import onboarding.common.exceptions.CommonException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ScenarioScoped
public class TestVariables {
    private static final String REGEX = "\\$\\{([^\\\\$\\\\]+)\\}";

    private Map<String, Object> vars;

    public TestVariables() {
        vars = new HashMap<>();
    }

    public void setVariable(String varName, Object value){
    }

    public Object getVariable(String key) {
        return vars.get(key);
    }

    public String formatVariable(String text){
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);

        String returnString = text;
        while (matcher.find()) {
            String replacedString = matcher.group(0);
            String key = matcher.group(1);
            String replaceValue = vars.get(key).toString();
            if (replaceValue == null || replaceValue.isEmpty()) {
                throw new CommonException("Variable variable with name " + key + " does not exist.");
            }
            returnString = returnString.replace(replacedString, replaceValue);
        }
        return returnString;
    }
}
