package com.shilan.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot.");
            return null;
        }

        try {
            Path screenshotDir = Paths.get("screenshots");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            String fileName = screenshotName + "_" + System.currentTimeMillis() + ".png";
            Path targetPath = screenshotDir.resolve(fileName);

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            Files.copy(source.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toAbsolutePath().toString();

        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}