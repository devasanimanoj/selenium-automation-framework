package com.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.example.utils.AssertionUtil;
import com.example.utils.LoggerUtil;
import com.example.utils.ScreenshotUtil;
import java.time.Duration;

/**
 * BASE TEST CLASS
 * 
 * Abstract base class for all test classes
 * Provides:
 * - WebDriver initialization and cleanup
 * - Setup/teardown for each test
 * - Pre-configured utilities (logging, assertions, screenshots)
 * - Common wait times and configurations
 * 
 * Usage:
 * public class LoginTest extends BaseTest {
 *     @Test
 *     public void testValidLogin() {
 *         LoggerUtil.testStart("testValidLogin");
 *         
 *         // Test code here
 *         driver.get(BASE_URL + "/login");
 *         // ...
 *         
 *         AssertionUtil.assertEquals(actualTitle, expectedTitle, "Title mismatch");
 *         LoggerUtil.testEnd("testValidLogin", true);
 *     }
 * }
 */

public class BaseTest {
    
    // ===== PROTECTED FIELDS (available in child test classes) =====
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;
    
    // ===== CONFIGURATION CONSTANTS =====
    
    protected static final String BASE_URL = "http://localhost:8080";  // Change as needed
    protected static final int IMPLICIT_WAIT = 10;
    protected static final int EXPLICIT_WAIT = 15;
    protected static final int THREAD_SLEEP = 2000;
    
    // ===== SUITE LEVEL SETUP/TEARDOWN =====
    
    @BeforeSuite
    public void beforeSuite() {
        LoggerUtil.info("════════════════════════════════════════════════════");
        LoggerUtil.info("TEST SUITE STARTED");
        LoggerUtil.info("════════════════════════════════════════════════════");
        LoggerUtil.info("Browser: Chrome");
        LoggerUtil.info("Base URL: " + BASE_URL);
        LoggerUtil.info("Implicit Wait: " + IMPLICIT_WAIT + "s");
        LoggerUtil.info("Explicit Wait: " + EXPLICIT_WAIT + "s");
    }
    
    @AfterSuite
    public void afterSuite() {
        LoggerUtil.info("════════════════════════════════════════════════════");
        LoggerUtil.info("TEST SUITE COMPLETED");
        LoggerUtil.info("════════════════════════════════════════════════════");
    }
    
    // ===== TEST LEVEL SETUP/TEARDOWN =====
    
    @BeforeTest
    public void beforeTest() {
        LoggerUtil.info("Test setup phase started");
    }
    
    @AfterTest
    public void afterTest() {
        LoggerUtil.info("Test teardown phase started");
    }
    
    // ===== CLASS LEVEL SETUP/TEARDOWN =====
    
    @BeforeClass
    public void beforeClass() {
        LoggerUtil.info("Class level setup phase started");
    }
    
    @AfterClass
    public void afterClass() {
        LoggerUtil.info("Class level teardown phase started");
    }
    
    // ===== METHOD LEVEL SETUP/TEARDOWN =====
    
    @BeforeMethod
    public void beforeMethod() {
        initializeWebDriver();
        initializeWaits();
        initializeAssertions();
        initializeScreenshots();
    }
    
    @AfterMethod
    public void afterMethod() {
        cleanupResources();
    }
    
    // ===== WEBDRIVER INITIALIZATION =====
    
    /**
     * Initialize WebDriver with Chrome browser
     */
    protected void initializeWebDriver() {
        try {
            ChromeOptions options = getDefaultChromeOptions();
            driver = new ChromeDriver(options);
            
            LoggerUtil.info("✓ WebDriver initialized");
            
        } catch (Exception e) {
            LoggerUtil.error("Failed to initialize WebDriver");
            LoggerUtil.exception(e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }
    
    /**
     * Get default Chrome options
     */
    protected ChromeOptions getDefaultChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        
        // Basic options
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        
        // Headless mode (comment out if you want to see the browser)
        // options.addArguments("--headless");
        
        // Disable images for faster loading (optional)
        // options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
        return options;
    }
    
    /**
     * Initialize explicit waits
     */
    protected void initializeWaits() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        
        LoggerUtil.info("✓ Waits initialized");
    }
    
    /**
     * Initialize soft assertions
     */
    protected void initializeAssertions() {
        softAssert = new SoftAssert();
        LoggerUtil.info("✓ SoftAssert initialized");
    }
    
    /**
     * Initialize screenshot utility
     */
    protected void initializeScreenshots() {
        ScreenshotUtil.setDriver(driver);
        LoggerUtil.info("✓ Screenshot utility initialized");
    }
    
    // ===== CLEANUP =====
    
    /**
     * Clean up resources after test
     */
    protected void cleanupResources() {
        try {
            if (driver != null) {
                driver.quit();
                LoggerUtil.info("✓ WebDriver closed");
            }
        } catch (Exception e) {
            LoggerUtil.error("Error closing WebDriver: " + e.getMessage());
        }
    }
    
    // ===== HELPER METHODS FOR TESTS =====
    
    /**
     * Navigate to URL
     */
    protected void navigateTo(String url) {
        try {
            LoggerUtil.navigateAction(url);
            driver.navigate().to(url);
            LoggerUtil.info("✓ Navigated to: " + url);
        } catch (Exception e) {
            LoggerUtil.error("Failed to navigate to: " + url);
            LoggerUtil.exception(e);
            throw e;
        }
    }
    
    /**
     * Navigate to base URL
     */
    protected void navigateToBase() {
        navigateTo(BASE_URL);
    }
    
    /**
     * Wait and ignore exceptions
     */
    protected void sleep(long milliseconds) {
        try {
            LoggerUtil.waitAction("Sleep", milliseconds / 1000);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LoggerUtil.exception(e);
        }
    }
    
    /**
     * Take screenshot with test name and step
     */
    protected String captureScreenshot(String stepName) {
        String testName = getCurrentTestName();
        return ScreenshotUtil.takeScreenshot(testName, stepName);
    }
    
    /**
     * Take screenshot on failure
     */
    protected String captureFailureScreenshot(String reason) {
        String testName = getCurrentTestName();
        return ScreenshotUtil.takeFailureScreenshot(testName, reason);
    }
    
    /**
     * Take screenshot on exception
     */
    protected String captureExceptionScreenshot(Exception e) {
        String testName = getCurrentTestName();
        return ScreenshotUtil.takeExceptionScreenshot(testName, e);
    }
    
    /**
     * Get current test method name
     */
    protected String getCurrentTestName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
    
    /**
     * Assert that condition is true (hard assertion)
     */
    protected void assertThat(boolean condition, String message) {
        AssertionUtil.assertTrue(condition, message);
    }
    
    /**
     * Soft assert that condition is true
     */
    protected void softAssertThat(boolean condition, String message) {
        AssertionUtil.softAssertTrue(softAssert, condition, message);
    }
    
    /**
     * Assert equals (hard assertion)
     */
    protected void assertThatEquals(Object actual, Object expected, String message) {
        AssertionUtil.assertEquals(actual, expected, message);
    }
    
    /**
     * Soft assert equals
     */
    protected void softAssertThatEquals(Object actual, Object expected, String message) {
        AssertionUtil.softAssertEquals(softAssert, actual, expected, message);
    }
    
    /**
     * Report all soft assertions
     */
    protected void reportSoftAssertions() {
        softAssert.assertAll();
        LoggerUtil.info("✓ All soft assertions verified");
    }
    
    /**
     * Log test step
     */
    protected void logStep(String stepNumber, String description) {
        LoggerUtil.step(stepNumber, description);
    }
    
    /**
     * Log action
     */
    protected void logAction(String action) {
        LoggerUtil.action(action);
    }
    
    /**
     * Log verification
     */
    protected void logVerification(String verification, boolean result) {
        LoggerUtil.verification(verification, result);
    }
    
    /**
     * Log test data
     */
    protected void logData(String label, Object value) {
        LoggerUtil.data(label, value);
    }
    
    // ===== RETRY MECHANISM =====
    
    /**
     * Retry operation with exponential backoff
     */
    protected <T> T retryOperation(String operationName, RetryableOperation<T> operation, int maxAttempts) {
        int attempt = 0;
        long delay = 500; // Start with 500ms
        
        while (attempt < maxAttempts) {
            try {
                attempt++;
                LoggerUtil.action("Executing: " + operationName + " (Attempt " + attempt + "/" + maxAttempts + ")");
                
                T result = operation.execute();
                
                LoggerUtil.success(operationName + " completed successfully");
                return result;
                
            } catch (Exception e) {
                LoggerUtil.warning("Attempt " + attempt + " failed: " + e.getMessage());
                
                if (attempt < maxAttempts) {
                    LoggerUtil.info("Retrying after " + delay + "ms");
                    sleep(delay);
                    delay *= 2; // Exponential backoff
                } else {
                    LoggerUtil.error("All " + maxAttempts + " attempts failed");
                    throw new RuntimeException(operationName + " failed after " + maxAttempts + " attempts", e);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Retry operation with default attempts
     */
    protected <T> T retryOperation(String operationName, RetryableOperation<T> operation) {
        return retryOperation(operationName, operation, 3);
    }
    
    /**
     * Functional interface for retryable operations
     */
    @FunctionalInterface
    public interface RetryableOperation<T> {
        T execute() throws Exception;
    }
    
    // ===== CONDITIONAL EXECUTION =====
    
    /**
     * Execute operation if condition is true
     */
    protected void executeIf(boolean condition, String description, Runnable operation) {
        if (condition) {
            LoggerUtil.action("Executing: " + description);
            operation.run();
            LoggerUtil.success(description + " completed");
        } else {
            LoggerUtil.info("Skipping: " + description);
        }
    }
    
    /**
     * Skip test with reason
     */
    protected void skipTest(String reason) {
        LoggerUtil.testSkipped(getCurrentTestName(), reason);
        throw new org.testng.SkipException(reason);
    }
}
