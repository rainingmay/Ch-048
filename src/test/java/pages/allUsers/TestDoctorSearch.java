package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {
    public static final String DOCTOR_INPUT = "ho";
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
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
        BaseNavigation.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.sleep(1);
        DoctorSearchResultPage doctorSearchResult = header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.changeLanguageToUa();
        BrowserWrapper.sleep(1);
        header.fillDoctorInput(DOCTOR_INPUT);
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        header.changeLanguageToEn();
        BrowserWrapper.sleep(1);
        header.fillDoctorInput(DOCTOR_INPUT);
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }


    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"абрвал", 0}
        };
    }
}