package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"поліклініка", 2},
                {"hosp", 3},
                {"абрвал", 0}
        };
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception{
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalNotAuthorizedUser(String searchWord, int expected) {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        HospitalSearchResultPage hospitalSearchResult = hospitalSeekerHomePage.header.findHospital(searchWord);
        BrowserWrapper.sleep(1);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalAuthorizedUser(String searchWord, int expected) throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin("admin@hospitals.ua", "1111");
        BrowserWrapper.sleep(1);
        HospitalSearchResultPage hospitalSearchResult = allUsersPage.header.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationUa() throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        hospitalSeekerHomePage.header.changeLanguageToUa();
        BrowserWrapper.sleep(1);
        hospitalSeekerHomePage.header.fillHospitalInput("ho");
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.header);
        assertEquals(hospitalSeekerHomePage.header.getHospitalSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationEng() throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        hospitalSeekerHomePage.header.changeLanguageToEn();
        BrowserWrapper.sleep(1);
        hospitalSeekerHomePage.header.fillHospitalInput("ho");
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.header);
        assertEquals(hospitalSeekerHomePage.header.getHospitalSearchError().getText(), properties.getProperty("lineToShort"));
    }
}
