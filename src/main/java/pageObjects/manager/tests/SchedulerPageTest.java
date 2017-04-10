package pageObjects.manager.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.allUsers.HospitalSeekerHomePage;
import pageObjects.manager.HospitalsPage;
import pageObjects.manager.SchedulerPage;
import utilities.BaseNavigation;

public class SchedulerPageTest extends FunctionalTest{
    @Test
    public void testEventCreation() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.setAppointment("Test",3);
        schedulerPage.saveButtonClick();
       // BaseNavigation.logout(driver);
        Thread.sleep(6000);
        System.out.println("1");
        System.out.println(driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[5]/a")));
        driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"dropdawn\"]/li[2]/a/span")).click();

        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");

        System.out.println("I'me here two");
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Test"));


    }
}
