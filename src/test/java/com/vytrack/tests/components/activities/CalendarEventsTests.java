package com.vytrack.tests.components.activities;

import com.vytrack.pages.activites.CalendarEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {

    @Test
    public void verifyTitleColumn() {
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //deselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        calendarPage.selectGridSetting("Title", false);
    }
}