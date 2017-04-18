package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headers.BaseHeader;


/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorSearchListPage {

    protected WebDriver driver;
    private BaseHeader header;

    public DoctorSearchListPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement rowsPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfRowsPerPage;

    @FindBy(css = "[class='panel-heading']")
    private WebElement doctorNameAtList;

    @FindBy(css = "[class='about-img']")
    private WebElement doctorPhotoAtList;

    //??
    public DoctorInfoPage toDoctorInfoFromPhoto() {
        doctorPhotoAtList.click();
        return new DoctorInfoPage(driver);
    }

    //??
    public DoctorInfoPage toDoctorInfoFromList() {
        doctorPhotoAtList.click();
        return new DoctorInfoPage(driver);
    }

//   driver.findElement(By.cssSelector("[class='panel-heading']")).size());
 //   doctorNameAtList.size();

}
