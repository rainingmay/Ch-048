package pages.admin;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.BaseHeader;
import utils.*;
import utils.databaseutil.DatabaseOperations;
import utils.databaseutil.UserDAO;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.*;


/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest{

    private AllUsersPage allUsersPage;

    Logger logger = LoggerFactory.getLogger(AllUsersPage.class);

    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
        DatabaseOperations.restore("hospital.backup");
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
    public void localizationTest() throws IOException {
        checkLanguageAndLoadProperties(allUsersPage.header);

        List<String> actual = new ArrayList<>();
        actual.add(allUsersPage.header.homeButton.getText());
        actual.add(allUsersPage.header.actions.getText());
        actual.add(allUsersPage.usersPerPageLabel.getText());

        List<String> expected = new ArrayList<>();
        expected.add(properties.getProperty("header.menu.home"));
        expected.add(properties.getProperty("admin.hospital.list.table.actions"));
        expected.add(properties.getProperty("admin.dashboard.users.show.users"));

        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test
    public void enableUsersViewTest() {
        allUsersPage = allUsersPage.showEnableUsers();
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, true);
        logger.info("Test pass");
    }


    @Test
    public void disableUsersViewTest() {
        allUsersPage = allUsersPage.showDisableUsers();
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, false);
        logger.info("Test pass");
    }


    @Test
    public void viewWindowTest() {
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        List<String> actual = allUsersPage.getUserDataFromInfoWindow(rowNumber);
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
        int rowNumber = 1;
        allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, role);
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
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
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test(dataProvider = "searchParams")
    public void searchTest(String role, String valueOfField, String count) {
        allUsersPage = allUsersPage.search(Integer.parseInt(count), role, "firstName", valueOfField);
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{valueOfField, role});
        TableParser tableParser = new TableParser(allUsersPage.table);
        List<String> actual = new LinkedList<>();
        if (tableParser.getFieldFromTableRow(rowNumber, "@email").contains(valueOfField)) actual.add(valueOfField);
        else actual.add("noSame");
        actual.add(tableParser.getFieldFromTableRow(rowNumber, "role"));
        Assert.assertEquals(actual, expected);
        logger.info("Test pass");
    }


    @Test
    public void nextPageButtonTest() {
        AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
        BrowserWrapper.sleep(2);
        Assert.assertNotEquals(allUsersPage, allUsersPage1);
        logger.info("Test pass");
    }


    @Test(dataProvider = "roles")
    public void sortByEmailTest(String role) {
        allUsersPage.changeRole(role);
        allUsersPage.searchButton.click();
        BrowserWrapper.sleep(2);
        allUsersPage = allUsersPage.clickSortByEmail();
        BrowserWrapper.sleep(2);
        int actual = new TableParser(allUsersPage.table).getFieldFromTableRow(1, "@email").compareToIgnoreCase(new TableParser(allUsersPage.table).getFieldFromTableRow(2, "@email"));
        Assert.assertEquals(actual < 0, true);
        logger.info("Test pass");
    }


    @DataProvider
    public Object[][] roles() {
        return new Object[][]{
                {"MANAGER"}//,
                //{"PATIENT"}
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


    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max - 1) + 1;
    }


}
