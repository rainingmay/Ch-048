package pages.adminsidetest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AddNewHospitalPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;



public class AddHospitalTest extends BaseTest {

    private static final String EMAIL = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";

    public AddHospitalTest() {
    }

    @Test
    public void addHospitalTest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, EMAIL, PASSWORD);
        BrowserWrapper.sleep(1);
        HospitalListPage hospitalListPage = new HospitalListPage(driver);
        BrowserWrapper.sleep(1);
        hospitalListPage.header.allHospitalsPage();
        BrowserWrapper.sleep(1);
        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
        addNewHospitalPage.addNewHospital("Вулиця Комарова, 32б, Чернівці, Чернівецька область, Україна", "AS КЛІНІКА ЛАЗЕРНОЇ МІКРОХІРУРГІЇ ОКА 1", "");
        BrowserWrapper.sleep(1);
        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        Assert.assertFalse(hospitalsCountOfRow == hospitalsCountOfRowAfterAdding);
    }

}
