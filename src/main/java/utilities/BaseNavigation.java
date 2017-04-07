package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {

    public static void login(WebDriver driver, String email, String password) throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver, 20L);

        driver.findElement(By.cssSelector("a[href=\"/HospitalSeeker/login\"]")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginSubmit")).click();
    }

}
