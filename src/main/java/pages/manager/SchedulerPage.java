package pages.manager;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.ManagerHeader;
import utils.BaseNavigation;
import utils.BrowserWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SchedulerPage extends PageObject {

    private static final int DEFAULT_NUMBER_OF_DAYS = 5;
    private static final String DEFAULT_BEGINNING_HOUR = "0 00";
    private static final String DEFAULT_ENDING_HOUR = "23 00";

    public ManagerHeader managerHeader;
    @FindBy(xpath = "/html/body/section/div/div/div[3]/div/form/div[2]/p")
    private WebElement doctorNameLabel;

    @FindBy(css = "label[for=\"workWeekSize\"]")
    private WebElement workWeekSizeLable;

    @FindBy(id="workWeekSize")
    private WebElement workWeekSizeSelector;

    @FindBy(css = "label[for=\"workdayBeginAt\"]")
    private WebElement workDayHoursLabel;

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

    @FindBy(css="div.hx_cal_date")
    private WebElement dateLabel;

    @FindBy(css = "div.dhx_cal_today_button")
    private WebElement todayButton;

    @FindBy(css = "div.dhx_cal_prev_button")
    private WebElement previousMonthButton;

    @FindBy(css = "div.dhx_cal_next_button")
    private WebElement nextMonthButton;

    @FindAll({@FindBy(css = "div.dhx_scale_holder"),
              @FindBy(css = "div.dhx_scale_holder_now")})
    private List<WebElement> tableColumn;

    @FindAll(@FindBy(css = "div.dhx_scale_ignore"))
    private List<WebElement> tableIgnoredColumns;

    @FindAll(@FindBy(className = "dhx_scale_hour"))
    private List<WebElement> tabelRows;

    @FindAll(@FindBy(css = "div.dhx_body"))
    private List<WebElement> eventBodys;

    @FindAll(@FindBy(css = "div.dhx_cal_event"))
    private List<WebElement> events;

//    @FindBy(css= "div.dhx_cal_editor")
//    private WebElement eventInput;

    @FindBy(xpath = "//textarea[contains(@class,'editor')]")
    private WebElement eventInput;

    @FindBy(css = "div.dhx_menu_icon.icon_save")
    private WebElement saveEvent;

    @FindBy(css = "div.icon_cancel")
    public WebElement cancelEvent;

    @FindBy(css = "div.icon_details")
    private WebElement detaisEvent;

    @FindBy(css = "div.icon_edit")
    private WebElement editEvent;

    @FindBy(css = "div.dhx_title")
    private WebElement eventTitle;

    @FindBy(css = "div.icon_delete")
    private WebElement eventDelete;

    @FindBy(css = "div.dhx_cal_ltext")
    private WebElement detailedEditorField;

    @FindBy(css = "div.dhx_btn_set.dhx_left_btn_set.dhx_cancel_btn_set")
    private WebElement cancelDetailedChanges;

    @FindBy(css = "div.dhx_btn_set.dhx_right_btn_set.dhx_delete_btn_set")
    private WebElement deleteDetailedChanges;

    @FindBy(css = "div.dhx_scale_hour:first-child")
    public WebElement beginningHour;

    @FindBy(css = "div.dhx_scale_hour:last-child")
    public WebElement endHour;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div[1]/div")
    public WebElement eventDeleteConfirmation;

    public WebElement getColumn(int i){
        if(i< tableColumn.size()-tableIgnoredColumns.size()) {
            WebElement element = driver.findElement(By.cssSelector("div.dhx_scale_holder:nth-child(" + i + ")"));
            return element;
        }
        return null;
    }


    public void nextMonthButtonClick() throws InterruptedException {
        nextMonthButton.click();
    }
    public void setAppointment(String text, int column) throws InterruptedException {
        nextMonthButtonClick();

        BaseNavigation.doubleClick(driver,getColumn(column));
        BrowserWrapper.waitUntilElementVisible(eventInput);
        eventInput.sendKeys(text);
        BrowserWrapper.waitUntilElementVisible(saveButton);
        saveEvent.click();

    }

    public void callEventContext(){
        events.get(0).findElement(By.cssSelector("div.dhx_title")).click();
    }
    public void deleteEventButtonClick(){
        eventDelete.click();
        BrowserWrapper.waitUntilElementClickable(eventDeleteConfirmation);
        eventDeleteConfirmation.click();
    }

    public void editEventText(String text){
        editEvent.click();
        eventInput.sendKeys(text);
        saveEvent.click();
    }


    public int getDaysNumber(){
        return tableColumn.size() - tableIgnoredColumns.size() - 1;
    }

    public String getBeginningHour(){
        return beginningHour.getText();
    }

    public String getEndingHour(){
        return endHour.getText();
    }


    public boolean checkDefaultConditionScheduler(){
        boolean result = false;
        try {
            result = beginningHour.getText().equals(DEFAULT_BEGINNING_HOUR)
                    && endHour.getText().endsWith(DEFAULT_ENDING_HOUR)
                    && getDaysNumber() == DEFAULT_NUMBER_OF_DAYS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void setDayDuration(String begin, String end){
        BrowserWrapper.waitUntilElementClickable(workDayBeginAtSelector);
        BrowserWrapper.selectDropdown(workDayBeginAtSelector, begin);
        BrowserWrapper.waitUntilElementClickable(workDayEndAtSelector);
        BrowserWrapper.selectDropdown(workDayEndAtSelector, end);
    }

    private boolean checkDayButton(){
        return (BrowserWrapper.isElementPresent(dayTabButton));
    }

    private boolean checkWeekButton(){
        return BrowserWrapper.isElementPresent(weekTabButton);

    }

    private boolean checkMonthButton(){
        return BrowserWrapper.isElementPresent(monthTabButton);
    }

    private boolean checkMiniCalendarButton(){
        return BrowserWrapper.isElementPresent(miniCalendarButton);
    }


    private boolean checkTodayButton(){
        return BrowserWrapper.isElementPresent(todayButton);
    }

    private boolean checkPreviousButton(){
        return BrowserWrapper.isElementPresent(previousMonthButton);
    }

    private boolean checkNextButton(){
        return BrowserWrapper.isElementPresent(nextMonthButton);
    }

    private boolean checkWeekSizeSelectorSelector(){
        return BrowserWrapper.isElementPresent(workWeekSizeSelector);
    }

    private boolean checkBeginAtHourSelector(){
        return BrowserWrapper.isElementPresent(beginningHour);
    }

    private boolean checkEndAtHourSelector() {
        return BrowserWrapper.isElementPresent(endHour);
    }

    private boolean checkAppointmentSizeSelector(){
        return BrowserWrapper.isElementPresent(apointmentSizeSelector);
    }

    private boolean checkDoctorLabel(){return BrowserWrapper.isElementPresent(doctorNameLabel); };

    public boolean isPageReady() throws Exception {
        BrowserWrapper.waitUntilElementVisible(doctorNameLabel);
        StringBuilder errors = new StringBuilder();
        if(!checkAppointmentSizeSelector()){
            errors.append("appointment size selector\n");
        }
        if(!checkBeginAtHourSelector()){
            errors.append("begin at hour selector\n");
        }
        if(!checkDayButton()){
            errors.append("day button\n");
        }
        if(!checkWeekSizeSelectorSelector()){
            errors.append("week size selector\n");
        }
        if(!checkEndAtHourSelector()){
            errors.append("end at hour selector\n");
        }
        if(!checkMiniCalendarButton()){
            errors.append("mini calendar button\n");
        }
        if(!checkMonthButton()){
            errors.append("month button\n");
        }
        if(!checkNextButton()){
            errors.append("next button\n");
        }
        if(!checkTodayButton()){
            errors.append("today button\n");
        }
        if(!checkWeekButton()){
            errors.append("week button\n");
        }
        if(!checkPreviousButton()) {
            errors.append("previous button\n");
        }
        if(!checkDoctorLabel()){
            errors.append("doctor label\n");
        }
        if(!errors.toString().isEmpty()){
            errors.append("are not present");
            throw new NoSuchElementException(errors.toString());
        }
        return true;
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
        BrowserWrapper.waitUntilElementClickable(workWeekSizeSelector);
        BrowserWrapper.selectDropdown(workWeekSizeSelector, value);
    }


    public void workDayBeginAtSelector(String value){
        BrowserWrapper.selectDropdown(workDayBeginAtSelector, value);
    }

    public void workDayEndAtSelector(String value){
        BrowserWrapper.selectDropdown(workDayEndAtSelector, value);
    }

    public void apointmentSizeSelector(String value){
        BrowserWrapper.selectDropdown(apointmentSizeSelector, value);
    }

    public void saveButtonClick(){
        BrowserWrapper.waitUntilElementVisible(saveButton);
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