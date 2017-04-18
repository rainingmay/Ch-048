
package pages.admin;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;

public class CreateUserTest extends BaseTest {





    public static final String NEWUSERLOGIN = "1testadmin182@gmail.com.ua";
    public static final String NEWUSERPASSWORD = "Q12345w";
    public static final String NEWUSERROLE = "ADMIN";

    public static final String IDFORWAITING = "searchButton";
    public static final String SUCCEFULYCREATEDUSERTEXT = " successfully registered!";


    @BeforeMethod
    private void beforeMethod() throws InterruptedException {
        BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
    }



@Test
    public void isElementsPresentAddUserTest() throws Exception{
    BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
    AddUserPage addUserPage = new AddUserPage(driver);
    BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
    addUserPage = addUserPage.header.goToAddUserPage();
        try{
            BrowserWrapper.waitForPage(driver);
            addUserPage.isPageReady();
        }catch (Exception e){

            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(addUserPage.isPageReady());

    }


    @Test
    public void successfulAddNewUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(NEWUSERLOGIN, NEWUSERPASSWORD, NEWUSERROLE);

        String actualCreatedLableText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = NEWUSERLOGIN + SUCCEFULYCREATEDUSERTEXT ;  //" successfully registered!";

        Assert.assertEquals(actualCreatedLableText,expectedCreatedLabelText);

    }

    @Test
    public void noRoleChangedAddNewUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUserWithotRole(NEWUSERLOGIN, NEWUSERPASSWORD);

        String actualErrorRolesLabelText = addUserPage.userRolesErrorLabel.getText();
        String expectedErrorRolesLabelText = "This field is required.";

        Assert.assertEquals(actualErrorRolesLabelText,expectedErrorRolesLabelText);

    }

    @DataProvider(name = "validInformation")
    public static Object[][] emailDetails() {

        return new Object[][] {
                { "2t1estonemal@mail.ru", NEWUSERPASSWORD },
                { "2t1esttwomail@com.ru", NEWUSERPASSWORD },
                {"2t1estthreemail@com.cv.ua", NEWUSERPASSWORD},
                {"2n1ewonetest1l@is.low.pass.case",NEWUSERPASSWORD}
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


    @Test
    public void noRequiredEmailTest() throws Exception{
       // BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser("", NEWUSERPASSWORD, NEWUSERROLE);

        String expectedEmailErrorLabelText = "This field is required.";
        String actualEmailErrorLabel = addUserPage.emailErrorLabel.getText() ;

        Assert.assertEquals(expectedEmailErrorLabelText , actualEmailErrorLabel);
        System.out.println("Email field is required but empty");
    }

    @Test
    public void noRequiredPasswordTest() throws Exception{
        // BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(NEWUSERLOGIN, "", NEWUSERROLE);

        String expectedPasswordErrorLabelText = "This field is required.";
        String actualPasswordErrorLabel = addUserPage.passwordErrorLabel.getText() ;

        Assert.assertEquals(expectedPasswordErrorLabelText , actualPasswordErrorLabel);
        System.out.println("Password field is required but empty");
    }

    @Test(dataProvider = "validInformation")
    public void validInfoAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualCreatedLabelText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = addUserName + SUCCEFULYCREATEDUSERTEXT; //" successfully registered!";

        Assert.assertEquals(actualCreatedLabelText,expectedCreatedLabelText);

    }



    @Test(dataProvider = "notValidEmails")
    public void notValidEmailsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualEmaiErrorLabelText = addUserPage.emailErrorLabel.getText() ;
        String expectedEmailErrorLabelText ="";

        Assert.assertNotEquals(actualEmaiErrorLabelText,expectedEmailErrorLabelText);




    }

    @Test(dataProvider = "notValidPasswords")
    public void notValidPasswordsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String expectedPasswordErrorLabelText = "";
        String actualPasswordErrorLabelText = addUserPage.passwordErrorLabel.getText() ;

        Assert.assertNotEquals(expectedPasswordErrorLabelText,actualPasswordErrorLabelText);
    }

    @AfterMethod
    public void afterMethod(){
        try {
            BaseNavigation.logout(this.driver);
            //BrowserWrapper.browserClose(driver);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (TimeoutException e){
            e.printStackTrace();
            System.out.println("failed");
        }


    }










}
