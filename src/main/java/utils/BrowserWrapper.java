package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 11.04.2017.
 */
public class BrowserWrapper {

    private static WebDriverWait wait = new WebDriverWait(Driver.instance(),30,250);




    public static boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public static void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }



    public static void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementClickableByLocator(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public static void waitUntiElementsVisible(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public static void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementLocated(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public static void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }


    public static void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void waitForPage(){
        Driver.instance().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public static void refreshPage(){
        Driver.instance().navigate().refresh();
    }

    public static boolean isAlertPresent(){
        boolean foundAlert;
        WebDriverWait wait = new WebDriverWait(Driver.instance(), 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException e) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public static void conformAlert(){
        waitUntilAlertIsPresent();
        Alert alert = Driver.instance().switchTo().alert();
        alert.dismiss();
    }


    public static void dragdrop(WebElement LocatorFrom, String xto, String yto) {
        ((JavascriptExecutor)Driver.instance()).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
                        "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
                LocatorFrom,xto,yto);
    }

    public static void doubleClick(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)Driver.instance();
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }
}
