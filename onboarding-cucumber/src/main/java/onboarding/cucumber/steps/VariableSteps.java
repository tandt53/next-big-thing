package onboarding.cucumber.steps;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.Map;

public class VariableSteps {
    @Inject
    private TestVariables vars;

    @And("I set variable {string} as {string}")
    public void iSetVariableWithAnd(String varName, String value) {
        varName = vars.formatVariable(varName);
        value = vars.formatVariable(value);
        vars.setVariable(varName, value);
    }

    @And("I set list variables")
    public void iSetListVariables(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        vars.setVariable(map);
    }
}
