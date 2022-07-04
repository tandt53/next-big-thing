package onboarding.cucumber.test.web;

import com.google.inject.Inject;
import onboarding.cucumber.test.state.KeywordState;
import onboarding.web.BaseWebPage;

public class PageObject extends BaseWebPage<PageObject> {

    @Inject
    private KeywordState state;

    public void openPage(String url){
        open(url);
        state.setState(url);
    }


}
