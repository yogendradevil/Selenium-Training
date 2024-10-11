package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Dropdown {
	ChromeDriver driver;

	@Test(priority = 1)
	public void login_to_app() {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).isDisplayed();

	}

	@Test(priority = 2)
	public void create_Order() {
		driver.findElement(By.linkText("Order")).click();
		Select product = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		product.selectByValue("ScreenSaver");
		// product.selectByVisibleText("FamilyAlbum");
		// product.selectByIndex(1);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("5");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("Dixit");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("ABC");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Redwood");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("5");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("123456789");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("12/23");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		String ActSuccessMsg = driver
				.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']"))
				.getText();
		String ExpSuccessMsg = "New order has been successfully added.";
		Assert.assertEquals(ExpSuccessMsg, ActSuccessMsg);
		// GO back to View All Order page and Verify that user got created

		driver.findElement(By.linkText("View all orders")).click();
		String ActUserName = driver.findElement(By.xpath("//td[text()='Dixit']")).getText();
		String ExpUserName = "Dixit";
		Assert.assertEquals(ExpUserName, ActUserName);

	}

	@BeforeTest
	public void pre_condition() {
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void post_condition() {
		driver.findElement(By.linkText("Logout")).click();
		driver.close();
	}
}
