package pageObjects.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.AdminHeader;
import pageObjects.headers.headersByRole.ManagerHeader;


public class HospitalsPage extends PageObject {
    @FindBy(className = "h1.text-center")
    private WebElement hospitalName;

    //maybe no work
    @FindBy(className = "label[for=\"doctorPerPage\"]")
    private WebElement showDoctorsLabel;

    @FindBy(id = "doctorPerPage")
    private WebElement doctorPerPageSelector;

    @FindBy(className = "label[for=\"pref-specializationBy\"]")
    private WebElement specializationLabel;

    @FindBy(id = "pref-specializationBy")
    private WebElement specializationSelector;

    @FindBy(className = "label[for=\"searchBy\"]")
    private WebElement searchByLabel;

    @FindBy(id = "searchBy")
    private WebElement searchBySelector;

    @FindBy(id = "search")
    private WebElement searchTextFild;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id = "clearButton")
    private WebElement clearButton;


    //change to page refernce
    @FindBy(css = "a[href=\"/HospitalSeeker/manager/manageDepartments/2\"]")
    private WebElement labaratoryButton;
    //too
    @FindBy(css = "a[href=\"/HospitalSeeker/manager/manageDepartments/2\"]")
    private WebElement departmentButton;
    //too
    @FindBy(className = "a[href=\"/HospitalSeeker/newDoctor\"]")
    private WebElement newDoctorButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[1]")
    private WebElement hashLabel;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[2]/i")
    private WebElement emailLabel;


    //Header row in table


    @FindBy(id = "email")
    private WebElement sortByEmailButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[4]/i")
    private WebElement nameLabel;

    @FindBy(id="firstName")
    private WebElement sortByFirstNameButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[4]/i")
    private WebElement lastName;

    @FindBy(id="lastName")
    private WebElement sortByLastNameButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[5]/i")
    private WebElement specializtionLabel;

    @FindBy(id="specialization")
    private WebElement sortBySpecializationButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[6]/i")
    private WebElement categoryLabel;

    @FindBy(id="category")
    private WebElement getSortByCategoryButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[7]/i")
    private WebElement actionLabel;


     //Rows

    //First row
    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[1]")
    private WebElement firstRowNumber;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[2]")
    private WebElement firstRowEmail;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[3]")
    private WebElement firstRowName;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[4]")
    private WebElement firstRowSurname;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[5]")
    private WebElement firstRowSpecialization;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/tbody/tr[1]/td[6]")
    private WebElement firstRowCategory;

    @FindBy(xpath = "//table/tbody/tr[1]/td[7]/span[1]/a")
    private WebElement firstRowShowDoctorsDetailButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[7]/span[2]/a")
    private WebElement firstRowEditDoctorsDetailButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[7]/span[3]/a")
    private WebElement firstRowShowSchedulerButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[7]/span[4]/a")
    private WebElement firstRowDeleteDoctorButton;


    //popUpForms

    @FindBy(xpath = "//*[@id=\"detailForm\"]/h1")
    private WebElement formInformationAboutDoctorLabel;

    @FindBy(id="firstName")
    private WebElement formFirstNameTextField;

    @FindBy(id="lastName")
    private WebElement formLastNameTextField;

    @FindBy(id="email")
    private WebElement formEmailTextField;

    @FindBy(id="image-uploaded")
    private WebElement formImage;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[2]/div[1]/div[1]")
    private WebElement formSpecializationLabel;

    @FindBy(id="specialization")
    private WebElement formSpecializationSelector;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[2]/div[1]/div[2]")
    private WebElement formCategoryLabel;

    @FindBy(id="category")
    private WebElement formCategorySelector;

    @FindBy(id="education")
    private WebElement formEducationTextField;

    @FindBy(id="address")
    private WebElement formAddressTextField;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[1]/div")
    private WebElement formGenderLabel;

    @FindBy(css = "input[value=\"MALE\"]")
    private WebElement formMaleRadioButton;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[2]/div/label")
    private WebElement formMaleLabel;

    @FindBy(css = "input[value=\"FEMALE\"]")
    private WebElement formFemaleRadioButton;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[3]/div/label")
    private WebElement formFemaileLabel;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[6]/div[1]/div")
    private WebElement formDataOfBirthLabel;

    @FindBy(id="birthDate")
    private WebElement formDateOfBirthField;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[6]/div[3]/div")
    private WebElement formPhoneLabel;

    @FindBy(id="phone")
    private WebElement formPhoneField;

    @FindBy(id="cancel")
    private WebElement formCancelButton;

    @FindBy(id="editUser")
    private WebElement formSaveButton;


    @FindBy(xpath = "//html/body/section/a[2]")
    private WebElement backToTopButton;


    public void selectDoctorPerPage(String value) {
        Select dropdown = new Select(doctorPerPageSelector);
        dropdown.selectByValue(value);
    }
    public void selectSpecialization(String value) {
        Select dropdown = new Select(specializationSelector);
        dropdown.selectByValue(value);
    }

    public void selectSerchBy(String value) {
        Select dropdown = new Select(searchBySelector);
        dropdown.selectByValue(value);
    }


    public void searchByText(String value){
        searchTextFild.clear();
        searchTextFild.sendKeys(value);
    }

    public void searchButtonClick(){
        searchButton.click();
    }

    public void clearButtonClick(){
        clearButton.click();
    }

    public DepartmentsPage labaratoryButtonClick(){
        labaratoryButton.click();
        return new DepartmentsPage(driver);
    }

    public DepartmentsPage departmentsButtonClick(){
        departmentButton.click();
        return new DepartmentsPage(driver);
    }

    public AddNewDoctorPage addNewDoctorButtonClick(){
        newDoctorButton.click();
        return new AddNewDoctorPage(driver);
    }

    public void sortByEmailButtonClick(){
        sortByEmailButton.click();
    }

    public void sortByFirstNameButtonClick(){
        sortByFirstNameButton.click();
    }

    public void sortByLastNameButtonClick(){
        sortByLastNameButton.click();
    }

    public void sortBySpecializationButtonClick(){
        sortBySpecializationButton.click();
    }

    public void sortByCategoryButtonClick(){
        getSortByCategoryButton.click();
    }


    public WebElement viewButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child #viewUser"));
    }

    public WebElement editButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child #ediUser"));
    }
    public WebElement scheduleButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child #schedule"));
    }

    public WebElement deleteButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child #deleteDoctor"));
    }



    public HospitalsPage(WebDriver driver) {
        super(driver, new ManagerHeader(driver));


    }
}
