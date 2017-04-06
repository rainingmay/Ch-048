package pageObjects.patient;

import pageObjects.allUsers.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by gregtar on 06.04.17.
 */
public class PatientProfile extends PageObject{

    @FindBy(xpath = "//*[@id=\"image-div\"]/figure/a")
    private WebElement profileImage;

    @FindBy(className = "panel-heading")
    private WebElement panelHeadingDiv;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[1]/div")
    private WebElement emergancyInformationCardText;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[1]/h3/span/button")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]")
    private WebElement firstNameLabel;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;


    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[2]/td[1]")
    private WebElement lastNameLabel;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[3]/td[1]")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[4]/td[1]")
    private WebElement dateOfBirthLabel;

    @FindBy(id = "birthDate")
    private WebElement getDateOfBirthInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[4]/td[1]")
    private WebElement phoneNumberLabel;

    @FindBy(id = "phone")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[6]/td[1]")
    private WebElement genderLabel;

    @FindBy(id = "gender")
    private WebElement genderSelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[1]")
    private WebElement homeAdressLabel;

    @FindBy(id = "address")
    private WebElement HomeAdressInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[2]/td[1]")
    private WebElement heightLabel;

    @FindBy(id = "height")
    private WebElement heightInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[3]/td[1]")
    private WebElement weightLabel;

    @FindBy(id = "weight")
    private WebElement weightInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[4]/td[1]")
    private WebElement bloodTypeLabel;

    @FindBy(id = "bloodType")
    private WebElement bloodTypeInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[5]/td[1]")
    private WebElement eyeColorLabel;

    @FindBy(id = "eyeColor")
    private WebElement eyeColorInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[6]/td[1]")
    private WebElement hairColorLabel;

    @FindBy(id = "hairColor")
    private WebElement hairColorInput;

    @FindBy(css = "#patientProfileForm > div > div.panel-body > div > div:nth-child(4)")
    private WebElement medicalInformation;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[1]/td[1]")
    private WebElement allergiesLabel;

    @FindBy(id = "allergies")
    private WebElement allergiesInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[2]/td[1]")
    private WebElement currentMedicationLabel;

    @FindBy(id = "currentMedication")
    private WebElement currentMedicationInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[3]/td[1]")
    private WebElement hearthProblemsLabel;

    @FindBy(id = "heartProblems")
    private WebElement heartProblemsSelect;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[4]/td[1]")
    private WebElement diabetesLabel;

    @FindBy(id = "diabetes")
    private WebElement diabetesSelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[5]/td[1]")
    private WebElement epilepsyLabel;

    @FindBy(id = "epilepsy")
    private WebElement epilepsySelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[6]/td[1]")
    private WebElement restrictionsLabel;

    @FindBy(id = "restrictions")
    private WebElement restrictionsInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[6]")
    private WebElement immediatelyCallCaseLabel;


    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[1]/p")
    private WebElement relativeNameOneLabel;


    @FindBy(id = "relativeName")
    private WebElement relativeNameInputOne;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[2]/p")
    private WebElement relativePhoneLabelOne;

    @FindBy(id = "relativePhone")
    private WebElement relativePhoneInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[3]/p")
    private WebElement relationLabel;

    @FindBy(id = "relativeRelation")
    private WebElement relativeRelativeInputOne;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[1]/p")
    private WebElement relativeNameTwoLabel;

    @FindBy(xpath = "//*[@id=\"relativeName\"]")
    private WebElement relativeNameInputTwo;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[2]/p")
    private WebElement relativePhoneLabelTwo;

    @FindBy(xpath = "//*[@id=\"relativePhone\"]")
    private WebElement relativePhoneInputTwo;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[3]/p")
    private WebElement relativeRelativeLabelTwo;

    @FindBy(xpath = "//*[@id=\"relativeRelation\"]")
    private WebElement RelativeRelativeInputTwo;

    @FindBy(id = "submitChanges")
    private WebElement submitChangesButton;









    public PatientProfile(WebDriver driver) {
        super(driver);
    }
}
