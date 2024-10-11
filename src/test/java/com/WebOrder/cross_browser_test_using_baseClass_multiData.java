package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class cross_browser_test_using_baseClass_multiData {
WebDriver driver;

@Test(dataProvider="WebOrder_LoginAll_TCs",dataProviderClass=TestData.class)
public void login_to_app(String uname, String pass, String Exp_Result) {
	driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).clear();
	driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys(uname);
	driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).clear();
	driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys(pass);
	driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
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
@Parameters({"browser","url"})
public void pre_condition(String browser,String url) throws Exception 
{
	driver = BaseClass.crossBrowserTesting(browser);
	 driver.manage().window().maximize();
	 driver.get(url);
}


@AfterTest
public void post_condition() {
	
	driver.close();
}
}
