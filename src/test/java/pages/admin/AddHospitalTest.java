package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.Driver;



public class AddHospitalTest extends BaseTest {

    private static final String EMAIL = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";
    private static final String ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION = "/html/body/section/div/div/div/div[1]/div[1]/a[1]";
    private static final String ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION = "//*[@id=\"image-uploaded\"]";
    private static final String ALL_USERS_PAGE_ID_IDENTIFICATION = "#searchButton";


    @BeforeMethod(alwaysRun = true)
    public void before() {
        BaseNavigation.loginAsAdmin(EMAIL, PASSWORD);
        BrowserWrapper.waitUntilElementIsPresent(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));

    }


    @Test(dataProvider = "validHospitalAddress")
    public void addNewHospitalWithValidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) {

            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));
            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table: " + hospitalsCountOfRow);
            hospitalListPage.submitAddNewHospital();
            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION));

            //addNewHospitalPage.pushAddPhotoButton();
            //addNewHospitalPage.addNewHospitalPhoto();
            addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

            Assert.assertNotEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);
    }

    @Test(dataProvider = "loginDataForDeleteHospital")
    public void deleteHospitalTest(String login, String password, int hospitalCountForDelete) throws Exception {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(login, password);
            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row: " + hospitalCountOfRow);
            hospitalListPage.deleteHospital(hospitalCountForDelete);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));
            int hospitalCountOfRowAfterDelete = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row after delete: " + hospitalCountOfRowAfterDelete);

            Assert.assertNotEquals(hospitalCountOfRow, hospitalCountOfRowAfterDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "editHospitalBuildingAndStreet")
    public void editHospitalTest(String building, String street, int hospitalCountForEdit) throws Exception {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(EMAIL, PASSWORD);
            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            String actual = hospitalListPage.getHospitalDataFromTableRow(hospitalCountOfRow - 2).toString();
            hospitalListPage.editButton(hospitalCountForEdit);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION));

            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
            addNewHospitalPage.changeBuilding(building);
            addNewHospitalPage.changeStreet(street);
            addNewHospitalPage.pushSaveButton();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            String expected = hospitalListPage.getHospitalDataFromTableRow(15).toString();

            Assert.assertNotEquals(actual, expected);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test(dataProvider = "invalidHospitalAddress")
    public void addNewHospitalWithInvalidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) throws Exception {

        WebDriverWait wait = new WebDriverWait(Driver.instance(), 10);

        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(EMAIL, PASSWORD);
        BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));

        HospitalListPage hospitalListPage = new HospitalListPage();
        hospitalListPage.header.goToAllHospitalsPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION));

        addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
        BrowserWrapper.sleep(3);
        hospitalListPage.header.goToAllHospitalsPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

        Assert.assertEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);
    }

    @DataProvider
    public static Object [] [] validHospitalAddress() {
        return new  Object [] [] {
                {"Вул. Мусоргського, 8, Чернівці, Чернівецька, Україна", "МІСЬКА СТАНЦІЯ ШВИДКОЇ МЕДИЧНОЇ ДОПОМОГИ", ""}
        };
    }

    @DataProvider
    public Object [] [] loginDataForDeleteHospital() {
            return new Object [] [] {
                    {"admin@hospitals.ua", "1111", 10}
            };
    }

    @DataProvider
    public Object [] [] editHospitalBuildingAndStreet() {
        return new Object[][] {
                {"2", "Вулиця Руська", 8},
                {"4", "Вулиця Лесі Українки", 9}
        };
    }

    @DataProvider
    public Object [] [] invalidHospitalAddress() {
        return new Object[][] {
                {"вул. Березовська, 14, смт Глибока, Чернівецька область, Україна", "Лікарня №2", ""},
        };
    }

    @AfterMethod
    public void after() {

        BaseNavigation.logout();
    }
}
