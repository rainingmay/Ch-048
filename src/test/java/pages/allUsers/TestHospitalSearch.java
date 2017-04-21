package pages.allUsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.BaseHeader;
import utils.BaseTest;

import static org.testng.Assert.assertEquals;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"поліклініка", 1},
                {"hosp", 3},
                {"абрвал", 0}
        };
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospital(String searchWord, int expected) throws Exception {
        BaseHeader header = new BaseHeader(driver);
        HospitalSearchResultPage hospitalSearchResult = header.findHospital(searchWord);
        Thread.sleep(10000);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test
    public void testFindHospitalInputValidation() throws Exception {
        BaseHeader header = new BaseHeader(driver);
        header.fillHospitalInput("ho");
        assertEquals(header.getHospitalSearchError().getText(), "Please enter at least 3 symbols");
    }
}
