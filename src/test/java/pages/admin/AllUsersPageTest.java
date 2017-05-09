package pages.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;
import utils.databaseutil.DatabaseOperations;
import utils.databaseutil.UserDAO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest {

    private AllUsersPage allUsersPage;

    Logger logger = LoggerFactory.getLogger(AllUsersPage.class);


    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
        DatabaseOperations.restore("hospital_database.backup");
        allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        logger.info("Test is initialized");
    }

    @AfterMethod
    public void afterMethod() {
        BaseNavigation.logout();
        logger.info("Test is close");
        DriverInitializer.close();
    }

    @Test
    public void enableUsersViewTest() {
        allUsersPage = allUsersPage.showEnableUsers();
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromFirstTableRow("@email"));
        Assert.assertTrue(actual);
        logger.info("Test pass");
    }


    @Test
    public void disableUsersViewTest() {
        allUsersPage = allUsersPage.showDisableUsers();
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromFirstTableRow("@email"));
        Assert.assertFalse(actual);
        logger.info("Test pass");
    }


    @Test
    public void viewWindowTest() {
        List<String> actual = allUsersPage.getFirstUserDataFromInfoWindow();
        int ID_INDEX = 0;
        int EMAIL_INDEX = 1;
        List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(EMAIL_INDEX));
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{allInfo.get(ID_INDEX), allInfo.get(EMAIL_INDEX), "true"});
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test(dataProvider = "roles")
    public void changeRoleTest(String role) {
        String expected = role;
        allUsersPage = allUsersPage.changeRoleInEditWindow(role);
        String actual = new TableParser(allUsersPage.table).getFieldFromFirstTableRow("role");
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test(dataProvider = "count")
    public void changeCountOfUsersOnPageTest(String count) {
        int expected = Integer.parseInt(count);
        allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
        int actual = allUsersPage.getCountOfUsersInTable();
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test(dataProvider = "roles")
    public void searchByRoleTest(String role) {
        String expected = role;
        allUsersPage = allUsersPage.changeRole(expected);
        String actual = new TableParser(allUsersPage.table).getFieldFromFirstTableRow("role");
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test(dataProvider = "searchParams")
    public void searchTest(String role, String valueOfField, String count) {
        allUsersPage = allUsersPage.search(Integer.parseInt(count), role, "firstName", valueOfField);
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{valueOfField, role});
        TableParser tableParser = new TableParser(allUsersPage.table);
        List<String> actual = new LinkedList<>();
        if (tableParser.getFieldFromFirstTableRow("@email").contains(valueOfField)) actual.add(valueOfField);
        else actual.add("noSame");
        actual.add(tableParser.getFieldFromFirstTableRow("role"));
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test
    public void nextPageButtonTest() {
        AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
        Assert.assertNotEquals(allUsersPage, allUsersPage1);
        logger.info("Test pass");
    }


    @Test(dataProvider = "roles")
    public void sortByEmailTest(String role) {
        allUsersPage.changeRole(role);
        allUsersPage.searchButton.click();
        BrowserWrapper.sleep(2);
        allUsersPage = allUsersPage.clickSortByEmail();
        int actual = new TableParser(allUsersPage.table).getFieldFromFirstTableRow("@email").compareToIgnoreCase(new TableParser(allUsersPage.table).getFieldFromTableRow(2, "@email"));
        boolean isSortCorrect = actual < 0;
        Assert.assertTrue(isSortCorrect);
        logger.info("Test pass");
    }


    @DataProvider
    public Object[][] roles() {
        return new Object[][]{
                {"MANAGER"}
        };
    }

    @DataProvider
    public Object[][] count() {
        return new Object[][]{
                {"20"}
        };
    }

    @DataProvider
    public Object[][] searchParams() {
        return new Object[][]{
                {"MANAGER", "a", "20"}
        };
    }

}
