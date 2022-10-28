package onboarding.cucumber.steps;

import onboarding.ui.element.Element;
import onboarding.ui.element.ElementInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementManagerUtils {


    public static String getName(String elementName) {
        return isContainArgs(elementName) ? splitName(elementName).get(0) : elementName;
    }

    public static boolean isContainArgs(String elementName) {
        String regex = ".+\\[.+\\]";
        return elementName.matches(regex);
    }

    public static List<String> splitName(String elementName) {
        List<String> nameElements = new ArrayList<>();
        String regex = "(.+)\\[(.+)\\]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elementName);
        while (matcher.find()) {
            nameElements.add(matcher.group(1));
            nameElements.add(matcher.group(2));
        }
        return nameElements;
    }

    public static String formatLocatorValueWithArgs(String locatorValue, String elementName) {
        if (isContainArgs(elementName)) {
            String elementArgs = splitName(elementName).get(1);
            locatorValue = formatElement(locatorValue, elementArgs);
        }
        return locatorValue;
    }

    public static String formatElement(String name, String elementArgs) {
        String magicDelimiter = "ONBOARDING";
        elementArgs = elementArgs.replace("\",\"", "\"" + magicDelimiter + "\"");
        elementArgs = elementArgs.replace("\"", "");
        String[] argArray = elementArgs.split(magicDelimiter);
        return String.format(name, argArray);
    }


}
