package tandt.mobile.test.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import tandt.mobile.ElementFactory;
import tandt.mobile.annotations.FindElement;
import tandt.mobile.element.BaseMobileElement;
import tandt.mobile.element.MobileLocatorType;

public class IosPage extends HomePage {

    private AppiumDriver<WebElement> driver;

    @FindElement(type = MobileLocatorType.ID, value="edtUsername")
    private BaseMobileElement edtUsername;

    @FindElement(type = MobileLocatorType.ID, value="edtPassword")
    private BaseMobileElement edtPassword;

    @FindElement(type = MobileLocatorType.ID, value="chbRememberMe")
    private BaseMobileElement chbRememberMe;

    @FindElement(type = MobileLocatorType.ID, value="btnLogin")
    private BaseMobileElement btnLogin;

    @FindElement(type = MobileLocatorType.ID, value="txtErrorMessage")
    private BaseMobileElement txtErrorMessage;

    public IosPage(){
        System.out.println("============ ios page ===========");
        ElementFactory.initElements(this);
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

    protected String getUrl() {
        return null;
    }
}
