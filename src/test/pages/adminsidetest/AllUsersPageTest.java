package pages.adminsidetest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.databaseutil.UserDAO;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest {

    @Test
    @Parameters({"login", "password"})
    public void enableUsersViewTest(String login, String password) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.implicitWait(driver);
            allUsersPage = allUsersPage.showEnableUsers();
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            boolean actual = UserDAO.getStatusByEmail(allUsersPage.getUserDataFromTableRow(rowNumber).get(0));
            Assert.assertEquals(actual, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password"})
    public void disableUsersViewTest(String login, String password) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.implicitWait(driver);
            AllUsersPage allUsersPage1 = allUsersPage.showDisableUsers();
            BrowserWrapper.sleep(3);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            boolean actual = UserDAO.getStatusByEmail(allUsersPage1.getUserDataFromTableRow(rowNumber).get(0));
            Assert.assertEquals(actual, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password"})
    public void viewWindowTest(String login, String password) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            List<String> actual = allUsersPage.getUserDataFromInfoWindow(rowNumber);
            List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(1));
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{allInfo.get(0),allInfo.get(1), "true"});
            BrowserWrapper.implicitWait(driver);
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password", "role"})
    public void changeRoleTest(String login, String password, String role) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.implicitWait(driver);
            String expected = role;
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, role);
            BrowserWrapper.sleep(3);
            String actual = allUsersPage.getUserDataFromTableRow(rowNumber).get(3);
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password", "count"})
    public void changeCountOfUsersOnPageTest(String login, String password, String count) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            BrowserWrapper.implicitWait(driver);
            int expected = Integer.parseInt(count);
            allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
            int actual = allUsersPage.getCountOfUsersInTable();
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password", "role"})
    public void searchByRoleTest(String login, String password, String role) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            String expected = role;
            allUsersPage = allUsersPage.changeRole(expected);
            BrowserWrapper.sleep(3);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            String actual = allUsersPage.getUserDataFromTableRow(rowNumber).get(3);
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Parameters({"login", "password", "role", "fieldForSearch", "valueOfField", "count"})
    public void searchTest(String login, String password, String role, String fieldForSearch, String valueOfField, String count) {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, login, password);
            allUsersPage = allUsersPage.search(Integer.parseInt(count), role, fieldForSearch, valueOfField);
            BrowserWrapper.sleep(3);
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{valueOfField, role});
            List<String> allInfo = allUsersPage.getUserDataFromTableRow(rowNumber);
            List<String> actual = new LinkedList<>();
            if (allInfo.get(1).contains(valueOfField))actual.add(valueOfField);
                else actual.add("noSame");
            actual.add(allInfo.get(3));
            Assert.assertEquals(actual, expected);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max-1)+1;
    }


    public boolean toNextPage(AllUsersPage allUsersPage) {
        if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) return false;
        else {
            allUsersPage.nextPageButton.click();
            return true;
        }
    }
}
