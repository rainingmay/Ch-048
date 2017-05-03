package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"абрвал", 0}
        };
    }

    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception{
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorNotAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        DoctorSearchResultPage doctorSearchResult = header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        BaseNavigation.login("admin@hospitals.ua", "1111");
        Thread.sleep(1000);
        DoctorSearchResultPage doctorSearchResult = header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test
    public void testFindDoctorInputValidationEng() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.fillDoctorInput("ho");
        header.changeLanguageToUa();
        Thread.sleep(1000);
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test
    public void testFindDoctorInputValidationUa() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.changeLanguageToEn();
        Thread.sleep(1000);
        header.fillDoctorInput("ho");
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }
}