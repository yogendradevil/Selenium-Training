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

public class WebOrder_Login_TCs {
	WebDriver driver;
	@Test(dataProvider="WebOrder_LoginAll_TCs",dataProviderClass=TestData.class)
	public void login_to_app(String uname, String pass, String Exp_Result) {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).clear();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).clear();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		// Logic to decide or take the call
		if(Exp_Result.equalsIgnoreCase("Logout"))
		{
		String Act_Msg = driver.findElement(By.linkText("Logout")).getText();
		Assert.assertEquals(Act_Msg, Exp_Result);
		driver.findElement(By.linkText("Logout")).click();
		}
		else
		{
			String Act_Error_Msg = driver.findElement(By.id("ctl00_MainContent_status")).getText();
			Assert.assertEquals(Act_Error_Msg, Exp_Result);
		}

	}
	@BeforeTest
	public void pre_condition() {
//		WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}
	@AfterTest
	public void post_condition() {
		
		driver.close();
	}
	
}
