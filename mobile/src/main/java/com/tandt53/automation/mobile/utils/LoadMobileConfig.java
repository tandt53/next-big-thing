//package com.tandt53.automation.mobile.utils;
//
//import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
//import com.tandt53.automation.dataprovider.properties.PropertiesLoader;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//public class LoadMobileConfig {
//
//    private static Properties props = new Properties();
//    private static String fSeparator = File.separator;
//    private static String propertiesFile = Constants.PROPERTIES_FILE;
//    private static FileInputStream fs;
//
//    private static String propAppiumServerPort = "appium.server.port";
//    private static String propAppiumServerHost = "appium.server.host";
//    private static String propTestEnv = "testEnv";
//    private static String propPlatform = "platform";
//    private static String propPlatformVersion = "platformVersion";
//    private static String propAppFileName = "appName";
//    private static String propDeviceName = "device.name";
//    private static String propDeviceUdid = "device.udid";
//    private static String propBrowserName = "browserName";
//    private static String propLanguage = "language";
//    private static String propAppPackage = "appPackage";
//    private static String propAppActivity = "appActivity";
//    private static String propUserName = "userName";
//    private static String propAccessKey = "accessKey";
//    private static String propBuildNumber = "buildNumber";
//    private static String propProjectTag = "projectTag";
//    private static String propDbUser = "db.user";
//    private static String propDbPassword = "db.password";
//    private static String propStfHost = "stf.host";
//    private static String propStfToken = "stf.token";
//    private static String noReset = "noReset";
//    private static String propAppJsonPath = "app";
//    private static String propDeviceJsonPath = "device";
//    private static String propAppiumJsonPath = "appium";
//    private static String propAppFilePath = "appFilePath";
//    private static String propPageManagerPath = "pageManagerPath";
//    private static String propListFileFeature = "listFileFeature";
//
//
//    public static List<String> getPropListFileFeature() {
//        List<String> result = new ArrayList<String>();
//        String listFeature = getProperty(propertiesFile, propListFileFeature.trim());
//        String[] strings = listFeature.split(",");
//        for (String str : strings) {
//            System.out.print(str.trim());
//            result.add(str.trim());
//        }
//        return result;
//    }
//
//    public static String getAppiumServerHost() {
//        return getProperty(propertiesFile, propAppiumServerHost.trim());
//    }
//
//    public static String getAppiumServerPort() {
//        return getProperty(propertiesFile, propAppiumServerPort.trim());
//    }
//
//    public static String getTestEnv() {
//        return getProperty(propertiesFile, propTestEnv.trim());
//    }
//
//    public static String getPlatform() {
//        return getProperty(propertiesFile, propPlatform.trim());
//    }
//
//    public static String getPlatformVersion() {
//        return getProperty(propertiesFile, prop(propPlatformVersion.trim()));
//    }
//
//    public static String getAppPath() throws IOException {
//        String appName = getProperty(propertiesFile, propAppFileName);
//        if (appName != null && appName.contains("bs://")) {
//            return appName;
//        } else {
//            appName = getProperty(propertiesFile, prop(propAppFileName.trim()));
//            File appDir = new File(new File(System.getProperty("user.dir")), "./res/apps/");
//            File app = new File(appDir.getCanonicalPath(), appName);
//            return app.getAbsolutePath();
////            return appName;
//        }
//    }
//
//    public static String getDeviceName() {
//        return getProperty(propertiesFile, prop(propDeviceName.trim()));
//    }
//
//    public static String getDeviceUdid() {
//        return getProperty(propertiesFile, prop(propDeviceUdid.trim()));
//    }
//
//    public static String getBrowserName() {
//        return getProperty(propertiesFile, prop(propBrowserName.trim()));
//    }
//
//    public static String getAppPackage() {
//        return getProperty(propertiesFile, prop(propAppPackage.trim()));
//    }
//
//    public static String getAppActivity() {
//        return getProperty(propertiesFile, prop(propAppActivity.trim()));
//    }
//
//    public static String getUserName() {
//        return getProperty(propertiesFile, propUserName.trim());
//    }
//
//    public static String getAccessKey() {
//        return getProperty(propertiesFile, propAccessKey.trim());
//    }
//
//    public static String getNoReset() {
//        return getProperty(propertiesFile, noReset.trim());
//    }
//
//    public static String getBuildNumber() {
//        return getProperty(propertiesFile, propBuildNumber.trim());
//    }
//
//    public static String getProjectTag() {
//        return getProperty(propertiesFile, propProjectTag.trim());
//    }
//
//    public static String getDbUser() {
//        return getProperty(propertiesFile, propDbUser.trim());
//    }
//
//    public static String getDbPassword() {
//        return getProperty(propertiesFile, propDbPassword.trim());
//    }
//
//    public static String getStfHost() {
//        return getProperty(propertiesFile, propStfHost.trim());
//    }
//
//    public static String getStfAccessToken() {
//        return getProperty(propertiesFile, propStfToken.trim());
//    }
//
//    public static String getPropAppJsonPath() {
//        return getProperty(propertiesFile, propAppJsonPath.trim());
//    }
//
//    public static String getPropDeviceJsonPath() {
//        return getProperty(propertiesFile, propDeviceJsonPath.trim());
//    }
//
//    public static String getPropAppiumJsonPath() {
//        return getProperty(propertiesFile, propAppiumJsonPath.trim());
//    }
//
//    public static String getPropAppFilePath() {
//        return getProperty(propertiesFile, propAppFilePath);
//    }
//
//    public static String getLanguage() {
//        String language = getProperty(propertiesFile, prop(propLanguage.trim()));
//        if (language == null) {
//            language = "en";
//        }
//        return language;
//    }
//
//    public static String getProperty(String proKey) {
//        return getProperty(propertiesFile, proKey);
//    }
//
//    private static String getProperty(String file, String proKey) {
//        try {
//            return PropertiesLoader.getProperty(propertiesFile /* + fSeparator + file */, proKey).toString();
//        } catch (PropertiesException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String getPropertyPageManagerPath(String file, String propPageManagerPath) throws PropertiesException {
//        return PropertiesLoader.getProperty(propertiesFile /* + fSeparator + file */, propPageManagerPath).toString();
//    }
//
//    private static String prop(String prop) {
//        return getPlatform().toLowerCase() + "." + prop;
//    }
//}
//
