package tandt.mobile.test.pages;

import tandt.mobile.ElementFactory;
import tandt.mobile.annotations.FindElement;
import ui.element.Element;
import tandt.mobile.element.MobileLocatorType;

public class IosPage extends HomePage {

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
