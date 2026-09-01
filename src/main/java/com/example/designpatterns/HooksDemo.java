package com.example.designpatterns;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * HOOKS IN TEST AUTOMATION (TESTNG & JUNIT)
 * 
 * What are Hooks?
 * - Setup methods that run before tests
 * - Teardown methods that run after tests
 * - Used for initialization and cleanup
 * - Part of testing framework (TestNG, JUnit)
 * 
 * Why use Hooks?
 * 1. Setup: Initialize WebDriver, navigate to URL, login
 * 2. Cleanup: Close browser, delete test data, logout
 * 3. Reduce code duplication
 * 4. Ensure consistent test state
 * 5. Easy to maintain and modify
 * 
 * TestNG Annotations (in order):
 * @BeforeSuite - runs once before all tests
 * @BeforeTest - runs before each test tag
 * @BeforeClass - runs once before each test class
 * @BeforeMethod - runs before each test method
 * @Test - the test method itself
 * @AfterMethod - runs after each test method
 * @AfterClass - runs once after each test class
 * @AfterTest - runs after each test tag
 * @AfterSuite - runs once after all tests
 */

// ===== TESTNG HOOKS IMPLEMENTATION =====

public class HooksDemo {
    WebDriver driver;
    WebDriverWait wait;

    // ===== SUITE-LEVEL HOOKS =====
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("🔧 BEFORE SUITE: Initializing test environment");
        // Initialize test data
        // Start database
        // Create test accounts
        // Setup logging
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("🧹 AFTER SUITE: Cleaning up test environment");
        // Delete test data
        // Close database
        // Generate report
        // Send notifications
    }

    // ===== CLASS-LEVEL HOOKS =====
    @BeforeClass
    public void beforeClass() {
        System.out.println("🔧 BEFORE CLASS: Setting up for test class");
        // Setup class-specific resources
        // Create test fixtures
    }

    @AfterClass
    public void afterClass() {
        System.out.println("🧹 AFTER CLASS: Cleaning up after test class");
        // Cleanup class-specific resources
    }

    // ===== METHOD-LEVEL HOOKS (Most Common) =====
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("🔧 BEFORE METHOD: Setting up WebDriver");
        
        // Initialize WebDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Maximize window
        driver.manage().window().maximize();
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        // Navigate to base URL
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        
        System.out.println("✓ WebDriver initialized and navigated to base URL");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("🧹 AFTER METHOD: Cleaning up WebDriver");
        
        // Take screenshot if test failed (optional)
        // captureScreenshot();
        
        // Delete test data
        // deleteTestData();
        
        // Close WebDriver
        if (driver != null) {
            driver.quit();
            System.out.println("✓ WebDriver closed");
        }
    }

    // ===== TEST METHODS =====

    @Test
    public void testLoginSuccess() {
        System.out.println("\n📝 TEST: Successful Login");
        // Test code here
        // WebDriver is already initialized by @BeforeMethod
    }

    @Test
    public void testLoginFailure() {
        System.out.println("\n📝 TEST: Failed Login");
        // Test code here
        // WebDriver is already initialized by @BeforeMethod
    }

    @Test
    public void testLogout() {
        System.out.println("\n📝 TEST: Logout");
        // Test code here
        // WebDriver is already initialized by @BeforeMethod
    }
}

// ===== ADVANCED HOOKS WITH PARAMETERS =====

class HooksWithParameters {
    WebDriver driver;

    /**
     * Get test method name for logging
     */
    @BeforeMethod
    public void beforeMethodWithName(java.lang.reflect.Method method) {
        System.out.println("🔧 BEFORE METHOD: " + method.getName());
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethodWithName(java.lang.reflect.Method method) {
        System.out.println("🧹 AFTER METHOD: " + method.getName());
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Get test result for conditional cleanup
     */
    @AfterMethod
    public void afterMethodWithResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("❌ TEST FAILED: " + result.getName());
            // captureScreenshot();
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("✅ TEST PASSED: " + result.getName());
        }
        
        if (driver != null) {
            driver.quit();
        }
    }
}

// ===== HOOKS WITH CONDITIONAL EXECUTION =====

class HooksWithConditions {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethodWithGroup(java.lang.reflect.Method method) {
        // Check if method has certain groups
        Test testAnnotation = method.getAnnotation(Test.class);
        
        String[] groups = testAnnotation.groups();
        if (java.util.Arrays.asList(groups).contains("login")) {
            System.out.println("🔧 Setting up for Login tests");
            driver = new ChromeDriver();
            driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        }
    }
}

// ===== BASE CLASS FOR HOOKS =====

abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.out.println("🔧 Setting up test");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to(getBaseURL());
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("🧹 Tearing down test");
        if (driver != null) {
            driver.quit();
        }
    }

    // Override this in subclasses
    protected String getBaseURL() {
        return "https://practicetestautomation.com/";
    }
}

// ===== TESTNG LISTENER (ALTERNATIVE APPROACH) =====

class TestNGListener implements ITestListener {
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔧 Test Started: " + result.getName());
        driver = new ChromeDriver();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        // captureScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️  Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⚠️  Test Failed But Within Success %: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Test Suite Finished");
        if (driver != null) {
            driver.quit();
        }
    }
}

// ===== BEST PRACTICES FOR HOOKS =====

class HooksBestPractices {
    static void showBestPractices() {
        System.out.println("\n===== HOOKS BEST PRACTICES =====");
        
        System.out.println("\n1. ORGANIZATION:");
        System.out.println("   - Keep hooks in base class or separate class");
        System.out.println("   - One @BeforeMethod, one @AfterMethod");
        System.out.println("   - Use inherited hooks in test classes");
        
        System.out.println("\n2. INITIALIZATION:");
        System.out.println("   - Initialize WebDriver in @BeforeMethod");
        System.out.println("   - Navigate to base URL");
        System.out.println("   - Set implicit/explicit waits");
        System.out.println("   - Set browser preferences");
        
        System.out.println("\n3. CLEANUP:");
        System.out.println("   - Always close WebDriver in @AfterMethod");
        System.out.println("   - Delete temporary test data");
        System.out.println("   - Clear browser cache/cookies if needed");
        System.out.println("   - Log cleanup actions");
        
        System.out.println("\n4. ERROR HANDLING:");
        System.out.println("   - Use try-catch in hooks");
        System.out.println("   - Handle cases where WebDriver is null");
        System.out.println("   - Log errors appropriately");
        
        System.out.println("\n5. LOGGING:");
        System.out.println("   - Log hook execution");
        System.out.println("   - Include test method name");
        System.out.println("   - Include result status");
        System.out.println("   - Use appropriate log levels");
        
        System.out.println("\n6. PERFORMANCE:");
        System.out.println("   - Minimize setup time");
        System.out.println("   - Cache reusable objects");
        System.out.println("   - Use appropriate hook level (@BeforeSuite for global, @BeforeMethod for per-test)");
        
        System.out.println("\n7. DEPENDENCIES:");
        System.out.println("   - Keep hooks independent");
        System.out.println("   - Don't rely on test execution order");
        System.out.println("   - Each test should be runnable independently");
    }
}

// ===== JUNIT 5 HOOKS (ALTERNATIVE) =====

class JUnit5HooksExample {
    // Note: This requires JUnit 5 instead of TestNG
    
    // @BeforeEach (similar to @BeforeMethod)
    // @AfterEach (similar to @AfterMethod)
    // @BeforeAll (similar to @BeforeSuite, static method)
    // @AfterAll (similar to @AfterSuite, static method)
}

// ===== EXAMPLE: COMPLETE TEST WITH HOOKS =====

class LoginTest extends BaseTest {
    
    @Test(description = "Test successful login")
    public void testSuccessfulLogin() {
        System.out.println("\n📝 Testing: Successful Login");
        // WebDriver is already initialized by setUp()
        
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");
        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");
        
        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("dashboard"));
        System.out.println("✅ Login test passed");
        
        // tearDown() will be called automatically
    }
    
    @Test(description = "Test failed login")
    public void testFailedLogin() {
        System.out.println("\n📝 Testing: Failed Login");
        // WebDriver is already initialized by setUp()
        
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("invalid");
        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("invalid");
        
        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        
        Thread.sleep(1000);
        System.out.println("✅ Login failed test passed");
        
        // tearDown() will be called automatically
    }
}
