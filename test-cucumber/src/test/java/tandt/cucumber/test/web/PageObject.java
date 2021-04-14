package tandt.cucumber.test.web;

import com.google.inject.Inject;
import tandt.cucumber.test.state.KeywordState;
import tandt.web.BaseWebPage;

public class PageObject extends BaseWebPage<PageObject> {

    @Inject
    private KeywordState state;


    public void openPage(String url){
        open(url);
        state.setState(url);
    }


}
