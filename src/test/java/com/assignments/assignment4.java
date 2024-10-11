package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class assignment4 {
	WebDriver driver;

	@Test
	public void login() throws InterruptedException {

		Thread.sleep(3000);
		driver.findElement(By.className("ico-login")).click();
		driver.findElement(By.className("email")).sendKeys("yogibijapur@gmail.com");
		driver.findElement(By.className("password")).sendKeys("admin@123");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();

		WebElement MyAccount = driver.findElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(MyAccount).perform();
		driver.findElement(By.linkText("Desktops")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//span[@class='price actual-price' and text()='1800.00']/ancestor::div[@class='add-info']//input[@class='button-2 product-box-add-to-cart-button']"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.name("product_attribute_74_5_26")).click();
		String act=driver.findElement(By.className("price-value-74")).getText();
		String exp = "1800.00";
		Assert.assertEquals(act, exp);
		driver.findElement(By.xpath("(//input[@id='add-to-cart-button-74'])[1]")).click();	
		Thread.sleep(3000);
		String Actual=driver.findElement(By.xpath("(//p[@class='content'])[1]")).getText();
		String Exp= "The product has been added to your shopping cart";
		Assert.assertEquals(Actual,Exp);
		Thread.sleep(3000);
		
		
		
		
		
		

	}
	
	@Test(priority=1)
	public void GoToShoppingCart(){
		driver.findElement(By.linkText("Shopping cart")).click();
		String Act_Val = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
		String Exp_Val = "1800.00";
		Assert.assertEquals(Act_Val,Exp_Val);
		
		driver.findElement(By.xpath("//span[normalize-space()='Qty.:']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[normalize-space()='Qty.:']/following-sibling::input")).sendKeys("2");
		driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
		
		String Act_Val_1 = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
		System.out.println(Act_Val_1);
		String Exp_Val_1= "3600.00";
		Assert.assertEquals(Act_Val_1,Exp_Val_1);
		
		String total_val = driver.findElement(By.xpath("//span[@class='product-price'][normalize-space()='3600.00']")).getText();
		Assert.assertEquals(total_val,Act_Val_1);
		
		driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
		driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
		
		String Exp_Msg = driver.findElement(By.xpath("//div[@class='order-summary-content']")).getText();
		String Act_Msg = "Your Shopping Cart is empty!";
		Assert.assertEquals(Exp_Msg,Act_Msg);
	}

	@AfterTest
	public void close() {
		driver.quit();
	}

	@BeforeTest
	public void launch() {
		driver = new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();

	}

}
