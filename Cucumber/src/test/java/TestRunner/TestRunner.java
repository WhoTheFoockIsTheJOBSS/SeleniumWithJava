package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@smoke",
        features = "src/test/java/Features",
        glue = "Step_Definitions",
        plugin = {
                "pretty",
                "html:target/cucumber-html.html",
                "json:target/cucumber-json.json",
                "junit:target/cucumber-xml.xml"
        },
        monochrome = false
)

public class TestRunner {
    @BeforeClass
    public static void setup() {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void teardown() {
        System.out.println("After Class");
    }
}
