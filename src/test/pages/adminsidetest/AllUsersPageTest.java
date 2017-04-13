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


    private static final String LOGIN = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";


    @Test
    public void enableUsersViewTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.implicitWait(driver);
            allUsersPage = allUsersPage.showEnableUsers();
            boolean actual = UserDAO.getStatusByEmail(allUsersPage.getUserDataFromTableRow(4).get(0));
            Assert.assertEquals(actual, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void disableUsersViewTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.implicitWait(driver);
            AllUsersPage allUsersPage1 = allUsersPage.showDisableUsers();
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            boolean actual = UserDAO.getStatusByEmail(allUsersPage1.getUserDataFromTableRow(rowNumber).get(0));
            Assert.assertEquals(actual, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void viewWindowTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
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
    public void changeRoleTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.implicitWait(driver);
            String expected = "MANAGER";
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, "MANAGER");
            BrowserWrapper.sleep(3);
            String actual = allUsersPage.getUserDataFromTableRow(rowNumber).get(3);
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeCountOfUsersOnPageTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.implicitWait(driver);
            int expected = 20;
            allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
            int actual = allUsersPage.getCountOfUsersInTable();
            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchByRoleTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            String expected = "MANAGER";
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
    public void searchTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            allUsersPage = allUsersPage.search(20, "MANAGER", "firstName", "a");
            int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{"20", "a", "MANAGER"});
            List<String> allInfo = allUsersPage.getUserDataFromTableRow(rowNumber);
            List<String> actual = new LinkedList<>();
            if (actual.get(1).contains("a"))actual.add("a");
            actual.add(allInfo.get(3));
            Assert.assertEquals(actual, expected);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


















    /*@Test(priority = 1)
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(1);

            List<String> actual = allUsersPage.getUserDataFromTableRow(4);
            List<String> expected = UserDAO.getUserFromDatabaseByEmail(actual.get(0));

            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 3)
    public void editWindowTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(1);

            boolean actual = false;
            try{
                actual = allUsersPage.openEditWindow(1).findElement(By.cssSelector("input")).isDisplayed();
            } catch (Exception e) {
                actual = false;
            } finally {
                allUsersPage.closeEditWindow();
                BrowserWrapper.sleep(1);
                Assert.assertEquals(actual, true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Test(priority = 5)
    public void changeCountOfUsersOnPageTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(1);
            int expected = 20;
            allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
            int actual = allUsersPage.getCountOfUsersInTable();

            Assert.assertEquals(actual, expected);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


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
