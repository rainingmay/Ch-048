package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.allUsers.HospitalSearchResult;
import pages.headers.BaseHeader;
import utils.BaseTest;
import utils.BrowserWrapper;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {
    private String urlHospitalSearch = "hospitals?q=";
    //extends BaseTest
    private StringBuffer verificationErrors = new StringBuffer();

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                { "поліклініка", 1 },
                { "hosp", 3 },
                { "абрвал", 0 },
        };
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(dataProvider="SearchProvider")
    public void testFindHospital(String searchWord, int expected) throws Exception {
        BaseHeader header = new BaseHeader(driver);
        HospitalSearchResult hospitalSearchResult = header.findHospital(searchWord);
        driver.get("https://localhost:8443/HospitalSeeker/" + urlHospitalSearch + searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.close();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
