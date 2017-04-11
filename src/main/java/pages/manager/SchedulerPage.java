package pages.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.ManagerHeader;
import utils.BaseNavigation;
import utils.BrowserWrapper;

import java.util.ArrayList;
import java.util.List;


public class SchedulerPage extends PageObject {

    public ManagerHeader managerHeader;
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

    @FindAll({
            @FindBy(css = "div.dhx_scale_holder"),
            @FindBy(css = "div.dhx_scale_holder_now")
    })
    private List<WebElement> tableColomns;

    @FindAll(@FindBy(css = "div.dhx_scale_ignore"))
    private List<WebElement> tableIgnoredColumn;

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

    @FindBy(css = "div.icon_cancel")
    public WebElement cancelEvent;

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

    @FindBy(css = "div.dhx_scale_hour:first-child")
    public WebElement beginingHour;

    @FindBy(css = "div.dhx_scale_hour:last-child")
    public WebElement endHour;

    public StringBuilder errors;

    public void nextMonthButtonClick() throws InterruptedException {
        Thread.sleep(3000);
        nextMonthButton.click();
    }
    public void setAppointment(String text, int column) throws InterruptedException {
        nextMonthButtonClick();
        WebElement col  = tableColomns.get(column+1);

        //BaseNavigation.doubleClick(column,driver);

        BaseNavigation.doubleClick(driver,col);

        Thread.sleep(3000);
        eventInput.sendKeys(text);
        saveEvent.click();


    }

    public int getDaysNumber(){

        return tableColomns.size() - tableIgnoredColumn.size() - 1;
    }

    public boolean checkDefaultConditionScheduler(){

        if(beginingHour.getText().equals("0 00") && endHour.getText().endsWith("23 00") && getDaysNumber()==5){
            return true;
        }else {
            errors.append("scheduler");
            return false;
        }
    }


    public boolean checkDayButton(){
        if(BrowserWrapper.isElementPresent(dayTabButton)){
            return true;
        }else{
            errors.append("dayButton");
            return false;
        }
    }

    public boolean checkWeekButton(){
        if(BrowserWrapper.isElementPresent(weekTabButton)){
            return true;
        }else{
            errors.append("weekButton");
            return false;
        }
    }

    public boolean checkMonthButton(){
        if(BrowserWrapper.isElementPresent(monthTabButton)){
            return true;
        }else{
            errors.append("monthButton");
            return false;
        }
    }

    public boolean checkMiniCalendarButton(){
        if(BrowserWrapper.isElementPresent(miniCalendarButton)){
            return true;
        }else{
            errors.append("miniCalendarButton");
            return false;
        }
    }


    public boolean checkTodayButton(){
        if(BrowserWrapper.isElementPresent(todayButton)){
            return true;
        }else{
            errors.append("todayButton");
            return false;
        }
    }

    public boolean checkPreviousButton(){
        if(BrowserWrapper.isElementPresent(previousMonthButton)){
            return true;
        }else{
            errors.append("previousButton");
            return false;
        }
    }

    public boolean checkNextButton(){
        return BrowserWrapper.isElementPresent(nextMonthButton);
    }

    public boolean checkDaysSelector(){
        return BrowserWrapper.isElementPresent(workWeekSizeSelector);
    }

    public boolean checkBeginAtHourSelector(){
        return BrowserWrapper.isElementPresent(workDayBeginAtSelector);
    }

    public boolean checkEndAtHourSelector(){
        return BrowserWrapper.isElementPresent(workDayEndAtSelector);
    }

    public boolean checkAppointmentSizeSelector(){
        return BrowserWrapper.isElementPresent(apointmentSizeSelector);

    }

    public boolean isPageReady(){
        boolean result = false;
        if(checkAppointmentSizeSelector()
                && checkBeginAtHourSelector()
                && checkDayButton()
                && checkDaysSelector()
                && checkDefaultConditionScheduler()
                && checkEndAtHourSelector()
                && checkMiniCalendarButton()
                && checkMonthButton()
                && checkNextButton()
                && checkTodayButton()
                && checkWeekButton()
                && checkPreviousButton()){
            result = true;
        }
        return result;
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
        super(driver);
        managerHeader = new ManagerHeader(driver);
    }
}
