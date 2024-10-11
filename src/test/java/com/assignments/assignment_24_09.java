package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.WebOrder.TestData;

public class assignment_24_09 {
	WebDriver driver;
	@Test(dataProvider = "product",dataProviderClass=TestData.class)
	public void login(String name, String value) throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='View all products']")).click();
		String expValue = driver.findElement(By.xpath("//td[normalize-space()='"+name+"']/following-sibling::td[1]")).getText();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Order']")).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='ctl00_MainContent_fmwOrder_ddlProduct']")));
		dropdown.selectByValue(value);
		String actValue = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtUnitPrice']")).getAttribute("value");
		Assert.assertTrue(expValue.contains(actValue));
		System.out.println("done for: "+name+" Actual value: "+ actValue+", exp Value: "+expValue);
	}
	
	@AfterTest
	public void cloasbrowser() {
		driver.quit();
	}
	@BeforeTest
	public void init() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_logout")).isDisplayed();
		System.out.println("Login compleated");
	}
}
