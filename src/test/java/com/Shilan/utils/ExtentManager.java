package com.Shilan.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
            sparkReporter.config().setReportName("Automation Test Results");
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Framework", "TestNG - Selenium");
            extent.setSystemInfo("Author", "Shilan");
        }
        return extent;
    }

    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest test = getExtentReports().createTest(testName);
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}