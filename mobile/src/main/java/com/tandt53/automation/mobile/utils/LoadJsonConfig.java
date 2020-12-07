//package com.tandt53.automation.mobile.utils;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//
//public class LoadJsonConfig {
//
//    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
//        loadFromFile(System.getProperty("user.dir") + "/config.json");
//    }
//
//    public static void loadFromFile(String filePath) throws FileNotFoundException, MalformedURLException {
//        Gson gson = new Gson();
////        HashMap<String, Object> map  = gson.fromJson(new FileReader(filePath), HashMap.class);
//        JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class)
//                .get("android").getAsJsonObject()
//                .get("local").getAsJsonObject();
////        HashMap<String, Object> map =
////                gson.fromJson(new FileReader(filePath), new TypeToken<HashMap<String, Object>>(){}.getType());
//
//        HashMap<String, Object> map =
//                new Gson().fromJson(jsonObject.toString(), new TypeToken<HashMap<String, Object>>() {
//                }.getType());
//
//        System.out.println(map.getClass().toString());
//        System.out.println(map);
//
////        String port = LoadMobileConfig.getAppiumServerPort();
//        String url = "http://127.0.0.1:4723/wd/hub";
//        System.out.println("URL: " + url);
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(map);
//        new AndroidDriver<WebElement>(new URL(url), desiredCapabilities);
//
//    }
//}
