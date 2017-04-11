package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotLogInUserHeader;


/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorSearchList extends PageObject{
    public NotLogInUserHeader header;

    public DoctorSearchList(WebDriver driver) {
        super(driver);
        this.header = new NotLogInUserHeader(driver);
    }

//
//    public DoctorSearchList(WebDriver driver) {
//        this.driver = driver;
//    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement rowsPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfRowsPerPage;

    @FindBy(css = "[class='panel-heading']")
    private WebElement doctorNameAtList;

    @FindBy(css = "[class='about-img']")
    private WebElement doctorPhotoAtList;

    //??
    public DoctorInfo toDoctorInfoFromPhoto() {
        doctorPhotoAtList.click();
        return new DoctorInfo (driver);
    }

    //??
    public DoctorInfo toDoctorInfoFromList() {
        doctorPhotoAtList.click();
        return new DoctorInfo (driver);
    }

//   driver.findElement(By.cssSelector("[class='panel-heading']")).size());
 //   doctorNameAtList.size();

}
