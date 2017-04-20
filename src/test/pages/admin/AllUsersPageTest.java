package pages.admin;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;
import utils.databaseutil.UserDAO;

import java.util.*;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest {

    @BeforeMethod
    public void before() {
        this.driver = BrowserInitializer.browserInitialization();

    }

    @AfterMethod
    public void after() {
        try {
            BaseNavigation.logout(this.driver);
            BrowserInitializer.browserClose(this.driver);
        } catch (Exception e) {
            BrowserInitializer.browserClose(this.driver);
        }
    }



    @Test(dataProvider = "loginData")
    public void enableUsersViewTest(String login, String password) {

            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            allUsersPage = allUsersPage.showEnableUsers();
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            //boolean actual = UserDAO.getStatusByEmail(allUsersPage.getUserDataFromTableRow(rowNumber).get(0));
            boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
            Assert.assertEquals(actual, true);


    }


    @Test(dataProvider = "loginData")
    public void disableUsersViewTest(String login, String password) {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            allUsersPage = allUsersPage.showDisableUsers();
            BrowserWrapper.sleep(2);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
            Assert.assertEquals(actual, false);
    }


    @Test(dataProvider = "loginData")
    public void viewWindowTest(String login, String password) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            List<String> actual = allUsersPage.getUserDataFromInfoWindow(rowNumber);
            List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(1));
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{allInfo.get(0),allInfo.get(1), "true"});
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(dataProvider = "loginDataAndRole")
    public void changeRoleTest(String login, String password, String role) {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            String expected = role;
            int rowNumber = 1;
            allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, role);
            String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
            Assert.assertEquals(actual, expected);

    }


    @Test(dataProvider = "loginDataAndCount")
    public void changeCountOfUsersOnPageTest(String login, String password, String count) {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            int expected = Integer.parseInt(count);
            allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
            int actual = allUsersPage.getCountOfUsersInTable();
            Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "loginDataAndRole")
    public void searchByRoleTest(String login, String password, String role) {

            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            String expected = role;
            allUsersPage = allUsersPage.changeRole(expected);
            BrowserWrapper.sleep(2);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
            Assert.assertEquals(actual, expected);

    }


    @Test(dataProvider = "searchParams")
    public void searchTest(String login, String password, String role, String valueOfField, String count) {

            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            allUsersPage = allUsersPage.search(Integer.parseInt(count), role, "firstName", valueOfField);
            BrowserWrapper.sleep(2);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{valueOfField, role});
            TableParser tableParser = new TableParser(allUsersPage.table);
            List<String> actual = new LinkedList<>();
            if (tableParser.getFieldFromTableRow(rowNumber, "@email").contains(valueOfField))actual.add(valueOfField);
                else actual.add("noSame");
            actual.add(tableParser.getFieldFromTableRow(rowNumber, "role"));
            Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "loginData")
    public void nextPageButtonTest(String login, String password) {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
            BrowserWrapper.waitForPage(driver);
            Assert.assertNotEquals(allUsersPage, allUsersPage1);
    }


    @Test(dataProvider = "loginData")
    public void deleteUsersTest(String login, String password) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            String actual = allUsersPage.getCurrentUrl();
            allUsersPage = allUsersPage.deleteUser(rowNumber);
            String expected = allUsersPage.getCurrentUrl();
            Assert.assertEquals(actual, expected);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(dataProvider = "dataForSortTest")
    public void sortByEmailTest(String login, String password, String role) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.waitForPage(driver);
            allUsersPage.changeRole(role);
            allUsersPage.searchButton.click();
            BrowserWrapper.sleep(2);
            allUsersPage = allUsersPage.clickSortByEmail();
            BrowserWrapper.sleep(2);
            int actual = new TableParser(allUsersPage.table).getFieldFromTableRow(1, "@email").compareToIgnoreCase
                    (new TableParser(allUsersPage.table).getFieldFromTableRow(2, "@email"));
            Assert.assertEquals(actual < 0, true);
        }   catch (NoSuchElementException e) {
            Assert.assertEquals(true, false);
        }
    }







    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"admin@hospitals.ua", "1111"}
        };
    }

    @DataProvider
    public Object[][] loginDataAndRole() {
        return new Object[][] {
                {"admin@hospitals.ua", "1111", "MANAGER"},
                {"admin@hospitals.ua", "1111", "PATIENT"}
        };
    }

    @DataProvider
    public Object[][] dataForSortTest() {
        return new Object[][]{
                {"admin@hospitals.ua", "1111", "\"\""},
                {"admin@hospitals.ua", "1111", "PATIENT"}
        };
    }

    @DataProvider
    public Object[][] loginDataAndCount() {
        return new Object[][] {
                {"admin@hospitals.ua", "1111", "20"}
        };
    }

    @DataProvider
    public Object[][] searchParams() {
        return new Object[][] {
                {"admin@hospitals.ua", "1111", "MANAGER", "a", "20"}
        };
    }


    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max-1)+1;
    }

}
