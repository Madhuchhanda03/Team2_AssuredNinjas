package testRunner;



import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

       features = "src/test/resources/features",
       glue = { "stepDefinitions" },
       monochrome = true,
       
       plugin = {
             "pretty",
             "html:target/cucumber.html",
             "json:target/cucumber.json",
             "junit:testngtarget/CucumberReports/CucumberReport.xml",
             "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",

       }
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
       return super.scenarios();
    }
}
