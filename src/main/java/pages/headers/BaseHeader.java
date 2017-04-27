package pages.headers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.allUsers.*;
import utils.DriverInitializer;


/**
 * Created by Evgen on 05.04.2017.
 */
public class BaseHeader implements PageInitializer {

    public BaseHeader(){
        pageInitialization();
    }


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

    @FindBy(css = "a[href=\"#toggle-search\"]")
    protected WebElement searchButton;

    @FindBy(id = "select_hospital_search")
    protected WebElement hospitalSearchField;

    @FindBy(id = "select_hospital_search-error")
    protected WebElement hospitalSearchError;

    @FindBy(id = "select_hospital_search_button")
    protected WebElement hospitalSearchButton;

    @FindBy(id = "select_doctor_search")
    protected WebElement doctorSearchField;

    @FindBy(id = "select_doctor_search-error")
    private WebElement doctorSearchError;

    @FindBy(id = "select_doctor_search_button")
    protected WebElement doctorSearchButton;


    public WebElement getDoctorSearchError() {
        return doctorSearchError;
    }

    public WebElement getHospitalSearchError() {
        return hospitalSearchError;
    }

    public HospitalSeekerHomePage toHomePage() {
        home.click();
        return new HospitalSeekerHomePage();
    }

    public MapSearchPage toMapOfHospitals() {
        nearestHospital.click();
        return new MapSearchPage();
    }



    public HospitalSearchResultPage findHospital(String hospitalName) {
        fillHospitalInput(hospitalName);
        hospitalSearchButton.click();
        return new HospitalSearchResultPage();
    }

    public void fillHospitalInput(String hospitalName) {
        searchButton.click();
        hospitalSearchField.clear();
        hospitalSearchField.sendKeys(hospitalName);
    }

    public DoctorSearchResult findDoctor(String doctorName) {
        fillDoctorInput(doctorName);
        doctorSearchButton.click();
        return new DoctorSearchResult();
    }

    public void fillDoctorInput(String doctorName) {
        searchButton.click();
        doctorSearchField.clear();
        doctorSearchField.sendKeys(doctorName);
    }
}
