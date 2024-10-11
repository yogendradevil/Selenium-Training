package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_HdfcAlert {
	WebDriver driver;
  @Test(priority = 1)
  public void alertTest() throws InterruptedException {
	  driver.switchTo().frame("login_page");
	  driver.findElement(By.linkText("CONTINUE")).click();
	  Thread.sleep(2000);
	  driver.switchTo().alert().accept();
	  driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("1000");
	  driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
	  Thread.sleep(2000);
	  driver.switchTo().defaultContent();	  
	  Thread.sleep(4000);
	  String Actual = driver.findElement(By.xpath("//label[normalize-space()='Password/IPIN']")).getText();
	  String expected = "Password/IPIN";
	  Assert.assertEquals(Actual, expected);
  }
  
  
  @BeforeTest
 	public void pre_condition() {
 	  	String url = "https://netbanking.hdfcbank.com/netbanking/";
 		driver = new ChromeDriver();
 		driver.manage().window().maximize();
 		driver.get(url);
 	}
 	@AfterTest
 	public void post_condition() {
 		driver.quit();
 	}
}
