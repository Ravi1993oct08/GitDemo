package baseComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExtentReporterNG;

public class Listeners extends BaseClass implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getExtentReoprtObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// System.out.println("Test method " + result.getMethod().getMethodName() + "
		// was skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// System.out.println("Test method " + result.getMethod().getMethodName() + "
		// failed but is within the success percentage.");
	}

	@Override
	public void onStart(ITestContext context) {
		// System.out.println("Starting test suite: " + context.getSuite().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("Finished test suite: " + context.getSuite().getName());
		extent.flush();

	}
}
