package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.manager.AddNewDoctorPage;
import pages.manager.ManagerDashBordPage;
import pages.manager.ModerationFeedBackPage;


/**
 * Created by Evgen on 06.04.2017.
 */
public class ManagerHeader extends AuthorizedHeader {



    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    private WebElement actions;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[1]/a")
    private WebElement hospitals;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    private WebElement addDoctor;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[3]/a")
    private WebElement feedbacks;


    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    private WebElement profile;

    @FindBy(xpath = "//a[contains(@href, '#')])[2]")
    private WebElement myProfile;

    @FindBy(xpath = "//ul[@id='dropdawn']/li[2]/a/span)[2]")
    private WebElement logOut;



    public AddNewDoctorPage addNewDoctorPage() {
        addDoctor.click();
        return new AddNewDoctorPage();
    }

    public ManagerDashBordPage managePage() {
        hospitals.click();
        return new ManagerDashBordPage();
    }

    public ModerationFeedBackPage feedBackPage() {
        feedbacks.click();
        return new ModerationFeedBackPage();
    }




}
