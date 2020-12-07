package com.tandt53.automation.mobile.test.pages;

import com.tandt53.automation.mobile.BaseMobilePage;
import com.tandt53.automation.mobile.annotations.FindElement;
import com.tandt53.automation.mobile.element.MobileElement;
import com.tandt53.automation.mobile.element.LocatorType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class MyApplicationPage extends BaseMobilePage {
    private AppiumDriver<WebElement> driver;

    @FindElement(type = LocatorType.ID, value="edtUsername")
    private MobileElement edtUsername;

    @FindElement(type = LocatorType.ID, value="edtPassword")
    private MobileElement edtPassword;

    @FindElement(type = LocatorType.ID, value="chbRememberMe")
    private MobileElement chbRememberMe;

    @FindElement(type = LocatorType.ID, value="btnLogin")
    private MobileElement btnLogin;

    @FindElement(type = LocatorType.ID, value="txtErrorMessage")
    private MobileElement txtErrorMessage;

    public MyApplicationPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void login(String username, String password, boolean isCheck){
        edtUsername.setText(username);
        edtPassword.setText(password);
        if(isCheck){
            chbRememberMe.click();
        }
        btnLogin.click();
    }

    public boolean isErrorMessageDisplayed(){
        return txtErrorMessage.isDisplayed();
    }

    public String getMessage(){
        return txtErrorMessage.getText();
    }

}
