package pageObjects.manager.tests;


import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.headers.headersByRole.ManagerHeader;
import pageObjects.manager.HospitalsPage;
import utilities.BaseNavigation;

import java.util.List;


/**
 * Created by radga on 07.04.2017.
 */
public class HospitalsPageTest extends FunctionalTest {
    private HospitalsPage hospitalsPage = new HospitalsPage(driver);

    @Test
    public void testAllElementPresence(){
    }


    @Test
    public void testDoctorsPerPage() throws Exception {
       BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");


        hospitalsPage.selectDoctorPerPage("10");


       Assert.assertEquals(hospitalsPage.getNumberOfRows(), 10);
    }

    @Test
    public void testSpecializationSelector() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");

        hospitalsPage.selectSpecialization("Neurologist");
        hospitalsPage.searchButtonClick();
        List<String > result =  hospitalsPage.getCoulumn("specialization");
        System.out.println(result.size());
        System.out.println(result.stream().allMatch(i -> i.equals("Neurologist")));
        Assert.assertTrue(result.stream().allMatch(i -> i.equals("Neurologist")));
    }




}
