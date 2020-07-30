package listeners;

import drivermanager.DriverManager;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Utils;

import java.io.IOException;

public class ScreenshotListenter implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            Utils.captureScreenShot(result.getName(), DriverManager.getDriver(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Utils.captureScreenShot(result.getName(), DriverManager.getDriver(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
