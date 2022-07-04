package onboarding.mobile.test.pages;

import onboarding.mobile.page.BasePage;

public abstract class HomePage extends BasePage<HomePage> {

    public abstract void login(String admin, String admin1, boolean b);

    public abstract String getMessage();

}
