package pages.doctor;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.allUsers.HospitalSeekerHomePage;
import pages.headers.headersByRole.DoctorHeader;
import pages.manager.SchedulerPage;
import utils.*;


/**
 * Created by Natasha on 18.04.2017.
 */
public class TestPatientRecord extends BaseTest {
    Logger logger = LoggerFactory.getLogger(ListPatientaPage.class);

    @BeforeMethod
    public void beforeMethod() throws InterruptedException{
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN,DOCTOR_PASSWORD);
        logger.info("Test is initialized");
    }

    @AfterMethod
    public void after() {
        DriverInitializer.instance().manage().deleteAllCookies();
        BaseNavigation.logout();
        // DriverInitializer.close();
        logger.info("Test is over");
    }


    @Test

    public void createNewRecord() throws Exception {
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.header.patientsButtonClick();
        listPatientaPage.getPatientsCardClick();
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        patientsCardPage.addNewRecordButtonClick();
        CreateNewRecordPage createNewRecord = new CreateNewRecordPage();
        createNewRecord.inputRecord("New complaint", "New result", "New prescription");
        Assert.assertTrue(patientsCardPage.checkRecord());
        logger.debug("Create new record");
    }
    @Test
    public void checkPatientsSearch() throws Exception {
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.header.patientsButtonClick();
        listPatientaPage.searchPatients("patient.sf@hospitals.ua");
        Assert.assertTrue(listPatientaPage.checkResultSearch());
        logger.info("Test pass");
    }

    @Test
    public void testPatientsSortingByFirstName() throws Exception {
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.header.patientsButtonClick();
        listPatientaPage.sortByFirstNameButton();
        BrowserWrapper.sleep(4);
        String first_patient_after_sort = listPatientaPage.getDataFromTable(1, 3);
        Assert.assertEquals(first_patient_after_sort, "Charles");
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByLastName() throws Exception {
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.header.patientsButtonClick();
        listPatientaPage.sortByLastNameButton();
        BrowserWrapper.sleep(3);
        String first_patient_after_sort = listPatientaPage.getDataFromTable(1, 4);
        Assert.assertEquals(first_patient_after_sort, "Auginas");
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByEmail() throws Exception {
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.header.patientsButtonClick();
        listPatientaPage.sortByEmailButton();
        BrowserWrapper.sleep(3);
        int actual = new TableParser(listPatientaPage.table).getFieldFromTableRow(1, "patient:").compareToIgnoreCase(new TableParser(listPatientaPage.table).getFieldFromTableRow(2, "patient:"));
        Assert.assertEquals(actual < 0, true);
        logger.info("Test pass");
    }


}
