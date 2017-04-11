package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by radga on 10.04.2017.
 */
public class WebElementWrapper {
    WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver,10);
    public WebElementWrapper(WebDriver driver){
        this.driver = driver;
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementEnable(WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilElementSelectionState(WebElement element, boolean bool) {
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, bool));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilTitleContains(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitUntilAllVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilUrlAvaliable(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
    public void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
