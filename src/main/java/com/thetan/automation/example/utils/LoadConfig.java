package com.thetan.automation.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {

	private static Properties prop = new Properties();
	private static InputStream input = null;

	private static Properties loadConfig() {
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}
	
	public static String getConfigBrowser() {
		return loadConfig().getProperty("browser");
	}
}
