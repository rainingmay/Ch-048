package pageObjects.patient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.PatientHeader;

/**
 * Created by gregtar on 06.04.17.
 */
public class Card extends  PageObject{

    @FindBy(id = "headingOne")
    private WebElement diagnosisLabel;

     @FindBy(xpath = "//*[@id=\"headingOne\"]/h4/a")
     private WebElement diagnosisTimeLink;

     @FindBy(xpath = "//*[@id=\"headingOne\"]/h4/span")
     private WebElement doctorNameText;

     public void diagnosisTimeLinkClick(){diagnosisTimeLink.click();}


    public Card(WebDriver driver){
        super(driver, new PatientHeader(driver));
    }
}
