package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import utils.Utils;

import java.io.IOException;

public class HtmlReports {
    private static ExtentReports extentReport;
    private static HtmlReports instance;

    private HtmlReports()  {
        extentReport = new ExtentReports();
        ExtentReporter reporter = null;
        try {
            reporter = new ExtentHtmlReporter(Utils.reportFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentReport.attachReporter(reporter);
    }

    public static ExtentTest createTest(String testName) {
        if(instance == null){
            instance =  new HtmlReports();
        }
        return extentReport.createTest(testName);
    }

    public static void flush() {
        extentReport.flush();
    }




}
