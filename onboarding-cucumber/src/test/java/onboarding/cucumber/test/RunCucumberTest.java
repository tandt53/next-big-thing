package onboarding.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/google.feature",
        glue = "onboarding.cucumber",
        plugin = {"onboarding.cucumber.listener.CucumberListener",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Web_S1")
public class RunCucumberTest {
}
