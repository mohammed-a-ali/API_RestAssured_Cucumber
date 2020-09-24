package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",
        glue = {"runner", "steps"},
        tags = {"not @ignore"},
        plugin = {"html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
