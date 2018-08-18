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

	public void startTestCase(String testName, String description, String params) {
		Log.info("---------------------------------- Test '" + testName + description
				+ "' ----------------------------------");
		Log.info("Started: " + testName);
		Log.info("Decription: " + description);
		Log.info("Parameters: " + params);
	}

	public void endTestCase(String testName, String result) {
		Log.info("Result: " + result);
		Log.info("END: " + testName);
	}

	public void endTestCase(String testName, String result, String testMessage) {
		Log.info("Result: " + result);
		Log.info("Message: " + testMessage);
		Log.info("END: " + testName);
	}

	public void configurationError(String instanceName, String name, String error) {
		Log.info("Result CONFIGURATION" + error + "\n");
		Log.info("END CONFIG " + instanceName + "." + name);
	}
}