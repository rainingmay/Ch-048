package pages.allUsers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headers.headersByRole.NotLogInUserHeader;
import tests.FunctionalTest;

/**
 * Created by Evgen on 06.04.2017.
 */
public class HospitalSearchResult extends PageObject{
    public NotLogInUserHeader header;

    public HospitalSearchResult(WebDriver driver) {
        super(driver);
        this.header = new NotLogInUserHeader(driver);
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement hospitalPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfHospitalsPerPage;

    @FindBy(css = "[class='panel-heading']")
    private WebElement hospitalNameAtList;

    @FindBy(css = "[class='about-img']")
    private WebElement hospitalPhotoAtList;

    @FindBy(css = "[class='img-responsive']")
    private WebElement hospitalLogoAtList;

//    public HospitalSearchResult(FunctionalTest driver) {
//        super(driver);
//    }

    public WebElement getHospitalPerPage() {
        return hospitalPerPage;
    }

    public void setHospitalPerPage(WebElement hospitalPerPage) {
        this.hospitalPerPage = hospitalPerPage;
    }


    // для теста

    private String baseUrl;

    String searchButton = "a[href*='#toggle-search']";
    String selectSearch = "select_search";
    String selectFind = "button.btn-info";
    //String urlHome = "HospitalSeeker/";
    String urlSearch = "hospitals?q=";
    String cssHospitalNameAtList = "[class='panel-heading']";
    String cssHospitalPhotoAtList = "[class='about-img']";
    String cssHospitalLogoAtList = "[class='img-responsive']";


    public void changeCountOfHostitalOnPage(int count) {
        numberOfHospitalsPerPage.findElement(By.cssSelector("option[value=" + count + "]")).click();
    }


//    //or private??
//    public boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    //or private??
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private int countOfElement(By by) {
        int i = driver.findElements(by).size();
        return i;
    }


//or private??
    public int testMain(String searchWord) {
        if (isElementPresent(By.cssSelector(searchButton))) {
            driver.findElement(By.cssSelector(searchButton)).click();
        }
        driver.findElement(By.id(selectSearch)).click();
        driver.findElement(By.id(selectSearch)).sendKeys(searchWord);
        driver.findElement(By.cssSelector(selectFind)).click();
        driver.get(FunctionalTest.getBaseUrl() + urlSearch + searchWord);
        if ((countOfElement(By.cssSelector(cssHospitalNameAtList)) == countOfElement(By.cssSelector(cssHospitalPhotoAtList)))
                || (countOfElement(By.cssSelector(cssHospitalNameAtList)) == countOfElement(By.cssSelector(cssHospitalLogoAtList)))) {
            return countOfElement(By.cssSelector(cssHospitalNameAtList));
        }
// check how to countOfElement
        else return countOfElement(By.cssSelector(cssHospitalNameAtList));
    }


}
