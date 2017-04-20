package utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

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


    @BeforeClass
    public void before() throws InterruptedException {
        this.driver = BrowserInitializer.browserInitialization();
    }


    @AfterClass
    public void after() {
        //BrowserInitializer.browserClose(this.driver);
    }

}


