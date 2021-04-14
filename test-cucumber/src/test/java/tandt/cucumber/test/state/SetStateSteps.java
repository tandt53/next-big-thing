package tandt.cucumber.test.state;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;

public class SetStateSteps {

    @Inject
    KeywordState state;


    @Given("I set state value is {string}")
    public void iSetStateValueIs(String arg0) {
        state.setState(arg0);
    }


}
