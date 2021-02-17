package tandt.web.actions;//package com.tandt.automation.example.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsActions {

    private WebDriver driver;

    public JsActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * execute - generic method to execute a non-parameterized JS command
     *
     * @param command
     */
    public void execute(String command) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command);
    }

    /**
     * execute - overloaded method to execute a JS command on WebElement
     *
     * @param command
     * @param element
     */
    public void execute(String command, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command, element);
    }

    /**
     * click - method to execute a JavaScript click event
     *
     * @param element
     */
    public void click(WebElement element) {
        execute("arguments[0].click();", element);
    }

    /**
     * sendKeys - method to execute a JavaScript value event
     *
     * @param keys
     * @param element
     */
    public void sendKeys(String keys, WebElement element) {
        execute("arguments[0].value='" + keys + "';", element);
    }

    /**
     * isPageReady - method to verify that a page has completely rendered
     *
     * @return boolean
     */
    public boolean isPageReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.readyState").equals("complete");
    }

    /**
     * scroll method - scroll page to pixel (endX, endY)
     *
     * @param endX
     * @param endY
     */
    public void scroll(int endX, int endY) {
        execute("window.scrollBy(" + endX + "," + endY + ")");
    }

    /**
     * scroll method - scroll until element is visible
     *
     * @param element
     */
    public void scroll(WebElement element) {
        execute("arguments[0].scrollIntoView();", element);
    }

    /**
     * scrollToBottom method
     */
    public void scrollToBottom() {
        execute("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * scrollHorizontal method - horizontal scroll until scrolls the page until the
     * mentioned element is in full view
     *
     * @param element
     */
    public void scrollHorizontal(WebElement element) {
        execute("arguments[0].scrollIntoView();", element);
    }

    /**
     * getWindowHeight
     *
     * @return long
     */
    public long getWindowHeight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (long) js.executeScript("return window.innerHeight;");
    }

    /**
     * getWindowWidth
     *
     * @return long
     */
    public long getWindowWidth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (long) js.executeScript("return window.innerWidth;");
    }


    public void highlight(WebElement element) {
        execute("arguments[0].style.border='3px solid red'", element);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
