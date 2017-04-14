package pages.managerScheduler;


import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.manager.HospitalsPage;
import pages.manager.SchedulerPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.databaseutil.UserDAO;


public class SchedulerPageTest extends BaseTest{

    public static final String TEST_BEGIN_AT_HOUR = "11:00";
    public static final String TEST_END_AT_HOUR = "20:00";
    public static final String TEST_WEEK_SIZE = "6 days";
    public static final int EXPECTED_WEEK_SIZE = 6;
    public static final String EXPECTED_BEGIN_AT_HOUR = "11 00";
    public static final String EXPECTED_END_AT_HOUR = "19 00";

    @BeforeMethod
    public void eventCleaner(){
        boolean b = UserDAO.deleteAllEvents();
        System.out.println(b);
    }

    @Test
    public void testElementPresence() throws Exception{
        BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        try{
            BrowserWrapper.waitForPage(driver);
            schedulerPage.isPageReady();
        }catch (Exception e){

            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady());

    }

    @Test(priority = 1)
    public void testDefaultSchedulerValues() throws Exception{
        BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.waitForPage(driver);
        try{
            schedulerPage.checkDefaultConditionScheduler();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler());
    }


    @Test(priority = 2)
    public void testWeekSize() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE);
    }

    @Test(priority = 2)
    public void testWorkingDayDuration() throws Exception {
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR));

    }



    @Test(priority = 6)
    public void testEventCreation() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        try {
            schedulerPage.setAppointment("Test", 3);
            schedulerPage.saveButtonClick();
        }catch (Exception e){
            e.printStackTrace();
        }
        BaseNavigation.logout(driver);

        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextMonthButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Test"));
    }

    @Test(priority = 3)
    public void testEventDeletion() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        try {
            schedulerPage.setAppointment("Test", 3);
            schedulerPage.saveButtonClick();
        }catch (Exception e){
            e.printStackTrace();
        }
        BrowserWrapper.refreshPage(driver);
        schedulerPage.nextMonthButtonClick();
        schedulerPage.callEventContext();
        schedulerPage.deleteEventButtonClick();
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextMonthButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0);
    }

    @Test(priority = 4)
    public void testEventEdition() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        try {
            schedulerPage.setAppointment("Test", 3);
            schedulerPage.saveButtonClick();
        }catch (Exception e){
            e.printStackTrace();
        }
        BrowserWrapper.refreshPage(driver);
        schedulerPage.nextMonthButtonClick();
        schedulerPage.callEventContext();
        schedulerPage.editEventText("Another text");
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextMonthButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Another text"));
    }

    @Test(priority = 5)
    public void testAlertIfNotSaved() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        try {
            schedulerPage.setAppointment("Test", 3);
        }catch (Exception e){
            e.printStackTrace();
        }
        BrowserWrapper.refreshPage(driver);
        Assert.assertTrue(BrowserWrapper.isAlertPresent(driver));
        BrowserWrapper.conformAlert(driver);
    }
}
