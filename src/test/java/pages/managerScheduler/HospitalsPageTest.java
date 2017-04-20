package pages.managerScheduler;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.manager.HospitalsPage;
import utils.BaseNavigation;
import utils.BaseTest;

import java.util.List;



public class HospitalsPageTest extends BaseTest {
   // private HospitalsPage hospitalsPage = new HospitalsPage(driver);

    @Test
    public void testAllElementPresence(){
    }


    @Test
    public void testDoctorsPerPage() throws Exception {
       BaseNavigation.login("manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage();

        hospitalsPage.selectDoctorPerPage("10");


       Assert.assertEquals(hospitalsPage.getNumberOfRows(), 10);
    }

    @Test
    public void testSpecializationSelector() throws Exception{
        BaseNavigation.login("manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage();
        hospitalsPage.selectSpecialization("Neurologist");
        hospitalsPage.searchButtonClick();
        Thread.sleep(1000);
        List<String > result =  hospitalsPage.getCoulumn("specialization");

        Assert.assertTrue(result.stream().allMatch(i -> i.equals("Neurologist")));
    }




}
