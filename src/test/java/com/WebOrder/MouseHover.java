package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MouseHover {
	@Test
	public void MouseHoverYatra() throws InterruptedException
	{
		//WebDriverManager.chromedriver().setup();
		// create Edge instance and maximize it
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get( "https://demowebshop.tricentis.com/");
		Thread.sleep(3000);
        
		WebElement MyAccount = driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Computers']"));
		Actions action = new Actions(driver);
        action.moveToElement(MyAccount).perform();
        //driver.findElementById("signInBtn").click();
        Thread.sleep(2000);
		driver.findElement(By.linkText("Desktops")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h1[normalize-space()='Desktops']")).isDisplayed();
		driver.quit();	

	}
}
