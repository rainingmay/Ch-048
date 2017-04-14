package pages.AdminSideTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AddNewHospitalPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;

public class AddHospitalTest extends BaseTest {

    private static final String email = "admin@hospitals.ua";
    private static final String password = "1111";



 /*   @Test
    public void addHospitalTest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, email, password);
        BrowserWrapper.sleep(2);
        HospitalListPage hospitalListPage = new HospitalListPage(driver);
        BrowserWrapper.sleep(2);
        hospitalListPage.header.allHospitalsPage();
        BrowserWrapper.sleep(2);
        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        BrowserWrapper.sleep(2);
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
        addNewHospitalPage.addNewHospital("Вулиця Головна, 129, Чернівці, Чернівецька область, Україна", "МІСЬКИЙ КЛІНІЧНИЙ ПОЛОГОВИЙ БУДИНОК № 1", "");
        BrowserWrapper.sleep(2);
        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        Assert.assertFalse(hospitalsCountOfRow == hospitalsCountOfRowAfterAdding);
    }*/

}
