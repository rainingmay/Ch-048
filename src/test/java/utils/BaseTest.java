package java.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Driver;

public class BaseTest {

    public static final String ADMIN_LOGIN = "admin@hospitals.ua";
    public static final String ADMIN_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";


    @BeforeClass
    public void before(){
        Driver.initialization();
    }


    @AfterClass
    public void after() {
        Driver.close();
    }

}


