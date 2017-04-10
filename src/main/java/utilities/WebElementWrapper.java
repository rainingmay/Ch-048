package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by ytomktc on 10.04.2017.
 */
public class WebElementWrapper {
    public WebDriver driver;

    public WebElementWrapper(WebDriver driver){
        this.driver = driver;
    }


    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementEnable(WebElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;

        }
    }
    public boolean isElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
