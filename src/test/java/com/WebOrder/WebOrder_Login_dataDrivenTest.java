package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebOrder_Login_dataDrivenTest extends TestData{
	WebDriver driver;
	@Test(priority=1, dataProvider="Login")
	public void login(String uname, String pass) throws InterruptedException {
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys(uname);
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys(pass);
		Thread.sleep(2000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_logout")).isDisplayed();
		driver.findElement(By.id("ctl00_logout")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).isDisplayed();
	}
	@AfterTest
	public void closebrowser() {
		driver.quit();
	}
	@BeforeTest
	public void launchBrowser() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
	}
	
}
