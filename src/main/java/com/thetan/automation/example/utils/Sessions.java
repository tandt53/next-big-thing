//package com.thetan.automation.example.utils;
//
//import java.util.HashMap;
//
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
//
//public class Sessions {
//	private static AppiumDriverLocalService service;
//	private static HashMap<String, String> SessionData = null;
//
//	public static void start() {
//		service = AppiumDriverLocalService.buildDefaultService();
//		service.start();
//
//		if (service == null || !service.isRunning()) {
//			throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//		}
//	}
//
//	public static void end() {
//		if (service != null) {
//			service.stop();
//		}
//
//	}
//
//	public static void saveValueToSession(String key, String value) {
//		if (SessionData == null) {
//			SessionData = new HashMap();
//		}
//		SessionData.put(key, value);
////		LogWork.debug("SAVE_DATA_TO_SESSION_LIST:[" + key + "]=[" + value + "]");
//	}
//
//	public static String getValueFromSession(String key) {
//		String value = SessionData.get(key);
//		if (value == null) {
//			value = key;
////			LogWork.debug("GET_DATA_FROM_SESSION_LIST_IS_NULL:[" + key + "]=[" + value + "]");
//		} 
////		else
////			LogWork.debug("GET_DATA_FROM_SESSION_LIST:[" + key + "]=[" + value + "]");
//		return value;
//	}
//
//}
