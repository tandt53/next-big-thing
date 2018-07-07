package com.thetan.automation.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Strings;

public class LoadConfig {

	private static Properties prop = new Properties();
	private static InputStream input = null;

	public static Properties loadConfig() {
		try {
			input = new FileInputStream("config.properties");
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

	public static String getSystemProperties(String propName, String propKey) {
		String propValue = "";
		propValue = System.getProperty(propName);
		if (prop == null) {
			prop = LoadConfig.loadConfig();
		}
		if (Strings.isNullOrEmpty(propValue)) {
			propValue = prop.getProperty(propKey);	
		}
		return propValue;
	}

	public static String getUserName() {
		return getSystemProperties("user", "user.name");
	}

	public static String getPassword() {
		return getSystemProperties("pass", "user.pass");
	}

	public static String getLanguage() {
		String language = getSystemProperties("language", "language");
		if (language == null) {
			language = "en_US";
		}
		return language;
	}

	public static String getFileApp() {
		return getSystemProperties("app", "app.fileName");
	}

	public static String getAppiumVersion() {
		return getSystemProperties("appiumversion", "app.appiumVersion");
	}

	public static String getPlatformName() {
		return getSystemProperties("platformname", "app.platformName");
	}

	public static String getPlatformVersion() {
		return getSystemProperties("platformversion", "app.platformVersion");
	}

	public static String getDeviceName() {
		return getSystemProperties("device", "app.deviceName");
	}

	public static String getServerName() {
		return getSystemProperties("server", "server.name");
	}

	public static String getServerHost() {
		return getSystemProperties("host", "server.host");
	}

	public static String getServerPort() {
		return getSystemProperties("port", "server.port");
	}

	public static String getProtocol() {
		return getSystemProperties("protocol", "server.protocol");
	}
}
