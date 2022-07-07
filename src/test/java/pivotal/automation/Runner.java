package pivotal.automation;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"pivotal.automation"}
)
public class Runner extends AbstractTestNGCucumberTests {
}
