package pages.adminsidetest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AddNewHospitalPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BaseTest;


public class AddHospitalTest extends BaseTest {

    private static final String EMAIL = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";
    private static final String ADMIN_HOME_PAGE_XPATH_IDENTIFICATION = "/html/body/section/div[1]/h1";
    private static final String ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION = "/html/body/section/div/div/div/div[1]/div[1]/a[1]";
    private static final String ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION = "//*[@id=\"image-uploaded\"]";

    public AddHospitalTest() {
    }

    @Test
    public void addHospitalTest() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        BaseNavigation.loginAsAdmin(driver, EMAIL, PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADMIN_HOME_PAGE_XPATH_IDENTIFICATION)));
        HospitalListPage hospitalListPage = new HospitalListPage(driver);
        //  hospitalListPage.header.allHospitalsPage();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION)));
        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION)));
        addNewHospitalPage.addNewHospital("вул. Мусоргського, 8, Чернівці, Чернівецька область, Україна", "МІСЬКА СТАНЦІЯ ШВИДКОЇ МЕДИЧНОЇ ДОПОМОГИ", "");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION)));
        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);
        Assert.assertFalse(hospitalsCountOfRow == hospitalsCountOfRowAfterAdding);
    }

}
