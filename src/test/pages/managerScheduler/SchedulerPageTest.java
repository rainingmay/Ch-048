package pages.managerScheduler;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
    public static final String TEST_APPOINTMENT_TEXT = "Test event";
    public static final String EXPECTED_APPOINTMENT_TEXT = "Test event";
    public static final String TEST_EDITABLE_APPOINTMENT_TEXT = "Another text";
    public static final String EXPECTED_EDITABLE_APPOINTMENT_TEXT = "Another text";
    private SchedulerPage schedulerPage;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()  {
        UserDAO.deleteAllEvents();
        BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
            BaseNavigation.logout(driver);
    }


    @Test
    public void testElementPresence() {
        try{
            BrowserWrapper.waitForPage(driver);
            schedulerPage.isPageReady();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady());
    }

    @Test
    public void testDefaultSchedulerValues(){
        BrowserWrapper.waitForPage(driver);
        try{
            schedulerPage.checkDefaultConditionScheduler();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler());
    }


    @Test
    public void testWeekSize(){
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE);
    }

    @Test
    public void testWorkingDayDuration(){
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR));
    }



    @Test(dataProvider = "eventCreation")
    public void testEventCreation(String actualText, String expectedText){
        schedulerPage.nextButtonClick();
        schedulerPage.setAppointment(actualText);
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(expectedText));
    }

    @Test
    public void testEventDeletion(){
        schedulerPage.nextButtonClick();
        schedulerPage.setAppointment(TEST_APPOINTMENT_TEXT);
        schedulerPage.saveButtonClick();
        BrowserWrapper.refreshPage(driver);
        schedulerPage.nextButtonClick();
        schedulerPage.callEventContext();
        schedulerPage.deleteEventButtonClick();
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0);
    }

    @Test
    public void testEventEdition(){
        schedulerPage.nextButtonClick();
        schedulerPage.setAppointment(TEST_APPOINTMENT_TEXT);
        schedulerPage.saveButtonClick();
        BrowserWrapper.refreshPage(driver);
        schedulerPage.nextButtonClick();
        schedulerPage.callEventContext();
        schedulerPage.editEventText(TEST_EDITABLE_APPOINTMENT_TEXT);
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(EXPECTED_EDITABLE_APPOINTMENT_TEXT));
    }
    @Test
    public void testEventCancel(){
        schedulerPage.nextButtonClick();
        schedulerPage.inputEvent(TEST_APPOINTMENT_TEXT);
        schedulerPage.cancelButtonClick();
        schedulerPage.saveButtonClick();
        BrowserWrapper.refreshPage(driver);
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0);

    }

    @Test(dataProvider = "eventCreation")
    public void testCreateEventDayTab(String actualText, String expectedText) {
        schedulerPage.dayTabButtonClick();
        schedulerPage.nextDayClick();
        schedulerPage.setAppointment(actualText);
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.dayTabButtonClick();
        schedulerPage.nextDayClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(expectedText));
    }

    @Test
    public void testCreateEventMonthTab(){
        schedulerPage.monthTabButtonClick();
        schedulerPage.nextButtonClick();
        schedulerPage.createEventCalendar(TEST_APPOINTMENT_TEXT);
        schedulerPage.saveButtonClick();
        BaseNavigation.logout(driver);
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager(driver, MANAGER_LOGIN, MANAGER_PASSWORD);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.monthTabButtonClick();
        schedulerPage.nextButtonClick();
        System.out.println(schedulerPage.getEventsCalendar().size() > 0);
        Assert.assertTrue( schedulerPage.getEventsCalendar().size() > 0 && schedulerPage.getEventsCalendar().get(0).contains(EXPECTED_APPOINTMENT_TEXT));

    }


    @Test
    public void testMiniCalendar(){
        schedulerPage.miniCalendarButtonClick();
        Assert.assertTrue(schedulerPage.checkMiniCalendarVisibility());
    }

    @Test
    public void testTodayButton(){
        schedulerPage.nextButtonClick();
        schedulerPage.nextButtonClick();
        schedulerPage.nextButtonClick();
        schedulerPage.todayButtonClick();
        Assert.assertTrue(schedulerPage.checkTodayPresence());
    }


    @Test
    public void testAlertIfNotSaved(){
        schedulerPage.nextButtonClick();
        schedulerPage.setAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage(driver);
        boolean present = BrowserWrapper.isAlertPresent(driver);
        BrowserWrapper.conformAlert(driver);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(present);
    }

    @DataProvider(name = "eventCreation")
    public static Object[][] getData()
    {
        return  new Object[][]{
                {"Test text", "Test text"},
                {" ", ""},
                {"Тестовий текст", "Тестовий текст"}};
    }
}
