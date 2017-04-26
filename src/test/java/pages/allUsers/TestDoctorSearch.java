package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BaseNavigation;
import utils.BaseTest;
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

    private Properties properties;


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
        DoctorSearchResult doctorSearchResult = header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        BaseNavigation.login("admin@hospitals.ua", "1111");
        Thread.sleep(2000);
        DoctorSearchResult doctorSearchResult = header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test
    public void testFindDoctorInputValidation() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.fillDoctorInput("ho");
        header.changeLanguageToUa();
        header.changeLanguageToEn();
        checkLanguageAndLoadProperties(header);
        assertEquals(header.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test
    public void testFindDoctorInputValidationUa() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.changeLanguageToUa();
        header.fillDoctorInput("ho");
        assertEquals(header.getDoctorSearchError().getText(), "Please enter at least 3 symbols");
    }

    private void checkLanguageAndLoadProperties(BaseHeader header){
        properties = new Properties();
        try {
            if (header.getChangeLanguageIco().getAttribute("src").endsWith("/en.png")) {
                properties.load(new InputStreamReader(
                        new FileInputStream("src/main/resources/languageEng.properties"), "UTF-8"));
            } else {
                properties.load(new InputStreamReader(
                        new FileInputStream("src/main/resources/languageUa.properties"), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}