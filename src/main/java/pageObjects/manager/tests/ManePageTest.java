package pageObjects.manager.tests;


import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.manager.HospitalsPage;


/**
 * Created by radga on 07.04.2017.
 */
public class ManePageTest extends FunctionalTest {


    @Test
    public void testDoctorsPerPage(){
        driver.get("https://localhost:8443/HospitalSeeker/");

        driver.findElement(By.cssSelector("img.localization-flag")).click();
        driver.findElement(By.linkText("Українська")).click();
        driver.findElement(By.linkText("Ввійти")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("manager.jh@hospitals.ua");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("1111");
        driver.findElement(By.id("loginSubmit")).click();

        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        //hospitalsPage.select("doctorsPerPage", "10");
        System.out.println(driver.findElements(By.tagName("tr")).size());
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 10);
    }


}
