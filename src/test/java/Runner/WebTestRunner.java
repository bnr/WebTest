package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/Feature/HomePage.feature"},
		glue={"StepDefination"},tags = "@smoke",
		plugin= {"pretty","html:target/cucumber-html-report/index.html"}
		)
public class WebTestRunner {

}

