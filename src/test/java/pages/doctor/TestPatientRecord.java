package pages.doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.*;


/**
 * Created by Natasha on 18.04.2017.
 */
public class TestPatientRecord extends BaseTest {
    Logger logger = LoggerFactory.getLogger(ListPatientPage.class);

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
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.getPatientsCardClick();
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        patientsCardPage.addNewRecordButtonClick();
        CreateNewRecordPage createNewRecord = new CreateNewRecordPage();
        createNewRecord.inputRecord("New complaint", "New result", "New prescription");
        Assert.assertTrue(patientsCardPage.checkRecord());
        logger.debug("Create new record");
    }
    @Test
    public void checkPatientsSearch() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.searchPatients("patient.sf@hospitals.ua");
        Assert.assertTrue(listPatientPage.checkResultSearch());
        logger.info("Test pass");
    }

    @Test
    public void testPatientsSortingByFirstName() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByFirstNameButton();
        BrowserWrapper.sleep(4);
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 3);
        Assert.assertEquals(first_patient_after_sort, "Charles");
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByLastName() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByLastNameButton();
        BrowserWrapper.sleep(3);
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 4);
        Assert.assertEquals(first_patient_after_sort, "Auginas");
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByEmail() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByEmailButton();
        BrowserWrapper.sleep(3);
        int actual = new TableParser(listPatientPage.table).getFieldFromTableRow(1, "patient:").compareToIgnoreCase(new TableParser(listPatientPage.table).getFieldFromTableRow(2, "patient:"));
        Assert.assertEquals(actual < 0, true);
        logger.info("Test pass");
    }


}
