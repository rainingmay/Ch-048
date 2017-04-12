package pages.adminsidetest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AddNewHospitalPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;

public class AddHospitalTest extends BaseTest {

    public AddHospitalTest() {
    }

    @Test
    public void addHospitalTest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, "admin@hospitals.ua", "1111");
        HospitalListPage hospitalListPage = new HospitalListPage(driver);
        hospitalListPage.header.allHospitalsPage();
        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
        addNewHospitalPage.addNewHospital("Вулиця Руська, 207а, Чернівці, Чернівецька область, Україна", "Дитяча обласна поліклініка №1", "Дитяча");
        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        Assert.assertEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);
    }

}
