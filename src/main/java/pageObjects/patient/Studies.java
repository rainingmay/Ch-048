package pageObjects.patient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.PatientHeader;

/**
 * Created by gregtar on 06.04.17.
 */
public class Studies extends PageObject {

    @FindBy(xpath = "/html/body/section/div/div/ul/li[1]/a")
    private WebElement resultOfStudiesTab;

    @FindBy(xpath = "/html/body/section/div/div/ul/li[2]/a")
    private WebElement futureStudiesTab;

    @FindBy(xpath = "//*[@id=\"menu2\"]/div/div[2]/div/h3")
    private WebElement futureStudiesLabel;

    public void resultOfStudiesTabClick(){resultOfStudiesTab.click();}

    public void futureStudiesTabClick(){futureStudiesTab.click();}



    public Studies(WebDriver driver){
        super(driver, new PatientHeader(driver));
    }
}
