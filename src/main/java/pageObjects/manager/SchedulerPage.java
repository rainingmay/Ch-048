package pageObjects.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.ManagerHeader;


public class SchedulerPage extends PageObject {

    @FindBy(xpath = "/html/body/section/div/div/div[3]/div/form/div[2]/p")
    private WebElement doctorNameLabel;

    @FindBy(css = "label[for=\"workWeekSize\"]")
    private WebElement workWeekSizeLable;

    @FindBy(id="workWeekSize")
    private WebElement workWeekSizeSelector;

    @FindBy(css = "label[for=\"workdayBeginAt\"]")
    private WebElement workDayHours;

    @FindBy(id="workDayBeginAt")
    private WebElement workDayBeginAtSelector;

    @FindBy(id="workDayEndAt")
    private WebElement workDayEndAtSelector;

    @FindBy(css = "label[for=\"appointmentTime\"]")
    private WebElement apointmentSizeLabel;

    @FindBy(id="appointmentTime")
    private WebElement apointmentSizeSelector;

    @FindBy(id="saveData")
    private WebElement saveButton;

    @FindBy(name="day_tab")
    private WebElement dayTabButton;

    @FindBy(name="week_tab")
    private WebElement weekTabButton;

    @FindBy(name = "month_tab")
    private WebElement monthTabButton;

    @FindBy(id="dhx_minical_icon")
    private WebElement miniCalendarButton;

    @FindBy(className="div.hx_cal_date")
    private WebElement dateLabel;

    @FindBy(className = "div.dhx_cal_today_button")
    private WebElement todayButton;

    @FindBy(className = "div.dhx_cal_prev_button")
    private WebElement previousMonthButton;

    @FindBy(className = "div.dhx_cal_next_button")
    private WebElement nextMonthButton;

    public void workWeekSizeSelector(String value){
        Select select = new Select(workWeekSizeSelector);
        select.selectByVisibleText(value);
    }


    public void workDayBeginAtSelector(String value){
        Select select = new Select(workDayBeginAtSelector);
        select.selectByVisibleText(value);
    }

    public void workDayEndAtSelector(String value){
        Select select = new Select(workDayEndAtSelector);
        select.selectByVisibleText(value);
    }

    public void apointmentSizeSelector(String value){
        Select select = new Select(apointmentSizeSelector);
        select.selectByVisibleText(value);
    }

    public void saveButton(){
        saveButton.click();
    }

    public void dayTabButton(){
        dayTabButton.click();
    }

    public void weekTabButton(){
        weekTabButton.click();
    }

    public void monthTabButton(){
        monthTabButton.click();
    }

    public void miniCalendarButton(){
        miniCalendarButton.click();
    }

    public void todayButton(){
        todayButton.click();
    }

    public void previousMonthButton(){
        previousMonthButton.click();
    }

    public void nextMonthButton(){
        nextMonthButton.click();
    }

    public SchedulerPage(WebDriver driver) {
        super(driver, new ManagerHeader(driver));
    }
}
