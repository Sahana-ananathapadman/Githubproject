package base;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class BaseTest {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String reportpath;
	
	
	@Parameters({"browsername","url","reportpath"})
	@BeforeSuite
	public void beforeSuite(@Optional("chrome") String browsername,
			@Optional("http://zero.webappsecurity.com")String url,
			@Optional("/test-output/") String path)
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");	
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		reportpath=path+ts.toString().replace('-', '_').replace(' ', '_').replace(':', '_').replace('.', '_')+"/";
		createReport();
	}
	
	public void createReport() {
		//Configure the location to save the report
		//htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestReport.html");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + reportpath+"TestReport.html");
        // Create an object of Extent Reports
		report = new ExtentReports();
		//Attach the ExtentHtmlReporter object to ExtentReports class and provide dashboard details
		report.attachReporter(htmlReporter);
		report.setSystemInfo("User Name", "Naveen S");
        report.setSystemInfo("Environment", "Production");
		report.setSystemInfo("OS", "Windows 10");
		//Provide report properties like title, report name and theme
		htmlReporter.config().setDocumentTitle("Test Report"); 
		htmlReporter.config().setReportName("Zero Bank- Regression Test"); 
		htmlReporter.config().setTheme(Theme.STANDARD);			
	}
	
	public String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
		//Capture the screenshot and return the path of the screenshot.
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + reportpath + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//To capture screenshot path and store the path of the screenshot in the string "screenshotpath"
			//Calling takeScreenShot method to take screenshot
			String screenshotpath = takeScreenShot(driver, result.getName());
			//Add it in report using fail method and add screenshot using addScreenCaptureFromPath method
			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotpath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
	}
	
	@AfterSuite
	public void afterSuite()
	{
		report.flush();
		driver.quit();
	}
}
