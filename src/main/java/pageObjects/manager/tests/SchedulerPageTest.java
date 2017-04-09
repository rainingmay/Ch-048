package pageObjects.manager.tests;

import org.testng.annotations.Test;
import pageObjects.manager.HospitalsPage;
import pageObjects.manager.SchedulerPage;
import utilities.BaseNavigation;

public class SchedulerPageTest extends FunctionalTest{
    @Test
    public void testEventCreation() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        System.out.println(schedulerPage.getDate());


    }
}
