package com.assignments;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class notesAssigment {
    static WebDriver driver;

    // Uncomment this method if you want to use cross-browser testing
    /*
    public static WebDriver crossBrowserTesting(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }
        return driver;
    }
    */

    @Test
    public void login_to_app() {
        // Set up EdgeDriver path if not set in the system PATH
         // Update with your path
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice.expandtesting.com/notes/app/login");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ayanakash936@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).clear();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Epsilon@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Optional: Wait for some time to ensure the next page loads
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void delete() throws InterruptedException {
        boolean noNotes = driver.getPageSource().contains("You don't have any notes in all categories");

        if (!noNotes) {
            List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[contains(.,'Delete')]"));

            for (WebElement deleteButton : deleteButtons) {
                deleteButton.click(); // Click the Delete button for the note
                
                // Optional wait for the confirmation button to appear
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Delete']")).click(); // Confirm delete
                
                // Wait after delete to see if the note is removed
                Thread.sleep(2000);
            }
        }
    }

    @AfterTest
    public void post_condition() {
        if (driver != null) {
            driver.close();
        }
    }
}




