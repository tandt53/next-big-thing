package tandt.cucumber.test.mobile;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import tandt.cucumber.test.mobile.components.HomeComponent;
import tandt.mobile.page.BasePage;
import tandt.mobile.page.PageFactory;

@ScenarioScoped
public class HomePage extends BasePage<HomePage> {

    private HomeComponent homeComponent;

    @Inject
    public HomePage(PageFactory pageManager){
        homeComponent = pageManager.create(HomeComponent.class);
    }

    public void login(String username, String password) {
        homeComponent.login(username, password, true);
    }

    public String getMessage(){
        return homeComponent.getMessage();
    }
}
