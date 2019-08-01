package com.vytrack.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestBase {

    //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;

    //we need this object for building reports, but it doesn't build itself
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void testSetup(){
        //we are creating actual reporter
        report = new ExtentReports();
        //this is path to the report itself
        String pathToReport = System.getProperty("user.dir")+"/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(pathToReport);

        report.attachReporter(htmlReporter);

        //we can add system information to report
        report.setSystemInfo("OS", System.getProperty("os.name"));

        htmlReporter.config().setDocumentTitle("VYTrack Test Automation");

    }

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }


    //ITestresult describes the result of a test.
    //we can determine if test failed, passed or ignored
    @AfterMethod
    public void teardown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()) {
           //if test failed get a screenshot and save the location to the image
            String pathToTheScreenshot = SeleniumUtils.getScreenshot(result.getName());

            // capture the name of a test method that failed
            extentLogger.fail(result.getName());
            try {
                //to add screenshot into report
                extentLogger.addScreenCaptureFromPath(pathToTheScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //to add thrown exception into report
            extentLogger.fail(result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            //if test skipped, this information will appear on the report
            extentLogger.skip("Test case skipped "+result.getName());
        }
        Driver.closeDriver();
    }

    @AfterTest
    public void tearDownTest(){
        report.flush();
    }
}
