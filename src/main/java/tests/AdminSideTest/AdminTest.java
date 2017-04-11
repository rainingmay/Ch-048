package tests.AdminSideTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AllUsersPage;
import testDAO.UserDAO;
import tests.FunctionalTest;
import utils.BaseNavigation;


import java.util.List;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AdminTest extends FunctionalTest {

    @Test
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, "admin@hospitals.ua", "1111");
            while (true) {
                Thread.sleep(2000);
                for (int i = 1; i < allUsersPage.gecCountOfUsersInTable(); i++) {
                    List<String> actual = allUsersPage.getUserDataFromTableRow(i);
                    List<String> expected = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
                    Assert.assertEquals(actual, expected);
                }
                if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) break;
                else allUsersPage.nextPageButton.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
