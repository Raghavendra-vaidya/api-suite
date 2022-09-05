package com.rkv.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;
    public  static  ExtentReports createInstance(){
        final String directory = System.getProperty("user.dir") + "/reports/";
        new File(directory).mkdirs();
        String path = directory+"APITestResults.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Reports");
        sparkReporter.config().setReportName("Public API Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimelineEnabled(false);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization","rkv");
        extent.setSystemInfo("Scope","Public apis");
        extent.setSystemInfo("Image","maven:3.8-jdk-8-slim");
        extent.attachReporter(sparkReporter);
        return extent;


    }
}
