package pages.allUsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.allUsers.DoctorSearchResult;
import pages.headers.BaseHeader;
import utils.BaseTest;


import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hou", 1},
                {"абрвал", 0}
        };
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctor(String searchWord, int expected) throws Exception {
        BaseHeader header = new BaseHeader();
        DoctorSearchResult doctorSearchResult = header.findDoctor(searchWord);
        Thread.sleep(10000);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test
    public void testFindDoctorInputValidation() throws Exception {
        BaseHeader header = new BaseHeader();
        header.fillDoctorInput("ho");
        assertEquals(header.getDoctorSearchError().getText(), "Please enter at least 3 symbols");
    }
}