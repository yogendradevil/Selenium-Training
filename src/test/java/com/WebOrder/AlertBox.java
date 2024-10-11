package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertBox {
	WebDriver driver;
  @Test
  public void AlertEx() {
//	  alert box
	  driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();
	  String actualText = driver.switchTo().alert().getText();
	  String expectedText = "I am a JS Alert";
	  Assert.assertEquals(actualText, expectedText);
	  driver.switchTo().alert().accept();
	  
//	  conform box
	  driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
	  driver.switchTo().alert().dismiss();
	  
//	  enter text
	  driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();
	  driver.switchTo().alert().sendKeys("yogi");
	  driver.switchTo().alert().accept();
	  String act = driver.findElement(By.xpath("//p[@id='result']")).getText();
	  Assert.assertTrue(act.contains("yogi"));
  }
  
  @BeforeTest
	public void pre_condition() {
	  	String url = "https://the-internet.herokuapp.com/javascript_alerts";
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	@AfterTest
	public void post_condition() {
		driver.quit();
	}
}
