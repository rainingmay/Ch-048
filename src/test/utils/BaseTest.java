package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class BaseTest {

    public static final String ADMIN_LOGIN = "admin@hospitals.ua";
    public static final String ADMIN_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";

    protected WebDriver driver;

    @BeforeMethod
    public void before() {
        this.driver = BrowserWrapper.browserInitialization();

    }

//    @BeforeMethod
//    private void beforeMethod(){
//        this.driver.get("https://localhost:8443/HospitalSeeker/");
//    }

    @AfterMethod
    public void afterMethod(){
        try {
            BaseNavigation.logout(this.driver);
            BrowserWrapper.browserClose(driver);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    @AfterClass
    public void after() {
            BrowserWrapper.browserClose(this.driver);
    }
}
