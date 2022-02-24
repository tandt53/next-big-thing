package tandt.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/google.feature",
        glue = "tandt.cucumber.test",
        tags = "@Mobile_S1",
        plugin = {"tandt.cucumber.listener.TestListener"})
public class RunCucumberTest {
}
