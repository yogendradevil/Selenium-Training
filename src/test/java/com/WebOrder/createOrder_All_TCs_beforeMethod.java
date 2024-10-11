package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class createOrder_All_TCs_beforeMethod {
	WebDriver driver;
	@Test(dataProvider="WebOrder_All_Orders_TC",dataProviderClass=TestData.class)
	public void Create_Order(String quantity, String discount, String name, String street, String city, String state, String zip, String cardNo, String expiry, String Exp_Msg) {
		//define browser driver reference
		driver.findElement(By.linkText("Order")).click();
		Select se = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		se.selectByValue("FamilyAlbum");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount")).sendKeys(discount);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(name);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNo);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expiry);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		boolean foundMsg = driver.getPageSource().contains(Exp_Msg);
		Assert.assertEquals(true, foundMsg);
		
	}
	@BeforeTest
	public void pre_condition() {
		//WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$login_button']")).click();
	}
	@AfterTest
	public void post_condition() {
		
		driver.findElement(By.linkText("Logout")).click();
		driver.close();
	}

}
