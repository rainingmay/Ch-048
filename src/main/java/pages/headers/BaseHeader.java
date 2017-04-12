package pages.headers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.HospitalSearchResult;
import pages.allUsers.HospitalSeekerHomePage;
import pages.allUsers.MapSearch;
import pages.allUsers.PageObject;


/**
 * Created by Evgen on 05.04.2017.
 */
public class BaseHeader extends PageObject{
    protected WebDriver driver;

    @FindBy(className = "img-responsive logo")
    protected WebElement logo;

    @FindBy(css = "a[href$='/HospitalSeeker/']")
    protected WebElement home;

    @FindBy(css = "a[href$='/HospitalSeeker/mapsearch']")
    protected WebElement nearestHospital;


    @FindBy(className = "img.localization-flag")
    protected WebElement changeLanguageIco;

    @FindBy(css = "a[href$='https://localhost:8443/HospitalSeeker/?lang=ua']")
    protected WebElement uaLanguage;

    @FindBy(css = "a[href$='https://localhost:8443/HospitalSeeker/?lang=en']")
    protected WebElement enLanguage;


    @FindBy(className = "[ animate ]")
    protected WebElement search;

    @FindBy(id = "select_hospital_search")
    protected WebElement hospitalSearchField;

    @FindBy(id = "select_hospital_search_button")
    protected WebElement hospitalSearchButton;

    @FindBy(id = "select_doctor_search")
    protected WebElement doctorSearchField;

    @FindBy(id = "select_doctor_search_button")
    protected WebElement doctorSearchButton;

    public BaseHeader(WebDriver driver) {
        super(driver);
    }


    public HospitalSeekerHomePage toHomePage() {
        home.click();
        return new HospitalSeekerHomePage(driver);
    }

    public MapSearch toMapOfHospitals() {
        nearestHospital.click();
        return new MapSearch(driver);
    }


  /*  public BaseHeader changeLanguageToUa() {
        uaLanguage.click();
        return new BaseHeader(driver);
    }
    public BaseHeader changeLanguagetoEn() {
        enLanguage.click();
        return this;
    }*/

    public HospitalSearchResult findHospital(String hospitalName) {
        hospitalSearchField.clear();
        hospitalSearchField.sendKeys(hospitalName);
        hospitalSearchButton.click();
        return new HospitalSearchResult(driver);
    }

}
