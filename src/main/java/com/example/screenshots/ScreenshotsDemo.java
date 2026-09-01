package com.example.screenshots;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * SCREENSHOTS IN SELENIUM - Comprehensive Guide
 * 
 * What are Screenshots?
 * - Visual evidence of test execution
 * - Help debug failures
 * - Document test scenarios
 * - Part of test reports
 * 
 * When to take Screenshots:
 * 1. On test failure
 * 2. On specific steps
 * 3. Before assertions
 * 4. On error/exception
 * 5. For documentation
 */

public class ScreenshotsDemo {
    static WebDriver driver;
    private static final String SCREENSHOTS_DIR = "screenshots/";
    private static final DateTimeFormatter dateFormatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
    
    public static void main(String[] args) {
        driver = new ChromeDriver();
        try {
            System.out.println("===== SCREENSHOTS DEMONSTRATION =====");
            
            driver.navigate().to("https://www.google.com");
            
            // Method 1: Basic screenshot
            System.out.println("\n--- Method 1: Basic Screenshot ---");
            takeSimpleScreenshot("google_homepage");
            
            // Method 2: Screenshot with timestamp
            System.out.println("\n--- Method 2: Screenshot with Timestamp ---");
            takeScreenshotWithTimestamp("google_search");
            
            // Method 3: Screenshot of specific element
            System.out.println("\n--- Method 3: Element Screenshot ---");
            takeElementScreenshot("logo");
            
            // Method 4: Full page screenshot
            System.out.println("\n--- Method 4: Full Page Screenshot ---");
            takeFullPageScreenshot("full_page");
            
            // Method 5: Screenshot with description
            System.out.println("\n--- Method 5: Screenshot with Description ---");
            takeScreenshotWithDescription("test_step_1", "User navigated to Google homepage");
            
        } finally {
            driver.quit();
        }
    }
    
    // ===== BASIC SCREENSHOT METHODS =====
    
    /**
     * Take simple screenshot
     */
    static void takeSimpleScreenshot(String fileName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            
            File destination = new File(SCREENSHOTS_DIR + fileName + ".png");
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
        }
    }
    
    /**
     * Take screenshot with automatic timestamp
     */
    static void takeScreenshotWithTimestamp(String testName) {
        try {
            String timestamp = LocalDateTime.now().format(dateFormatter);
            String fileName = testName + "_" + timestamp + ".png";
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
        }
    }
    
    /**
     * Take screenshot of specific element
     */
    static void takeElementScreenshot(String elementIdentifier) {
        try {
            WebElement element = driver.findElement(By.tagName("img")); // Google logo
            
            // Get element screenshot
            File source = element.getScreenshotAs(OutputType.FILE);
            String fileName = elementIdentifier + ".png";
            File destination = new File(SCREENSHOTS_DIR + fileName);
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Element screenshot saved: " + fileName);
        } catch (NoSuchElementException e) {
            System.out.println("✗ Element not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
        }
    }
    
    /**
     * Take full page screenshot (including scroll area)
     */
    static void takeFullPageScreenshot(String fileName) {
        try {
            // Scroll to top
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollTo(0, 0);");
            
            // Get page dimensions
            Long pageHeight = (Long) jsExecutor.executeScript("return document.body.scrollHeight;");
            Long pageWidth = (Long) jsExecutor.executeScript("return document.body.scrollWidth;");
            
            // Set window to full page size
            driver.manage().window().setSize(new Dimension(pageWidth.intValue(), pageHeight.intValue()));
            
            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fileName + ".png");
            
            FileUtils.copyFile(source, destination);
            
            System.out.println("✓ Full page screenshot saved: " + fileName + ".png");
            System.out.println("  Dimensions: " + pageWidth + "x" + pageHeight);
            
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
        }
    }
    
    /**
     * Take screenshot with description (for documentation)
     */
    static void takeScreenshotWithDescription(String fileName, String description) {
        try {
            String timestamp = LocalDateTime.now().format(dateFormatter);
            String fullFileName = fileName + "_" + timestamp + ".png";
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + fullFileName);
            
            FileUtils.copyFile(source, destination);
            
            // Log the description
            System.out.println("✓ Screenshot: " + fullFileName);
            System.out.println("  Description: " + description);
            System.out.println("  Timestamp: " + timestamp);
            
        } catch (IOException e) {
            System.out.println("✗ Screenshot failed: " + e.getMessage());
        }
    }
    
    // ===== SCREENSHOT UTILITY CLASS =====
    
    static class ScreenshotUtil {
        private static final String SCREENSHOTS_PATH = "test-reports/screenshots/";
        private static final DateTimeFormatter dateFormat = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        
        static {
            // Create screenshots directory if not exists
            new File(SCREENSHOTS_PATH).mkdirs();
        }
        
        /**
         * Take screenshot on failure
         */
        static String takeFailureScreenshot(String testName) {
            return takeScreenshot(testName + "_FAILURE", "Test failure detected");
        }
        
        /**
         * Take screenshot on exception
         */
        static String takeExceptionScreenshot(String testName, Exception e) {
            return takeScreenshot(testName + "_EXCEPTION", 
                "Exception: " + e.getClass().getSimpleName());
        }
        
        /**
         * Take screenshot for documentation
         */
        static String takeDocumentationScreenshot(String description) {
            return takeScreenshot("DOC_" + description, description);
        }
        
        /**
         * Core screenshot method
         */
        static String takeScreenshot(String screenName, String description) {
            try {
                String timestamp = LocalDateTime.now().format(dateFormat);
                String fileName = screenName + "_" + timestamp + ".png";
                
                TakesScreenshot screenshot = (TakesScreenshot) ScreenshotUtil.driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                File destination = new File(SCREENSHOTS_PATH + fileName);
                
                FileUtils.copyFile(source, destination);
                
                System.out.println("✓ Screenshot taken: " + fileName);
                if (description != null && !description.isEmpty()) {
                    System.out.println("  Description: " + description);
                }
                
                return destination.getAbsolutePath();
                
            } catch (IOException e) {
                System.out.println("✗ Screenshot failed: " + e.getMessage());
                return null;
            }
        }
        
        // Static WebDriver reference
        static WebDriver driver;
        
        static void setDriver(WebDriver webDriver) {
            driver = webDriver;
        }
    }
    
    // ===== SCREENSHOT WITH LOGGING =====
    
    static class ScreenshotLogger {
        private WebDriver driver;
        private String testName;
        private List<String> screenshotList = new ArrayList<>();
        
        public ScreenshotLogger(WebDriver driver, String testName) {
            this.driver = driver;
            this.testName = testName;
        }
        
        public void captureStep(String stepName) {
            String fileName = testName + "_step_" + screenshotList.size() + "_" + stepName;
            String path = takeAndLogScreenshot(fileName, "Step: " + stepName);
            if (path != null) {
                screenshotList.add(path);
            }
        }
        
        public void captureOnFailure(String failureReason) {
            String fileName = testName + "_FAILURE_" + System.currentTimeMillis();
            String path = takeAndLogScreenshot(fileName, "Failure: " + failureReason);
            if (path != null) {
                screenshotList.add(path);
            }
        }
        
        public void captureOnException(Exception e) {
            String fileName = testName + "_EXCEPTION_" + e.getClass().getSimpleName();
            String path = takeAndLogScreenshot(fileName, "Exception: " + e.getMessage());
            if (path != null) {
                screenshotList.add(path);
            }
        }
        
        private String takeAndLogScreenshot(String fileName, String description) {
            try {
                String timestamp = LocalDateTime.now().format(dateFormatter);
                String fullFileName = fileName + "_" + timestamp + ".png";
                
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                File destination = new File(SCREENSHOTS_DIR + fullFileName);
                
                FileUtils.copyFile(source, destination);
                
                System.out.println("📸 Screenshot: " + fullFileName);
                System.out.println("   " + description);
                
                return destination.getAbsolutePath();
                
            } catch (IOException e) {
                System.out.println("✗ Screenshot failed: " + e.getMessage());
                return null;
            }
        }
        
        public List<String> getAllScreenshots() {
            return new ArrayList<>(screenshotList);
        }
        
        public void printReport() {
            System.out.println("\n📊 Screenshot Report for Test: " + testName);
            System.out.println("Total screenshots: " + screenshotList.size());
            for (int i = 0; i < screenshotList.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + screenshotList.get(i));
            }
        }
    }
    
    // ===== ADVANCED: SCREENSHOT WITH ANNOTATIONS =====
    
    static class AnnotatedScreenshot {
        private WebDriver driver;
        private WebElement highlightElement;
        
        public AnnotatedScreenshot(WebDriver driver) {
            this.driver = driver;
        }
        
        /**
         * Highlight element and take screenshot
         */
        public String captureWithHighlight(WebElement element, String fileName) {
            try {
                // Highlight element
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);
                
                // Take screenshot
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                File destination = new File(SCREENSHOTS_DIR + fileName + "_highlighted.png");
                
                FileUtils.copyFile(source, destination);
                
                System.out.println("✓ Annotated screenshot saved: " + fileName);
                
                // Remove highlight
                jsExecutor.executeScript("arguments[0].style.border='';", element);
                
                return destination.getAbsolutePath();
                
            } catch (IOException e) {
                System.out.println("✗ Annotated screenshot failed: " + e.getMessage());
                return null;
            }
        }
    }
    
    // ===== BEST PRACTICES =====
    
    static void bestPractices() {
        System.out.println("\n===== SCREENSHOT BEST PRACTICES =====");
        System.out.println("1. WHEN TO CAPTURE:");
        System.out.println("   ✓ On test failure");
        System.out.println("   ✓ On exception/error");
        System.out.println("   ✓ On assertion failure");
        System.out.println("   ✓ Before critical actions");
        System.out.println("   ✓ For documentation");
        System.out.println("   ✗ After every single action (too many files)");
        System.out.println("");
        System.out.println("2. FILE NAMING:");
        System.out.println("   - Use descriptive names");
        System.out.println("   - Include timestamp for uniqueness");
        System.out.println("   - Include test name or step");
        System.out.println("   - Format: testname_stepdescription_timestamp.png");
        System.out.println("");
        System.out.println("3. DIRECTORY STRUCTURE:");
        System.out.println("   screenshots/");
        System.out.println("   ├── 2024-01-15/");
        System.out.println("   │   ├── testLoginSuccess/");
        System.out.println("   │   └── testLoginFailure/");
        System.out.println("   └── 2024-01-16/");
        System.out.println("");
        System.out.println("4. STORAGE & MANAGEMENT:");
        System.out.println("   - Store in separate directory");
        System.out.println("   - Archive old screenshots");
        System.out.println("   - Don't include sensitive data");
        System.out.println("   - Clean up regularly");
        System.out.println("");
        System.out.println("5. INTEGRATION WITH REPORTS:");
        System.out.println("   - Link screenshots in test reports");
        System.out.println("   - Include path in test logs");
        System.out.println("   - Generate HTML report with screenshots");
        System.out.println("   - Add to Allure or TestNG reports");
        System.out.println("");
        System.out.println("6. PERFORMANCE:");
        System.out.println("   - Use PNG format (smaller than BMP)");
        System.out.println("   - Compress images for storage");
        System.out.println("   - Don't take screenshot for every action");
        System.out.println("   - Consider screenshot frequency in CI/CD");
    }
}
