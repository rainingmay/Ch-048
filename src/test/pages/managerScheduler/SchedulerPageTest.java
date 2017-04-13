package pages.managerScheduler;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.manager.HospitalsPage;
import pages.manager.SchedulerPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;



public class SchedulerPageTest extends BaseTest{

    public static final String TEST_BEGIN_AT_HOUR = "11:00";
    public static final String TEST_END_AT_HOUR = "20:00";
    public static final String TEST_WEEK_SIZE = "6 days";
    public static final int EXPECTED_WEEK_SIZE = 6;
    public static final String EXPECTED_BEGIN_AT_HOUR = "11 00";
    public static final String EXPECTED_END_AT_HOUR = "19 00";

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

    @Test
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


    @Test
    public void testWeekSize() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE);
    }

    @Test
    public void testWorkingDayDuration() throws Exception {
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR));

    }



    @Test
    public void testEventCreation() throws Exception{
        BaseNavigation.login(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.implicitWait(driver);
        schedulerPage.setAppointment("Test",3);
        schedulerPage.saveButtonClick();

        schedulerPage.managerHeader.logOut();
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Test"));
    }
}
