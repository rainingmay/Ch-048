package pageObjects.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.headers.BaseHeader;

public class PageObject {

    protected WebDriver driver;
    protected BaseHeader header;


    public PageObject(WebDriver driver, BaseHeader header){
        this.driver = driver;
        this.header = header;
        PageFactory.initElements(driver, this);
    }
}
