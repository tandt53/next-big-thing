package onboarding.cucumber.steps;

import onboarding.cucumber.exceptions.ElementInPageNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementManagerUtils {
    private static final String REGEX_PAGE_ELEMENT = "^(.+)\\.(.+)$";
    private static final String REGEX_PAGE_ELEMENT_FORMAT = "^(.+)\\.(.+)\\[(.+)]$";


    /**
     * This method always returns 3-elements list
     * @param element
     * @return
     */
    public static List<String> parse(String element) {
        if (element.matches(REGEX_PAGE_ELEMENT_FORMAT)) {
            Pattern pattern = Pattern.compile(REGEX_PAGE_ELEMENT_FORMAT);
            Matcher matcher = pattern.matcher(element);
            matcher.find();
            return Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3));
        } else if (element.matches(REGEX_PAGE_ELEMENT)) {
            Pattern pattern = Pattern.compile(REGEX_PAGE_ELEMENT);
            Matcher matcher = pattern.matcher(element);
            matcher.find();
            return Arrays.asList(matcher.group(1), matcher.group(2), "");
        } else {
            throw new ElementInPageNotFoundException("Element " + element + " not found.");
        }
    }

}
