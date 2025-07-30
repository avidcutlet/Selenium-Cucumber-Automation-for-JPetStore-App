package com.test.demo_petstore.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String fileNamePrefix) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "reports/screenshots/" + fileNamePrefix + "_" + timestamp + ".png";
            FileHandler.copy(src, new File(filePath));
            System.out.println("[INFO] Screenshot saved: " + filePath);
        } catch (IOException io) {
            System.out.println("[ERROR] Failed to capture screenshot: " + io.getMessage());
        }
    }
}