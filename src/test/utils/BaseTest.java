package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class BaseTest {

    /*public WebElementWrapper browser;
    public DataSetUtils dataSetUtils;
    public static String language = LocalizationConfig.setLanguage();

    public WebElementWrapper getWrapper() {
        return browser;
    }

    public static final String HOME_URL = "https://localhost:8443/HospitalSeeker/";
    public static final String DOCTOR_PAGE_URL = HOME_URL.concat("doctor/6/manage");
    public static final String ADMIN_DASHBOARD_URL = HOME_URL.concat("admin/users?status=true");
    public static final String ADDING_NEW_HOSPITAL_URL = HOME_URL.concat("admin/map/new");
    public static final String ADDING_NEW_USER_URL = HOME_URL.concat("admin/newUser");
    public static final String HOSPITAL_LIST_URL = HOME_URL.concat("admin/map/listhospitals");
    public static final String HOSPITALS_URL = HOME_URL.concat("hospitals");
    public static final String EDIT_HOSPITALS_MANAGERS_URL = HOME_URL.concat("editHospitalsManagers");
    public static final String CHECK_HOSPITALS_LIST_URL = HOME_URL.concat("admin/map/validate");
    public static final String PATIENTS_LIST_URL = HOME_URL.concat("patients");
    public static final String LOGIN_URL = HOME_URL.concat("login");
    public static final String CONFIGURE_TOKENS_URL = HOME_URL.concat("admin/configureToken");
    public static final String REGISTER_URL = HOME_URL.concat("registration");
    public static final String FIND_URL = HOME_URL.concat("mapsearch");
    public static final String VALIDATE_URL = HOME_URL.concat("admin/map/validate");
*/

    public static final String ADMIN_LOGIN = "admin@hospitals.ua";
    public static final String ADMIN_PASSWORD = "1111";
    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";
    public static final String DOCTOR_GH_LOGIN = "doctor.gh@hospitals.ua";
    public static final String DOCTOR_GH_PASSWORD = "1111";
    public static final String DOCTOR_LHC_LOGIN = "doctor.lhc@hospitals.ua";
    public static final String DOCTOR_LHC_PASSWORD = "1111";
    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";
    public static final String SECOND_PATIENT_LOGIN = "patient.in@hospitals.ua";
    public static final String SECOND_PATIENT_PASSWORD = "1111";
/*
    @BeforeMethod
    public void before() {
        dataSetUtils = new DataSetUtils();
        dataSetUtils.importDataSet();
        browser = new BrowserWrapper(BrowserInitialization.initialize());
        browser.browserMaximize();
    }

    @AfterMethod
    public void after() {
        browser.getDriver().quit();
        dataSetUtils.databaseTearDown();
    }*/

    protected WebDriver driver;

    @BeforeTest
    public void before() {
        driver = BrowserWrapper.browserInitialization();
    }

    @AfterMethod
    public void afterMethod(){
        try {
            BaseNavigation.logout(this.driver);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @AfterTest
    public void after() {
            BrowserWrapper.browserClose(this.driver);

    }
}
