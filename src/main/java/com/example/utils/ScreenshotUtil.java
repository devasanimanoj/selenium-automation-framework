package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SCREENSHOT UTILITY CLASS
 * 
 * Reusable screenshot capture methods
 * Supports full page, element, and failure screenshots
 * Automatic timestamping and directory management
 * 
 * Usage:
 * ScreenshotUtil.setDriver(driver);
 * ScreenshotUtil.takeScreenshot("testName", "step description");
 * ScreenshotUtil.takeFailureScreenshot("testName", "failure reason");
 */

public class ScreenshotUtil {
    
    private static WebDriver driver;
    private static final String SCREENSHOTS_DIR = "test-reports/screenshots/";
    private static final DateTimeFormatter dateFormatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
    
    static {
        createScreenshotsDirectory();
    }
    
    /**
     * Set WebDriver instance for screenshot capture
     */
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    
    /**
     * Create screenshots directory if it doesn't exist
     */
    static void createScreenshotsDirectory() {
        File dir = new File(SCREENSHOTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("✓ Screenshots directory created: " + SCREENSHOTS_DIR);
        }
    }
    
    // ===== BASIC SCREENSHOT METHODS =====
    
    /**
     * Take screenshot with description
     */
    public static String takeScreenshot(String testName, String description) {
        try {
            if (driver == null) {
                System.out.println("✗ WebDriver not initialized");
                return null;
            }
            
            String timestamp = LocalDateTime.now().format(dateFormatter);
            String fileName = testName + "_" + timestamp + ".png";
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Screenshot: " + fileName);
            if (description != null && !description.isEmpty()) {
                System.out.println("  Description: " + description);
            }
            
            LoggerUtil.screenshot(fileName, description);
            
            return destination.getAbsolutePath();
            
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
            LoggerUtil.exception("Screenshot capture failed", e);
            return null;
        }
    }
    
    /**
     * Take screenshot without description
     */
    public static String takeScreenshot(String testName) {
        return takeScreenshot(testName, null);
    }
    
    // ===== SPECIFIC SCREENSHOT TYPES =====
    
    /**
     * Take screenshot on test failure
     */
    public static String takeFailureScreenshot(String testName, String failureReason) {
        try {
            String fileName = testName + "_FAILURE_" + LocalDateTime.now().format(dateFormatter) + ".png";
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("❌ Failure Screenshot: " + fileName);
            System.out.println("   Reason: " + failureReason);
            
            LoggerUtil.screenshotOnFailure(fileName, failureReason);
            
            return destination.getAbsolutePath();
            
        } catch (IOException e) {
            System.out.println("✗ Failure screenshot failed: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot on exception
     */
    public static String takeExceptionScreenshot(String testName, Exception e) {
        String exceptionType = e.getClass().getSimpleName();
        String fileName = testName + "_EXCEPTION_" + exceptionType + "_" + 
            LocalDateTime.now().format(dateFormatter) + ".png";
        
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("⚠️  Exception Screenshot: " + fileName);
            System.out.println("   Exception: " + exceptionType + " - " + e.getMessage());
            
            LoggerUtil.screenshot(fileName, "Exception: " + exceptionType);
            
            return destination.getAbsolutePath();
            
        } catch (IOException ioe) {
            System.out.println("✗ Exception screenshot failed: " + ioe.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot for documentation
     */
    public static String takeDocumentationScreenshot(String description) {
        String fileName = "DOC_" + description.replace(" ", "_") + "_" + 
            LocalDateTime.now().format(dateFormatter) + ".png";
        
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("📚 Documentation Screenshot: " + fileName);
            LoggerUtil.screenshot(fileName, "Documentation: " + description);
            
            return destination.getAbsolutePath();
            
        } catch (IOException e) {
            System.out.println("✗ Documentation screenshot failed: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take element screenshot
     */
    public static String takeElementScreenshot(WebElement element, String elementName) {
        try {
            String fileName = elementName + "_element_" + 
                LocalDateTime.now().format(dateFormatter) + ".png";
            
            File source = element.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Element Screenshot: " + fileName + " (" + elementName + ")");
            LoggerUtil.screenshot(fileName, "Element: " + elementName);
            
            return destination.getAbsolutePath();
            
        } catch (IOException e) {
            System.out.println("✗ Element screenshot failed: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take full page screenshot (scrollable area)
     */
    public static String takeFullPageScreenshot(String testName) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            
            // Get page dimensions
            Long pageHeight = (Long) jsExecutor.executeScript("return document.body.scrollHeight;");
            Long pageWidth = (Long) jsExecutor.executeScript("return document.body.scrollWidth;");
            
            // Store original window size
            Dimension originalSize = driver.manage().window().getSize();
            
            // Set window to full page size
            driver.manage().window().setSize(new Dimension(pageWidth.intValue(), pageHeight.intValue()));
            
            // Wait for resize
            Thread.sleep(500);
            
            // Take screenshot
            String fileName = testName + "_fullpage_" + 
                LocalDateTime.now().format(dateFormatter) + ".png";
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            // Restore original window size
            driver.manage().window().setSize(originalSize);
            
            System.out.println("✓ Full Page Screenshot: " + fileName);
            System.out.println("  Dimensions: " + pageWidth + "x" + pageHeight);
            LoggerUtil.screenshot(fileName, "Full page - " + pageWidth + "x" + pageHeight);
            
            return destination.getAbsolutePath();
            
        } catch (Exception e) {
            System.out.println("✗ Full page screenshot failed: " + e.getMessage());
            return null;
        }
    }
    
    // ===== HELPER METHODS =====
    
    /**
     * Get screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOTS_DIR;
    }
    
    /**
     * Get screenshot file name with timestamp
     */
    public static String generateFileName(String baseName) {
        return baseName + "_" + LocalDateTime.now().format(dateFormatter) + ".png";
    }
    
    /**
     * Delete screenshot file
     */
    public static boolean deleteScreenshot(String fileName) {
        try {
            File file = new File(SCREENSHOTS_DIR + fileName);
            if (file.exists()) {
                file.delete();
                System.out.println("✓ Screenshot deleted: " + fileName);
                return true;
            }
        } catch (Exception e) {
            System.out.println("✗ Failed to delete screenshot: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Get all screenshot files in directory
     */
    public static File[] getAllScreenshots() {
        File dir = new File(SCREENSHOTS_DIR);
        return dir.listFiles((d, name) -> name.endsWith(".png"));
    }
    
    /**
     * Clear all screenshots
     */
    public static void clearScreenshots() {
        try {
            File dir = new File(SCREENSHOTS_DIR);
            FileUtils.cleanDirectory(dir);
            System.out.println("✓ All screenshots cleared");
        } catch (IOException e) {
            System.out.println("✗ Failed to clear screenshots: " + e.getMessage());
        }
    }
    
    /**
     * Get screenshot count
     */
    public static int getScreenshotCount() {
        File[] screenshots = getAllScreenshots();
        return screenshots != null ? screenshots.length : 0;
    }
    
    /**
     * Get total size of all screenshots
     */
    public static long getScreenshotsTotalSize() {
        File dir = new File(SCREENSHOTS_DIR);
        return dir.exists() ? FileUtils.sizeOfDirectory(dir) : 0;
    }
    
    // ===== JAVASCRIPT EXECUTOR REFERENCE =====
    // Note: Move this to your WebDriver utility class
    interface JavascriptExecutor extends org.openqa.selenium.JavascriptExecutor {}
    
    interface Dimension extends org.openqa.selenium.Dimension {}
}
