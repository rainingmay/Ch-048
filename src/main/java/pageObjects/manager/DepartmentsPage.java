package pageObjects.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ytomktc on 06.04.2017.
 */
public class DepartmentsPage {
    @FindBy(xpath = "/html/body/section/div/div/h1")
    private WebElement departmentsLabel;
    

}
