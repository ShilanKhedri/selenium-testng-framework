package com.shilan.listeners;

import com.shilan.tests.BaseTest;
import com.shilan.utils.ExtentManager;
import com.shilan.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());

        if (screenshotPath != null) {
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}