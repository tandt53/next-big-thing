//package com.tandt53.automation.mobile.utils;
//
//import com.tandt53.automation.common.utils.Log;
//import com.tandt53.automation.mobile.driver.MobileDriverManager2;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class VerifyUtils {
//
//    private static Log log = new Log(VerifyUtils.class);
//
//    public static void verifyTrue(boolean expected, String message) {
//        if (!expected) {
//            performActions(message);
//        }
//    }
//
//    public static void verifyEquals(String expected, String actual, String message) {
//        if (!expected.equals(actual)) {
//            log.info("Expected: " + expected + " , but was: " + actual);
//            performActions(message);
//        }
//    }
//
//    public static void verifyEquals(Integer expected, Integer actual, String message) {
//        if (expected != actual) {
//            log.info("Expected: " + expected + " , but was: " + actual);
//            performActions(message);
//        }
//    }
//
//    public static void verifyEquals(Character expected, Character actual, String message) {
//        if (expected.compareTo(actual) != 0) {
//            log.info("Expected: " + expected + " , but was: " + actual);
//            performActions(message);
//        }
//    }
//
//    private static void performActions(String message) {
//        log.info(message);
//        MobileDriverManager2.takeScreenShot(message);
//    }
//
//    public static String getCurrentDateTime() {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/YYY - HH:mm");
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatted = " + formattedDate);
//        return formattedDate;
//    }
//
//    public static String getCurrentMonthYear() {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMMM - YYYY");
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatted = " + formattedDate);
//        return formattedDate;
//    }
//
//}