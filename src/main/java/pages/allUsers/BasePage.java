package pages.allUsers;

import org.openqa.selenium.support.PageFactory;
import utils.DriverInitializer;

public class BasePage {



    public BasePage(){
        PageFactory.initElements(DriverInitializer.instance(), this);
    }
}
