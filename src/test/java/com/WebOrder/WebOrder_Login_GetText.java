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

public class WebOrder_Login_GetText {
	WebDriver driver;
	@Test(priority=1)
	public void login() throws InterruptedException {
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_logout")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "List of All Orders");
		Assert.assertEquals(driver.getTitle(),"Web Orders");
	}
	@AfterTest
	public void cloasbrowser() {
		driver.quit();
	}
	@BeforeTest
	public void launchBrowser() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
	}
	@Test(priority=2)
	public void logout() throws InterruptedException {
		driver.findElement(By.id("ctl00_logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).isDisplayed();
		Assert.assertEquals(driver.getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
	}
	
}
