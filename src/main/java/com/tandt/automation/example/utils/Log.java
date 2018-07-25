package com.tandt.automation.example.utils;

import org.apache.log4j.Logger;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class Log {

    public Logger Log;

    public Log(Class<?> clazz) {
        Log = Logger.getLogger(clazz);
    }

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public void startTestCase(String sTestCaseName) {
        Log.info("/*****************           START: " + sTestCaseName + "           *****************/");
    }

    //This is to print log for the ending of the test case
    public void endTestCase(String sTestCaseName) {
        Log.info("/*****************           END: " + sTestCaseName + "           *****************/");
    }

    // Need to create these methods, so that they can be called
    public void info(String message) {
        Log.info(message);
    }

    public void warn(String message) {
        Log.warn(message);
    }

    public void error(String message) {
        Log.error(message);
    }

    public void fatal(String message) {
        Log.fatal(message);
    }

    public void debug(String message) {
        Log.debug(message);
    }

}