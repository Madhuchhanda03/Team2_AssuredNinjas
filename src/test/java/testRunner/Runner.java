package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class Runner {
	@CucumberOptions(features = "src/test/resources/features", 
			glue = { "stepDefinitions"},
			monochrome = true, dryRun = false,
            plugin = { "pretty",
					"html:target/cucumber.html", "json:target/cucumber.json",
					"junit:testngtarget/CucumberReports/CucumberReport.xml",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
			})
	
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

		@Override
		@DataProvider(parallel = true)
		public Object[][] scenarios() {
			return super.scenarios();
		}
	}
}
