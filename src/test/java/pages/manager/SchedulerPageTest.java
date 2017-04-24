package pages.manager;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.databaseutil.UserDAO;



public class SchedulerPageTest extends BaseTest {

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
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    private SchedulerPage schedulerPage;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()  {
        UserDAO.deleteAllEvents();
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
            BaseNavigation.logout();
    }


    @Test
    public void testElementPresence() {
        try{
            BrowserWrapper.waitForPage();
            schedulerPage.isPageReady();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady(),"Not all elements are on the page");
    }

    @Test
    public void testDefaultSchedulerValues(){
        BrowserWrapper.waitForPage();
        try{
            schedulerPage.checkDefaultConditionScheduler();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler(), "Scheduler values doesn't set to default");
    }


    @Test
    public void testWeekSize(){
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE, "Can't change week size");
    }

    @Test
    public void testWorkingDayDuration(){
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR), "Can't change day duration");
    }



    @Test(dataProvider = "eventCreation")
    public void testEventCreation(String actualText, String expectedText){
        schedulerPage.createAppointment(actualText);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(expectedText), "Can't create event");
    }

    @Test
    public void testEventDeletion(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.deleteEventButtonClick();
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0, "Cant edit event");
    }

    @Test
    public void testEventEdition(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.editEventText(TEST_EDITABLE_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(EXPECTED_EDITABLE_APPOINTMENT_TEXT), "Can't edit event");
    }
    @Test
    public void testEventCancel(){
        schedulerPage.nextButtonClick();
        schedulerPage.inputEvent(TEST_APPOINTMENT_TEXT);
        schedulerPage.cancelButtonClick();
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0, "Can't cancel event creation");

    }

    @Test
    public void testCreateEventDayTab() {
        schedulerPage.dayTabButtonClick();
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.dayTabButtonClick();
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(EXPECTED_APPOINTMENT_TEXT),"Can't create event at day tab");
    }

    @Test
    public void testCreateEventMonthTab(){
        schedulerPage.monthTabButtonClick();
        schedulerPage.createEventCalendar(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
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
        schedulerPage.todayButtonClick();
        Assert.assertTrue(schedulerPage.checkTodayPresence());
    }


    @Test
    public void testAlertIfNotSaved(){
        schedulerPage.nextButtonClick();
        schedulerPage.createAppointmentWithoutSave(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        boolean present = BrowserWrapper.isAlertPresent();
        BrowserWrapper.conformAlert();
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
