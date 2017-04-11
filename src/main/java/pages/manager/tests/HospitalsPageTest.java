package pages.manager.tests;


import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.manager.HospitalsPage;
import utils.BaseNavigation;


import java.util.List;


/**
 * Created by radga on 07.04.2017.
 */
public class HospitalsPageTest extends FunctionalTest {
   // private HospitalsPage hospitalsPage = new HospitalsPage(driver);

    @Test
    public void testAllElementPresence(){
    }


    @Test
    public void testDoctorsPerPage() throws Exception {
       BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);

        hospitalsPage.selectDoctorPerPage("10");


       Assert.assertEquals(hospitalsPage.getNumberOfRows(), 10);
    }

    @Test
    public void testSpecializationSelector() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        hospitalsPage.selectSpecialization("Neurologist");
        hospitalsPage.searchButtonClick();
        Thread.sleep(1000);
        List<String > result =  hospitalsPage.getCoulumn("specialization");

        Assert.assertTrue(result.stream().allMatch(i -> i.equals("Neurologist")));
    }




}
