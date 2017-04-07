package pageObjects.manager.tests;


import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.manager.HospitalsPage;
import utilities.BaseNavigation;


/**
 * Created by radga on 07.04.2017.
 */
public class ManePageTest extends FunctionalTest {


    @Test
    public void testDoctorsPerPage() throws Exception {
       BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");

        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        hospitalsPage.selectDoctorPerPage("10");
        System.out.println(driver.findElements(By.tagName("tbody tr")).size());

        Assert.assertEquals(driver.findElements(By.tagName("tbody tr")).size(), 10);
    }


}
