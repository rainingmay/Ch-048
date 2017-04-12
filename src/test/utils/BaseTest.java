package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void before() {
        driver = BrowserWrapper.browserInitialization();
    }

    @AfterMethod
    public void afterMethod(){
        try {
            BaseNavigation.logout(this.driver);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @AfterClass
    public void after() {
            BrowserWrapper.browserClose(this.driver);

    }
}
