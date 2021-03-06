package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.*;
import utils.BrowserWrapper;



public class AddHospitalTest extends BaseTest {

    private static final String EMAIL = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";
    private static final String ADMIN_HOME_PAGE_XPATH_IDENTIFICATION = "//*[@id=\"searchButton\"]";
    private static final String ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION = "/html/body/section/div/div/div/div[1]/div[1]/a[1]";
    private static final String ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION = "//*[@id=\"image-uploaded\"]";
    private static final String ALL_USERS_PAGE_CSS_IDENTIFICATION = "#searchButton";


    @BeforeMethod
    public void before() {
        driver = BrowserWrapper.browserInitialization();
    }


    @Test(dataProvider = "validHospitalAddress")
    public void addNewHospitalWithValidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) throws Exception {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, EMAIL, PASSWORD);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADMIN_HOME_PAGE_XPATH_IDENTIFICATION));

            HospitalListPage hospitalListPage = new HospitalListPage(driver);
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table: " + hospitalsCountOfRow);
            hospitalListPage.submitAddNewHospital();
            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION));

            //addNewHospitalPage.pushAddPhotoButton();
            //addNewHospitalPage.addNewHospitalPhoto();
            addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

            Assert.assertNotEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "loginDataForDeleteHospital")
    public void deleteHospitalTest(String login, String password) throws Exception {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitUntilElementClickableByLocator(By.cssSelector(ALL_USERS_PAGE_CSS_IDENTIFICATION));

            HospitalListPage hospitalListPage = new HospitalListPage(driver);
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row: " + hospitalCountOfRow);
            hospitalListPage = hospitalListPage.deleteHospital(11);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));
            int hospitalCountOfRowAfterDelete = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row after delete: " + hospitalCountOfRowAfterDelete);

            Assert.assertNotEquals(hospitalCountOfRow, hospitalCountOfRowAfterDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "editHospitalBuildingAndStreet")
    public void editHospitalTest(String building, String street) throws Exception {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, EMAIL, PASSWORD);
            BrowserWrapper.waitUntilElementClickableByLocator(By.cssSelector(ALL_USERS_PAGE_CSS_IDENTIFICATION));

            HospitalListPage hospitalListPage = new HospitalListPage(driver);
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            String actual = hospitalListPage.getHospitalDataFromTableRow(15).toString();
            hospitalListPage.editButton(15);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADD_HOSPITAL_PAGE_XPATH_IDENTIFICATION));

            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
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

        WebDriverWait wait = new WebDriverWait(driver, 10);

        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, EMAIL, PASSWORD);
        BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ADMIN_HOME_PAGE_XPATH_IDENTIFICATION));

        HospitalListPage hospitalListPage = new HospitalListPage(driver);
        hospitalListPage.header.goToAllHospitalsPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage(driver);
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
                {"Вулиця Руська, 207а, Чернівці, Чернівецька, Україна" , "Дитяча обласна поліклініка №1", ""},
                {"Вул. Мусоргського, 8, Чернівці, Чернівецька, Україна", "МІСЬКА СТАНЦІЯ ШВИДКОЇ МЕДИЧНОЇ ДОПОМОГИ", ""}
        };
    }

    @DataProvider
    public Object [] [] loginDataForDeleteHospital() {
            return new Object [] [] {
                    {"admin@hospitals.ua", "1111"}
            };
    }

    @DataProvider
    public Object [] [] editHospitalBuildingAndStreet() {
        return new Object[][] {
                {"2", "Вулиця Руська"},
                {"4", "Вулиця Лесі Українки"},
                {"6", "Вулиця Українська"}
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
        try {
            BaseNavigation.logout(driver);
            //BrowserWrapper.browserClose(this.driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
    }

}
