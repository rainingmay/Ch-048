package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.admin.AddNewHospitalPage;
import pages.admin.AllUsersPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 26.04.2017.
 */
public class AdminHospitalListActionSteps {
    private static final String ADMIN_LOGIN = "admin@hospitals.ua";
    private static final String ADMIN_PASSWORD = "1111";

    private static final String HOSPITAL_LIST_TITLE = "hospital list";

    private HospitalListPage hospitalListPage;
    private AllUsersPage allUsersPage;
    private AddNewHospitalPage addNewHospitalPage;

    @Given("ADMIN is on hospital list page")
    public void to_hospital_list_page() {
        allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        hospitalListPage = allUsersPage.header.goToAllHospitalsPage();
        BrowserWrapper.sleep(2);
    }


    @When("^ADMIN press button \"Add new hospital\"$")
    public void admin_try_open_add_hospital_page() {
        addNewHospitalPage = hospitalListPage.submitAddNewHospital();
        BrowserWrapper.sleep(2);
    }

    @Then("^ADMIN must see page \"Hospital add/edit page\"$")
    public void admin_is_on_add_hospital_page() {
        Assert.assertEquals(addNewHospitalPage.pageLabel.getText().toLowerCase(), "hospital add/edit page" );
    }
}
