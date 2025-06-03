package reportsdemo.ExtentReports;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {

	ExtentReports extent;

	@BeforeTest
	public void Config() {

		String reportDir = System.getProperty("user.dir") + File.separator + "reports";
		new File(reportDir).mkdirs();

		String path = reportDir + File.separator + "index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Kubra D.");
	}

	@Test
	public void initialDemo() {

		ExtentTest test = extent.createTest("Initial Demo");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		try {
			driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
			String title = driver.getTitle();
			System.out.println(title);

			test.pass("Navigated to " + title);

		} catch (Exception e) {
			test.fail("Test failed: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
	}
}
