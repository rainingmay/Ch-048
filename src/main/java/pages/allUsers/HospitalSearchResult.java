package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.headers.BaseHeader;
import java.util.List;


public class HospitalSearchResult extends PageObject {
    public BaseHeader header;


    public HospitalSearchResult(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement hospitalPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfHospitalsPerPage;

    @FindBy(css = "[class='panel-heading']")
    private List<WebElement> hospitalNameAtList;

    @FindBy(css = "[class='about-img']")
    private List<WebElement> hospitalPhotoAtList;

    @FindBy(css = "[class='img-responsive']")
    private List<WebElement> hospitalLogoAtList;

    @FindBy(css = ".cd-top")
    private WebDriver onTop;
    //css = "[class='cd-top active cd-is-visible']" (cd-top active cd-is-visible)

    @FindBy(css = "[class='pagination pagination-lg']")
    private WebDriver pageNavigation;

    // для теста
    public int countOfHospital() {
        // driver.get("https://localhost:8443/HospitalSeeker/" + urlHospitalSearch + searchWord);
        //   ExpectedConditions.visibilityOfAllElements(hospitalNameAtList);
        return hospitalNameAtList.size();
    }
}
