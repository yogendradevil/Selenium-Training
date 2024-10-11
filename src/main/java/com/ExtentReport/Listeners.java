//package com.ExtentReport;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//
//public class Listeners implements ITestListener {
//	ExtentReports extent = ExtentReportBase.CreateExtentReport("dummy.html", "Firefox");
////	BaseClass cr = new BaseClass();
////	ExtentReports extent = BaseClass.CreateExtentReport();
//	ExtentTest test;
//	//This is the concept of ThreadSafe
//	// make it private static, so no other class will have access and when you are dealing with ThreadSafe, its good to have private
//	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
//
//	public void onFinish(ITestContext context) {
//		extent.flush();
//	}
//
//	public void onStart(ITestContext context) {
//		
//	}
//
//	public void onTestFailure(ITestResult result) {
//		extentTest.get().fail(result.getThrowable());
//		
//	}
//
//	public void onTestSkipped(ITestResult result) {
//
//	}
//
//	public void onTestStart(ITestResult result) {
//		test = extent.createTest(result.getMethod().getMethodName());
//		extentTest.set(test);
//	}
//
//	public void onTestSuccess(ITestResult result) {
//		extentTest.get().log(Status.PASS, "Test Successful");
//		
//	}
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//}
//



package com.ExtentReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {
    private ExtentReports extent = ExtentReportBase.CreateExtentReport("TestReport.html", "Firefox");
    private ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private WebDriver driver;

    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void onStart(ITestContext context) {
        // Any setup can be done here if needed
    }

    public void onTestFailure(ITestResult result) {
        test = extentTest.get();
        test.fail(result.getThrowable());

        // Capture screenshot on test failure
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            String screenshotPath = ExtentReportBase.getScreenhot(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.fail("Test failed, but unable to capture screenshot: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extentTest.get();
        test.log(Status.SKIP, "Test Skipped");
    }

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        test = extentTest.get();
        test.log(Status.PASS, "Test Successful");

        // Capture screenshot on test success
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            String screenshotPath = ExtentReportBase.getScreenhotSuccess(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.fail("Test passed, but unable to capture screenshot: " + e.getMessage());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optionally handle this scenario
    }
}