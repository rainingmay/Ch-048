package pages.patient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;


/**
 * Created by gregtar on 06.04.17.
 */
public class CardPage extends PageObject {

    @FindBy(id = "headingOne")
    private WebElement diagnosisLabel;

     @FindBy(xpath = "//*[@id=\"headingOne\"]/h4/a")
     private WebElement diagnosisTimeLink;

     @FindBy(xpath = "//*[@id=\"headingOne\"]/h4/span")
     private WebElement doctorNameText;





    public CardPage(WebDriver driver) {
        super(driver);
    }
}
