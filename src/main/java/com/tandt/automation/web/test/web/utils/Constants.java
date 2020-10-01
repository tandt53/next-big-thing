package com.tandt.automation.web.test.web.utils;

public class Constants {
	
	// 
	public static final String BROWSER_CHROME = "Web Chrome";
	public static final String BROWSER_FIREFOX = "Web FireFox";
	public static final String BROWSER_IE = "Web IE";
	public static final String BROWSER_SAFARI = "Web Safari";
	public static final String MOBILE_NATIVE_ANDROID = "Mobile native android";
	public static final String MOBILE_NATIVE_IOS = "Mobile native iOS";
	public static final String MOBILE_HYBRID_ANDROID = "Mobile hybrid android";
	public static final String MOBILE_HYBRID_IOS = "Mobile hybrid android";

	// link
	public static final String urlLink = "http://google.com.vn/";
	public static final String invalidLink = "google.com";
	
	
	public static String DEF_BROWSER = null;
    public static String DEF_PLATFORM = null;
    public static String DEF_ENVIRONMENT = null;
    public static String SUITE_NAME = null;
    
	public static final String TEST_OUTPUT_PATH = "test-output/";
    public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Reports/";
    public static final String REPORT_CONFIG_FILE = "src/main/resources/extent-config.xml";


}
