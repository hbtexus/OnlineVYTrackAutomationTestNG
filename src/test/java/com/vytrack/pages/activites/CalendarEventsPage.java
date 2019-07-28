package com.vytrack.pages.activites;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarEventsPage {
    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEventBtn;

    @FindBy(css = "[id^='oro_calendar_event_form_title']")
    public WebElement titleInputLocator;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDateLocator;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDateLocator;

    @FindBy(css = "a[title='Grid Settings']")
    public WebElement gridSettingsElement;

    @FindBy(css = "a[title='Reset']")
    public WebElement resetBtnElement;

    public CalendarEventsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void selectGridSetting(String name, boolean yesOrNo){
        //click on grid options
        gridSettingsElement.click();
        //create locator for grid option based on the name
        String locator = "//td//label[text()='"+name+"']/../following-sibling::td//input";
        //find element
        //you can also call Driver.get()
        WebElement gridOption = Driver.getDriver().findElement(By.xpath(locator));
        //if param yesOrNo is true, and checkbox is not selected yet
        //click on it
        //or
        //ckeckbox is selected and you want to unselect it
        if((yesOrNo && !gridOption.isSelected())  || (
                yesOrNo == false && gridOption.isSelected())){
            gridOption.click();
        }
    }
}
