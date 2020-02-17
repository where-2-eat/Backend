package ca.mcgill.ecse428.where2eat.backend.features;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources",
        glue = "ca.mcgill.ecse428.where2eat.backend.features")
@SpringBootTest
public class CucumberTestsRunner {
}
