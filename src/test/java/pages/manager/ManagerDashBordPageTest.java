package pages.manager;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;

import java.util.List;


public class ManagerDashBordPageTest extends BaseTest {
   // private ManagerDashBordPage hospitalsPage = new ManagerDashBordPage(driver);

    @Test
    public void testAllElementPresence(){
    }


    @Test
    public void testDoctorsPerPage() throws Exception {
       BaseNavigation.login("manager.jh@hospitals.ua", "1111");
        ManagerDashBordPage managerDashBordPage = new ManagerDashBordPage();

        managerDashBordPage.selectDoctorPerPage("10");


       Assert.assertEquals(managerDashBordPage.getNumberOfRows(), 10);
    }

    @Test
    public void testSpecializationSelector() throws Exception{
        BaseNavigation.login("manager.jh@hospitals.ua", "1111");
        ManagerDashBordPage managerDashBordPage = new ManagerDashBordPage();
        managerDashBordPage.selectSpecialization("Neurologist");
        managerDashBordPage.searchButtonClick();
        Thread.sleep(1000);
        List<String > result =  managerDashBordPage.getCoulumn("specialization");

        Assert.assertTrue(result.stream().allMatch(i -> i.equals("Neurologist")));
    }




}
