package light.cucumber.test.vars;

import io.cucumber.guice.ScenarioScoped;

@ScenarioScoped
public class TestVariable {
    private String var;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
