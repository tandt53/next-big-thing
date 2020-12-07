package com.tandt53.automation.common;


import com.tandt53.automation.common.exceptions.CommonException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * This method is
     * - Check string contain System environment (pattern ${varName})
     * - Get value from System Variables
     * - Replace pattern String by that value
     *
     * @param text
     * @return
     * @throws CommonException
     */
    public static String parseVariables(String text) throws CommonException {
        String regex = "\\$\\{([^\\\\$\\\\]+)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        String returnString = text;
        while (matcher.find()) {
            String replacedString = matcher.group(0);
            String key = matcher.group(1);
            String replaceValue = System.getenv(key);
            if (replaceValue == null || replaceValue.isEmpty()) {
                throw new CommonException("System Variable with name " + key + " is not set.");
            }
            returnString = returnString.replace(replacedString, replaceValue);
        }
        return returnString;
    }

}
