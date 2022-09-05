package com.rkv.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.regex.Pattern;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentReportManager.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest( result.getMethod().getMethodName());
        extentTest.set(test);
        String testClassFull[] =result.getInstanceName().split(Pattern.quote("."));
        String testClassName = testClassFull[testClassFull.length-1];
        test.assignCategory(testClassName);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String logText = "<b>Test method " + result.getMethod().getMethodName() + " Successful</b>";
        Markup mark = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, mark);

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String exceptionMsg = result.getThrowable().toString();
        extentTest.get().fail("<details><summary><b><font color=red>" + " Exception occurred click to see the details:"
                + "</font></b></summary>" + exceptionMsg.replaceAll(",", "<br>") + "</details> \n");

        String logText = "<b>Test method " + result.getMethod().getMethodName() + " Failed</b>";
        Markup mark = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, mark);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>Test method " + result.getMethod().getMethodName() + " Skipped</b>";
        Markup mark = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, mark);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) extent.flush();
    }

}
