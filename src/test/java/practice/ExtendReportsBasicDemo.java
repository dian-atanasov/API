package practice;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReportsBasicDemo {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	WebDriver driver=null;
	String projectPath;
	String projectPath_Pictures;
	@BeforeSuite
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter("extent222.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);	
	}
	
	@BeforeTest
	public void setUpTest() {
	    projectPath = System.getProperty("user.dir");
	    //projectPath_Pictures = System.getProperty("user.dir");
	    System.setProperty("webdriver.chrome.driver", projectPath + "/Drivers/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	}

	@Test
	public void test1() throws Exception {
	    ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
	   
	    driver.get("https://google.com");
	    test.pass("Navigated to google.com");
	    
	    test.log(Status.INFO, "This step shows usage of log(status, details)");
	    test.info("This step shows usage of info(details)");
	    
	    String screenshotPath = projectPath + "/Pictures/screenshot111.png";
	    captureScreenshot(screenshotPath);
	    
	    test.pass("details111", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    test.addScreenCaptureFromPath(screenshotPath);
	}

	@Test
	public void test2() throws Exception {
	    ExtentTest test = extent.createTest("MySecondTest", "Sample description");
	    
	    test.log(Status.INFO, "This step shows usage of log(status, details)");
	    test.info("This step shows usage of info(details)");
	    
	    String screenshotPath = projectPath + "/Pictures/screenshot222.png";
	    captureScreenshot(screenshotPath);
	    
	    test.pass("details222", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    test.pass("OK", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    test.addScreenCaptureFromPath(screenshotPath);
	}

	// Method to capture screenshot
	private void captureScreenshot(String screenshotPath) {
	    try {
	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(srcFile, new File(screenshotPath));
	    } catch (IOException e) {
	        System.out.println("Exception while taking screenshot: " + e.getMessage());
	    }
	}
	
	@AfterTest
	public void tearDown() {
	    driver.close();
	    extent.flush();
	}

}
