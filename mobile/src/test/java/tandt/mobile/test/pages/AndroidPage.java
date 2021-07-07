package tandt.mobile.test.pages;

import tandt.mobile.ElementFactory;
import tandt.mobile.annotations.FindElement;
import tandt.mobile.drivermanager.DriverManager;
import tandt.mobile.element.MobileLocatorType;
import ui.element.Element;

public class AndroidPage extends HomePage {

    @FindElement(type = MobileLocatorType.ID, value="edtUsername")
    private Element edtUsername;

    @FindElement(type = MobileLocatorType.ID, value="edtPassword")
    private Element edtPassword;

    @FindElement(type = MobileLocatorType.ID, value="chbRememberMe")
    private Element chbRememberMe;

    @FindElement(type = MobileLocatorType.ID, value="btnLogin")
    private Element btnLogin;

    @FindElement(type = MobileLocatorType.ID, value="txtErrorMessage")
    private Element txtErrorMessage;

    public AndroidPage(){
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
