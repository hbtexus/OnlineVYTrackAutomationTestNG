package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    LoginPage loginPage = new LoginPage();

    @Test
    public void loginTest1(){
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Dashboard");
    }

    @Test
    public void negativeLoginTest1(){
        loginPage.login("wrongusername", "wrongpassword");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
        SeleniumUtils.waitPlease(3);
    }


}
