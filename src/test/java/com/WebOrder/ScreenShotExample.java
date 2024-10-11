package com.WebOrder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenShotExample {
	ChromeDriver driver;
	// Absolute Path, rather use the relative path
	// String filePath_failure = "D:\\F Drive\\Selenium Training
	// Data\\workspace\\Maven_Selenium_WebDriver_4\\Screenshot_Failure";
	String filePath = System.getProperty("user.dir");
	String filepath_failure = filePath + "\\Screenshot_fail";
	String filePath_Success = filePath + "\\Screenshot_success";
	@Test(priority=1)
	public void login_to_app() {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		//Verify Text Present or not
		String ActListElementName = driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).getText();
		String ExpListElementName = "List of All Orders";
		Assert.assertEquals(ExpListElementName, ActListElementName);
		// Verify Title of the Page
		String ActTitle = driver.getTitle();
		String ExpTitle = "Web Orders";
		Assert.assertEquals(ExpTitle, ActTitle);
		//Verify URL of the Page
		String ActURL = driver.getCurrentUrl();
		String ExpURL = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx";
		Assert.assertEquals(ExpURL, ActURL);
		
	}
	@Test(priority=2)
	public void create_Order()
	{
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
		
		driver.findElement(By.linkText("View all orders")).click();
		String ActUserName = driver.findElement(By.xpath("//td[text()='Dixit']")).getText();
		String ExpUserName = "Dixit";
		Assert.assertEquals(ExpUserName, ActUserName);		;
	}
	

	@BeforeTest
	public void LaunchBrowser()

	{
		//WebDriverManager.chromedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.edgedriver().setup();
		
		//EdgeOptions options = new EdgeOptions();
		//FirefoxOptions options = new FirefoxOptions();
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless=new");
		//options.setHeadless(true);
		// options.setHeadless(true);
		//options.addArguments("incognito");
		//driver = new EdgeDriver(options);
		//driver = new FirefoxDriver(options);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new
			// File(Relativepath_failure+"\\Login.png"));
			FileUtils.copyFile(Browserscreenshot,
					new File(filepath_failure + "\\" + result.getName() + "_" + System.currentTimeMillis() + ".png"));
		} else if (ITestResult.SUCCESS == result.getStatus()) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new
			// File(filePath_Success+"\\Login.png"));
			FileUtils.copyFile(Browserscreenshot,
					new File(filePath_Success + "\\" + result.getName() + "_" + System.nanoTime() + ".png"));
		}
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}
