package com.thetan.automation.example.pages;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConnectScreen {

    @FindBy(xpath = "//*[contains(@class,'text-input')][contains(@name,'address')]")
    WebElement serverAddress;

    @FindBy(xpath = "//*[contains(@class,'text-input')][contains(@name,'port')]")
    WebElement port;

    @FindBy(className = "no-margin-left")
    WebElement connect;

    @FindBy(className = "toolbar-title")
    WebElement toolbar;

    AndroidDriver driver;

    public ConnectScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setServerAddress(String address) {
        serverAddress.sendKeys(address);
    }

    public void setPort(String p) {
        port.sendKeys(p);
    }

    public void connect() {
        connect.click();
    }

    public String getToolbarText() {
        return toolbar.getText();
    }

    public void close(){
        driver.quit();
    }
}
