package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentReportBaseClass extends BaseClass {
    @BeforeClass
    public void startReport() {
        BaseClass.CreateExtentReport("WebOrder_Regression_Report.html", "Firefox");
    }

    @BeforeTest
    public void launchBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
    }

    @Test(priority = 1)
    public void WebOrder_Login() throws Exception {
        test = BaseClass.extent.createTest("Test Case 1", "Login to WebOrder");

        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
        driver.findElement(By.linkText("Logout")).isDisplayed();
        // Verify Text Present or not
        String ActListElementName = driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).getText();
        String ExpListElementName = "List of All Orders";
        Assert.assertEquals(ExpListElementName, ActListElementName);
        // Verify Title of the Page
        String ActTitle = driver.getTitle();
        String ExpTitle = "Web Orders";
        Assert.assertEquals(ExpTitle, ActTitle);
        // Verify URL of the Page
        String ActURL = driver.getCurrentUrl();
        String ExpURL = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx";
        Assert.assertEquals(ExpURL, ActURL);
    }

    @Test(priority = 2)
    public void create_Order_BaseClass() {
        test = BaseClass.extent.createTest("Test Case 2", "Create Order");
        driver.findElement(By.linkText("Order")).click();
        Select product = new Select(driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct")));
        product.selectByVisibleText("FamilyAlbum");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("5");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("Dixit");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("ABC");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Redwood");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("5");
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("123456789");
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("12/23");
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        String ExpSuccessMsg = driver.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']")).getText();
        String ActSuccessMsg = "New order has been successfully added";
        Assert.assertEquals(ExpSuccessMsg, ActSuccessMsg);
        // GO back to View All Order page and Verify that user got created

        driver.findElement(By.linkText("View all Orders")).click();
        String ActUserName = driver.findElement(By.xpath("//td[text()='Dixit']")).getText();
        String ExpUserName = "Dixit";
        Assert.assertEquals(ExpUserName, ActUserName);
    }

    @AfterMethod
    public void captureScreenShot(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus()) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " = FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
            String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
            // To add it in the extent report
            test.addScreenCaptureFromPath(screenshotPath); // This is for Screenshot

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " = PASSED ", ExtentColor.GREEN));
            String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
            // To add it in the extent report
            test.addScreenCaptureFromPath(screenshotPath); // This is for Screenshot
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        BaseClass.extent.flush();
    }
}