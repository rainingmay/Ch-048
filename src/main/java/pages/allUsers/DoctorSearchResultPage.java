package pages.allUsers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;

import java.util.List;


/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorSearchResultPage implements PageInitializer {
    private BaseHeader header;


    public DoctorSearchResultPage() {
        header = new BaseHeader();
        pageInitialization();
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement doctorsPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfDoctorsPerPage;

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> doctorNameAtList;

    @FindBy(css = ".about-img")
    private List<WebElement> doctorPhotoAtList;

    @FindBy(css = ".img-responsive")
    protected List<WebElement> doctorLogoAtList;

    @FindBy(css = ".cd-top")
    private WebElement onTop;

    @FindBy(css = "div.about-img > a > img.img-responsive")
    public WebElement firstDoctorImage;

    @FindBy(css = "a[href=/HospitalSeeker/doctor/6]")
    private WebElement testlink;



    @FindBy(css = "[class='pagination pagination-lg']")
    private WebElement pageNavigation;





    public int countOfDoctors() {
        return doctorNameAtList.size();
    }


    public DoctorInfoPage goToDoctorInfoPage(){
        BrowserWrapper.sleep(3);
        //BrowserWrapper.doubleClickJs(firstDoctorImage);
        firstDoctorImage.click();
        BrowserWrapper.sleep(5);
        return new DoctorInfoPage();
    }

}
