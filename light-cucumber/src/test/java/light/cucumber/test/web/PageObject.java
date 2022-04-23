package light.cucumber.test.web;

import com.google.inject.Inject;
import light.cucumber.test.state.KeywordState;
import light.web.BaseWebPage;

public class PageObject extends BaseWebPage<PageObject> {

    @Inject
    private KeywordState state;

    public void openPage(String url){
        open(url);
        state.setState(url);
    }


}
