
package pages.adminSideTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;

public class CreateUserTest extends BaseTest {





    public static final String NEWUSERLOGIN = "testadmin989@gmail.com.ua";
    public static final String NEWUSERPASSWORD = "Q12345w";
    public static final String NEWUSERROLE = "ADMIN";

    @BeforeMethod
    private void beforeMethod() throws InterruptedException {
        BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);

    }



@Test
    public void isElementsPresentAddUserTest() throws Exception{
       // BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        BrowserWrapper.waitUntilElementClickable((WebElement) By.id("searchButton"));

        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        try{
            BrowserWrapper.waitForPage(driver);
            addUserPage.isPageReady();
        }catch (Exception e){

            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(addUserPage.isPageReady());

    }

    @Test (priority = 1)
    public void successfulBaseAddNewUserTest() throws Exception {
       // BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser(NEWUSERLOGIN, NEWUSERPASSWORD, NEWUSERROLE);

        String actualCreatedLableText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = NEWUSERLOGIN + " successfully registered!";

        Assert.assertEquals(actualCreatedLableText,expectedCreatedLabelText);

    }

    @Test
    public void noRoleChangedAddNewUserTest() throws Exception {
      //  BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUserWithotRole(NEWUSERLOGIN, NEWUSERPASSWORD);

        String actualErrorRolesLabelText = addUserPage.userRolesErrorLabel.getText();
        String expectedErrorRolesLabelText = "";

        Assert.assertNotEquals(actualErrorRolesLabelText,expectedErrorRolesLabelText);


    }

    @DataProvider(name = "validInformation")
    public static Object[][] emailDetails() {

        return new Object[][] {
                { "testonemail1@mail.ru", NEWUSERPASSWORD },
                { "testtwomail1@.com.ru", NEWUSERPASSWORD },
                {"testthreemail1@com.cv.ua", NEWUSERPASSWORD},
                {"newonetest1@is.low.pass.case",NEWUSERPASSWORD}
        };

    }

    @DataProvider(name="notValidEmails")
    public static Object [] [] notValidEmailDetails() {
        return  new Object[][] {
                {"@gmail.com",NEWUSERPASSWORD },
                {"sd@sdj",NEWUSERPASSWORD},
                {"aa@sd.",NEWUSERPASSWORD},
                {"skdjw@.c",NEWUSERPASSWORD},
                {"sd)@.com.ua", NEWUSERPASSWORD},
                {"sdd@@com.ua", NEWUSERPASSWORD}
        };
    }

    @DataProvider(name="notValidPasswords")
    public static Object[][] notValidPasswordsDetails() {
        return new Object[][]{
                {"bnm@mail.ru", "a"},
                {"hjsn@mail.ru", "aaaaa"},
                {"sdx@gmail.com", " "},
                {"jdhjsnw@mail.ua", ""}


        };
    }

    @Test(dataProvider = "validInformation")
    public void validInfoAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
      //  BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualCreatedLabelText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = addUserName + " successfully registered!";

        Assert.assertEquals(actualCreatedLabelText,expectedCreatedLabelText);

    }

    @Test(dataProvider = "notValidEmails")
    public void notValidEmailsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
      //  BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualEmaiErrorLabelText = addUserPage.emailErrorLabel.getText() ;
        String expectedEmailErrorLabelText ="";

        Assert.assertNotEquals(actualEmaiErrorLabelText,expectedEmailErrorLabelText);




    }

    @Test(dataProvider = "notValidPasswords")
    public void notValidPasswordsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
     //   BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String expectedPasswordErrorLabelText = "";
        String actualPasswordErrorLabelText = addUserPage.emailErrorLabel.getText() ;

        Assert.assertNotEquals(expectedPasswordErrorLabelText,actualPasswordErrorLabelText);
    }










}
