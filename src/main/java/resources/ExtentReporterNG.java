package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getExtentReoprtObject() {
		
		String filePath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter report= new ExtentSparkReporter(filePath);
		report.config().setDocumentTitle("Tested By Ravi");
		report.config().setReportName("Web Automation Results");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Ravi");
		return extent;
	}
}
