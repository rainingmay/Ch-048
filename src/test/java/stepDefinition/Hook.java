package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import utils.Driver;

/**
 * Created by radgast on 22.04.17.
 */
public class Hook {
    @Before
    public void setUp(){
        Driver.initialization();
    }

    @After
    public void embedScreenshot(Scenario scenario){

        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + Driver.instance().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)Driver.instance()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }
        Driver.close();

    }

}
