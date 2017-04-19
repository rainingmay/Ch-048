package pages.doctorTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.doctor.CreateNewRecordPage;
import pages.doctor.ListPatientaPage;
import pages.doctor.PatientsCardPage;
import pages.headers.headersByRole.DoctorHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserInitializer;
import utils.BrowserWrapper;



/**
 * Created by Natasha on 18.04.2017.
 */
public class TestPatientRecord extends BaseTest {
    @BeforeMethod
    public void before() {
        this.driver = BrowserInitializer.browserInitialization();

    }


    @AfterMethod
    public void after() {
        BrowserInitializer.browserClose(this.driver);

    }


    @Test

     public void createNewRecord() throws Exception {
       WebDriverWait wait = new WebDriverWait(driver, 5);
       BaseNavigation.loginAsDoctor(driver, DOCTOR_LOGIN, DOCTOR_PASSWORD);

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
      DoctorHeader doctorHeader = new DoctorHeader(driver);
      doctorHeader.patientsButtonClick();

        ListPatientaPage listPatientaPage = new ListPatientaPage(driver);
        BrowserWrapper.sleep(3);

        listPatientaPage.getPatientsCardClick();
        BrowserWrapper.sleep(5);

        PatientsCardPage patientsCardPage = new PatientsCardPage(driver);
        patientsCardPage.addNewRecordButtonClick();
        CreateNewRecordPage createNewRecord = new CreateNewRecordPage(driver);
        createNewRecord.inputRecord("New complaint", "New result", "New prescription");

        Assert.assertTrue(patientsCardPage.checkRecord());
    }
  @Test
    public void checkPatientsSearch() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        BaseNavigation.loginAsDoctor(driver, DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader(driver);
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage(driver);
        listPatientaPage.searchPatients("patient.sf@hospitals.ua");
        BrowserWrapper.sleep(2);

        Assert.assertFalse(listPatientaPage.checkResultSearch());
    }

    @Test
    public void testPatientsSortingByFirstName() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        BaseNavigation.loginAsDoctor(driver, DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader(driver);
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage(driver);
        listPatientaPage.sortByFirstNameButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 3);

        Assert.assertEquals(firstPatientAfterSort, "Charles");
    }
    @Test
    public void testPatientsSortingByLastName() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        BaseNavigation.loginAsDoctor(driver, DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader(driver);
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage(driver);
        listPatientaPage.sortByLastNameButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 4);

        Assert.assertEquals(firstPatientAfterSort, "Auginas");
    }
    @Test
    public void testPatientsSortingByEmail() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        BaseNavigation.loginAsDoctor(driver, DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader(driver);
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage(driver);
        listPatientaPage.sortByEmailButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 2);

        Assert.assertEquals(firstPatientAfterSort, "a@gmail.com");
    }


}
