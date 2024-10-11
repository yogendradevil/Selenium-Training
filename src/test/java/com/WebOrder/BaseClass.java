package com.WebOrder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
    static WebDriver driver;

    String filePath = System.getProperty("user.dir");
    String filepath_failure = filePath + "\\Screenshot_fail";
    String filePath_Success = filePath + "\\Screenshot_success";

    public static WebDriver crossBrowserTesting(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            // WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            // WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            // WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            // WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshot_fail/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static String getScreenhotSuccess(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshot_success/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    static ExtentSparkReporter htmlreporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void CreateExtentReport(String ReportName, String Browser) {
        htmlreporter = new ExtentSparkReporter("./ExtentReport/" + ReportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlreporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", Browser);

        htmlreporter.config().setDocumentTitle("Regression Test");
        htmlreporter.config().setReportName(ReportName);
        htmlreporter.config().setTheme(Theme.DARK);
        htmlreporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
}