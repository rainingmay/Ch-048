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
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.setAppointment("Test",3);
        schedulerPage.saveButtonClick();


        schedulerPage.managerHeader.logOut();

        driver.findElement(By.xpath("(//a[contains(@href, '#')])[2]")).click();
        driver.findElement(By.xpath("(//ul[@id='dropdawn']/li[2]/a/span)[2]")).click();

        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Test"));


    }
}
