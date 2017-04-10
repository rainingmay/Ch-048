package pageObjects.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.ManagerHeader;
import utilities.BaseNavigation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


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

    @FindBy(css = "div.dhx_cal_next_button")
    private WebElement nextMonthButton;

    @FindAll(@FindBy(css = "div.dhx_scale_holder"))
    private List<WebElement> tabelColomns;

    @FindAll(@FindBy(className = "dhx_scale_hour"))
    private List<WebElement> tabelRows;

    @FindAll(@FindBy(css = "div.dhx_body"))
    private List<WebElement> eventBodys;

    @FindAll(@FindBy(css = "div.dhx_cal_event"))
    private List<WebElement> events;

    @FindBy(css= "div.dhx_cal_editor")
    private WebElement eventInput;

    @FindBy(css = "div.dhx_menu_icon.icon_save")
    private WebElement saveEvent;

    @FindBy(className = "div.icon_cancel")
    private WebElement cancelEvent;

    @FindBy(className = "div.icon_details")
    private WebElement detaisEvent;

    @FindBy(className = "div.icon_edit")
    private WebElement editEvent;

    @FindBy(className = "div.dhx_title")
    private WebElement eventTitle;

    @FindBy(css = "div.dhx_cal_ltext")
    private WebElement detailedEditorField;

    @FindBy(css = "div.dhx_btn_set.dhx_left_btn_set.dhx_cancel_btn_set")
    private WebElement cancelDetailedChanges;

    @FindBy(css = "div.dhx_btn_set.dhx_right_btn_set.dhx_delete_btn_set")
    private WebElement deleteDetailedChanges;

    public void nextMonthButtonClick() throws InterruptedException {
        Thread.sleep(3000);
        nextMonthButton.click();
    }
    public void setAppointment(String text, int column) throws InterruptedException {
        nextMonthButtonClick();
        WebElement col  = tabelColomns.get(column+1);
        BaseNavigation.doubleClick(col,driver);
        Thread.sleep(3000);
        eventInput.sendKeys(text);
        saveEvent.click();


    }

    public List<String> getEvents(){
        List<String> list = new ArrayList<>();

        if(eventBodys.size() == 0){
            return list;
        }
        for(WebElement element : eventBodys){
            list.add(element.getText());
        }

        return list;
    }

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

    public void saveButtonClick(){
        saveButton.click();
    }

    public void dayTabButtonClick(){
        dayTabButton.click();
    }

    public void weekTabButtonClick(){
        weekTabButton.click();
    }

    public void monthTabButtonClick(){
        monthTabButton.click();
    }

    public void miniCalendarButtonClick(){
        miniCalendarButton.click();
    }

    public void todayButtonClick(){
        todayButton.click();
    }

    public void previousMonthButtonClick(){
        previousMonthButton.click();
    }




    public SchedulerPage(WebDriver driver) {
        super(driver, new ManagerHeader(driver));
    }
}
