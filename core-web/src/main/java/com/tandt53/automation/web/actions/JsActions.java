package com.tandt53.automation.web.actions;//package com.tandt.automation.example.actions;
//
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import com.tandt.automation.example.driver.DriverManager;
//
//public class JsActions {
//
//	/**
//	 * execute - generic method to execute a non-parameterized JS command
//	 *
//	 * @param command
//	 */
//	public static void execute(String command) {
//		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		js.executeScript(command);
//	}
//
//	/**
//	 * execute - overloaded method to execute a JS command on WebElement
//	 *
//	 * @param command
//	 * @param element
//	 */
//	public static void execute(String command, WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		js.executeScript(command, element);
//	}
//
//	/**
//	 * click - method to execute a JavaScript click event
//	 *
//	 * @param element
//	 */
//	public static void click(WebElement element) {
//		execute("arguments[0].click();", element);
//	}
//
//	/**
//	 * sendKeys - method to execute a JavaScript value event
//	 *
//	 * @param keys
//	 * @param element
//	 */
//	public static void sendKeys(String keys, WebElement element) {
//		execute("arguments[0].value='" + keys + "';", element);
//	}
//
//	/**
//	 * isPageReady - method to verify that a page has completely rendered
//	 *
//	 * @param driver
//	 * @return boolean
//	 */
//	public static boolean isPageReady() {
//		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		return (Boolean) js.executeScript("return document.readyState").equals("complete");
//	}
//
//	/**
//	 * scroll method - scroll page to pixel (endX, endY)
//	 *
//	 * @param endX
//	 * @param endY
//	 */
//	public static void scroll(int endX, int endY) {
//		execute("window.scrollBy(" + endX + "," + endY + ")");
//	}
//
//	/**
//	 * scroll method - scroll until element is visible
//	 *
//	 * @param element
//	 */
//	public static void scroll(WebElement element) {
//		execute("arguments[0].scrollIntoView();", element);
//	}
//
//	/**
//	 * scrollToBottom method
//	 */
//	public static void scrollToBottom() {
//		execute("window.scrollTo(0, document.body.scrollHeight)");
//	}
//
//	/**
//	 * scrollHorizontal method - horizontal scroll until scrolls the page until the
//	 * mentioned element is in full view
//	 *
//	 * @param element
//	 */
//	public static void scrollHorizontal(WebElement element) {
//		execute("arguments[0].scrollIntoView();", element);
//	}
//
//	/**
//	 * getWindowHeight
//	 * @return long
//	 */
//	public static long getWindowHeight() {
//		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		return (long) js.executeScript("return window.innerHeight;");
//	}
//
//	/**
//	 * getWindowWidth
//	 * @return long
//	 */
//	public static long getWindowWidth() {
//		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		return (long) js.executeScript("return window.innerWidth;");
//	}
//
//
//	public static void highlight(WebElement element) {
//		execute("arguments[0].style.border='3px solid red'", element);
////		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
////		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 3px solid red;");
////        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
//
//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
