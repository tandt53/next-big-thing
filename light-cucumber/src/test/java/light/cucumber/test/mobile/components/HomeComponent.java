package light.cucumber.test.mobile.components;

import light.mobile.page.BasePage;

public abstract class HomeComponent extends BasePage<HomeComponent> {

    public abstract void login(String admin, String admin1, boolean b);

    public abstract String getMessage();

}
