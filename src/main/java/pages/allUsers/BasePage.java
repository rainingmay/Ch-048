package pages.allUsers;

import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class BasePage {



    public BasePage(){
        PageFactory.initElements(Driver.instance(), this);
    }
}
