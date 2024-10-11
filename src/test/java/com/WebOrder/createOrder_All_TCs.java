package com.WebOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class createOrder_All_TCs {
	WebDriver driver;

	@BeforeTest
	public void pre_condition() {
		//WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@BeforeMethod
	public void login() {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).isDisplayed();
		driver.findElement(By.xpath("//a[text()='Order']")).click();
	}

	@AfterMethod
	public void logout() {
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterTest
	public void post_condition() {
		driver.close();
	}

	@Test(dataProvider = "WebOrder_CreateOrder_All_TCs",dataProviderClass=TestData.class)
	public void create_order(String product, String quantity, String name, String street, String city, String zipcode,
			String card, String cardNr, String expdate, String expResult) throws InterruptedException {

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(name);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipcode);
		if (!card.equals("")) {
			driver.findElement(By.xpath("//input[@value='" + card + "']")).click();
		}

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNr);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expdate);
		
		if (!expResult.equals("empty_quantity"))
			driver.findElement(By.linkText("Process")).click();

		switch (expResult) {
		case "valid":
			String expMessage = driver
					.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']"))
					.getText();
			String actMessage = "New order has been successfully added.";
			Assert.assertEquals(expMessage, actMessage);
			break;
		case "empty_quantity":
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).clear();
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Quantity' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "invalid_quantity":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()='Quantity must be greater than zero.']"))
							.isDisplayed());
			break;
		case "empty_name":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Customer name' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "empty_street":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Street' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "empty_city":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'City' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "empty_zip":
			Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Zip' cannot be empty.\"]"))
					.isDisplayed());
			break;
		case "invalid_zip":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()='Invalid format. Only digits allowed.']"))
							.isDisplayed());
			break;
		case "empty_card":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()='Select a card type.']")).isDisplayed());
			break;
		case "empty_cardnr":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Card Nr' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "invalid_cardnr":
			Assert.assertTrue(driver.findElement(By.xpath(
					"//span[normalize-space()='Invalid format. Only digits allowed.'] [@id=\"ctl00_MainContent_fmwOrder_RegularExpressionValidator2\"]"))
					.isDisplayed());
			break;
		case "empty_expdate":
			Assert.assertTrue(
					driver.findElement(By.xpath("//span[normalize-space()=\"Field 'Expire date' cannot be empty.\"]"))
							.isDisplayed());
			break;
		case "invalid_expdate":
			Assert.assertTrue(driver
					.findElement(By.xpath("//span[normalize-space()=\"Invalid format. Required format is mm/yy.\"]"))
					.isDisplayed());
			break;

		}

	}

}
