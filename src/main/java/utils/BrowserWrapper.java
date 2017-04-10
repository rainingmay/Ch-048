package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BrowserWrapper {

    public static final int STANDARD_WAIT_TIME = 1000;

    protected WebDriver driver;
    
    BrowserWrapper(WebDriver driver) {
        this.driver = driver;
    }

    
    public String getTitle() {
        return driver.getTitle();
    }

    public boolean containsText(String text) {
        if (driver.getPageSource().contains(text)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresentById(String id) {
        try {
            return driver.findElement(By.id(id)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
   
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void browserBack() {
        driver.navigate().back();
    }

    public void waitUntilElementVisible(WebElement element) {
        new WebDriverWait(driver, STANDARD_WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
    }

    public void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void browserMaximize() {
        driver.manage().window().maximize();
    }

    
}

