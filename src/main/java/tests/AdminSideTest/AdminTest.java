package tests.AdminSideTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.admin.AllUsersPage;
import testDAO.UserDAO;
import tests.FunctionalTest;
import utilities.BaseNavigation;

import java.util.List;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AdminTest extends FunctionalTest{

    @Test
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, "admin@hospitals.ua", "1111");
            //for (int i = 1; i < allUsersPage.gecCountOfUsersInTable(); i++) {
                List<String> actual = allUsersPage.getUserDataFromTableRow(1);
                List<String> expected = UserDAO.getUserFromDatabaseByEmail("a@a.co");
                Assert.assertEquals(actual, expected);
            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
