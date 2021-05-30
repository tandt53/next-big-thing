package tandt.cucumber.test.mobile;

import com.google.inject.Inject;
import tandt.cucumber.test.mobile.components.HomeComponent;
import tandt.mobile.page.BasePage;
import tandt.mobile.page.PageFactory;
import tandt.mobile.page.pagemanager.PageManager;

import javax.inject.Singleton;

@Singleton
public class HomePage extends BasePage<HomePage> {

    private HomeComponent homeComponent;

    @Inject
    public HomePage(PageFactory pageManager){
        homeComponent = pageManager.create(HomeComponent.class, new HomePageBinder());
    }

    public void login(String username, String password) {
        homeComponent.login(username, password, true);
    }

    public String getMessage(){
        return homeComponent.getMessage();
    }
}
