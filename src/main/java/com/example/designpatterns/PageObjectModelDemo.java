package com.example.designpatterns;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * PAGE OBJECT MODEL (POM) DESIGN PATTERN
 * 
 * What is POM?
 * - Object-oriented design pattern that creates page object classes
 * - Encapsulates page elements and page methods in single class
 * - Reduces code duplication and improves maintainability
 * - Creates reusable page objects that are easy to maintain
 * 
 * Advantages:
 * 1. Maintainability - locators in one place
 * 2. Readability - clean separation of page and test logic
 * 3. Reusability - page objects used across multiple tests
 * 4. Scalability - easy to add new pages/methods
 * 5. Debugging - easier to identify where issues are
 * 6. Flexibility - full control over element handling
 * 
 * Differences from Page Factory:
 * Page Factory:
 * - Uses @FindBy annotations
 * - Automatic element initialization
 * - Less flexible
 * 
 * POM:
 * - Manual element finding (more control)
 * - Elements found in methods
 * - More flexible and powerful
 * - Better for complex scenarios
 * 
 * When to use POM:
 * - Large test automation frameworks
 * - Complex applications with multiple pages
 * - Need fine-grained control over waits
 * - Custom element handling strategies
 * - Team needs maximum flexibility
 */
public class PageObjectModelLoginPage {
    // WebDriver instances
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators - defined as constants or methods
    private static final By USERNAME_LOCATOR = By.id("username");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON = By.id("submit");
    private static final By ERROR_MESSAGE = By.className("error-message");
    private static final By REMEMBER_ME = By.id("rememberMe");
    private static final By PAGE_TITLE = By.tagName("h1");

    // Constructor
    public PageObjectModelLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Verify page is loaded in constructor
        verifyPageIsLoaded();
    }

    // ===== PRIVATE HELPER METHODS =====

    /**
     * Find element with explicit wait
     */
    private WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Find visible element
     */
    private WebElement findVisibleElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Find clickable element
     */
    private WebElement findClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Verify page is loaded
     */
    private void verifyPageIsLoaded() {
        try {
            findVisibleElement(PAGE_TITLE);
            System.out.println("✓ Login page verified");
        } catch (Exception e) {
            throw new RuntimeException("Login page not loaded: " + e.getMessage());
        }
    }

    // ===== PUBLIC PAGE ACTION METHODS =====

    /**
     * Enter username
     */
    public PageObjectModelLoginPage enterUsername(String username) {
        WebElement usernameField = findElement(USERNAME_LOCATOR);
        usernameField.clear();
        usernameField.sendKeys(username);
        System.out.println("✓ Entered username: " + username);
        return this; // For method chaining
    }

    /**
     * Enter password
     */
    public PageObjectModelLoginPage enterPassword(String password) {
        WebElement passwordField = findElement(PASSWORD_LOCATOR);
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("✓ Entered password");
        return this;
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        WebElement loginButton = findClickableElement(LOGIN_BUTTON);
        loginButton.click();
        System.out.println("✓ Clicked login button");
    }

    /**
     * Method chaining: Enter credentials and login
     */
    public void login(String username, String password) {
        enterUsername(username)
            .enterPassword(password);
        clickLoginButton();
        System.out.println("✓ Login action completed");
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorDisplayed() {
        try {
            findVisibleElement(ERROR_MESSAGE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        WebElement error = findVisibleElement(ERROR_MESSAGE);
        return error.getText();
    }

    /**
     * Check remember me checkbox
     */
    public PageObjectModelLoginPage checkRememberMe() {
        WebElement checkbox = findElement(REMEMBER_ME);
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("✓ Checked remember me");
        }
        return this;
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        WebElement title = findElement(PAGE_TITLE);
        return title.getText();
    }

    /**
     * Verify specific element visibility
     */
    public boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for page to redirect after login
     */
    public void waitForPageRedirect(String expectedUrl) {
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        System.out.println("✓ Page redirected successfully");
    }
}

// ===== DASHBOARD PAGE OBJECT (Demonstrates page hierarchy) =====

class PageObjectModelDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final By WELCOME_MESSAGE = By.tagName("h1");
    private static final By LOGOUT_BUTTON = By.id("logout");
    private static final By USER_PROFILE = By.className("user-profile");

    public PageObjectModelDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        verifyPageIsLoaded();
    }

    private void verifyPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WELCOME_MESSAGE));
        System.out.println("✓ Dashboard page loaded");
    }

    private WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getWelcomeMessage() {
        return findElement(WELCOME_MESSAGE).getText();
    }

    public void logout() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        logoutButton.click();
        System.out.println("✓ Logged out");
    }

    public String getUserProfile() {
        return findElement(USER_PROFILE).getText();
    }
}

// ===== POM WITH TEST EXECUTION =====

class PageObjectModelExample {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new org.openqa.selenium.chrome.ChromeDriver();
            
            // Navigate to login page
            driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
            
            // Create page object
            PageObjectModelLoginPage loginPage = new PageObjectModelLoginPage(driver);
            
            System.out.println("\n===== POM TEST EXECUTION =====");
            System.out.println("Page Title: " + loginPage.getPageTitle());
            
            // Test Case 1: Successful login
            System.out.println("\n--- Test Case 1: Successful Login ---");
            loginPage.login("student", "Password123");
            loginPage.waitForPageRedirect("dashboard");
            
            PageObjectModelDashboardPage dashboard = new PageObjectModelDashboardPage(driver);
            System.out.println("Welcome Message: " + dashboard.getWelcomeMessage());
            
            // Test Case 2: Logout
            System.out.println("\n--- Test Case 2: Logout ---");
            dashboard.logout();
            
            // Test Case 3: Failed login
            System.out.println("\n--- Test Case 3: Invalid Login ---");
            driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
            loginPage = new PageObjectModelLoginPage(driver);
            
            loginPage.login("invalid", "invalid");
            Thread.sleep(1000);
            
            if (loginPage.isErrorDisplayed()) {
                System.out.println("Error Message: " + loginPage.getErrorMessage());
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

// ===== BEST PRACTICES FOR POM =====

class POMBestPractices {
    static void showBestPractices() {
        System.out.println("\n===== POM BEST PRACTICES =====");
        System.out.println("1. SEPARATION OF CONCERNS:");
        System.out.println("   - One class per page");
        System.out.println("   - Separate locators from actions");
        System.out.println("   - Keep business logic in tests, not page objects");
        System.out.println("");
        System.out.println("2. NAMING CONVENTIONS:");
        System.out.println("   - Page class: [PageName]Page (LoginPage, DashboardPage)");
        System.out.println("   - Methods: verb + noun (clickButton, enterUsername)");
        System.out.println("   - Locators: ELEMENT_LOCATOR format");
        System.out.println("");
        System.out.println("3. WAIT STRATEGIES:");
        System.out.println("   - Always use explicit waits");
        System.out.println("   - Never use Thread.sleep() in page objects");
        System.out.println("   - Configure wait times in constructor");
        System.out.println("");
        System.out.println("4. METHOD CHAINING:");
        System.out.println("   - Return 'this' from methods that don't navigate");
        System.out.println("   - Return new page object after navigation");
        System.out.println("   - Makes tests more readable");
        System.out.println("");
        System.out.println("5. ERROR HANDLING:");
        System.out.println("   - Handle timeouts gracefully");
        System.out.println("   - Provide meaningful error messages");
        System.out.println("   - Throw exceptions for page load failures");
        System.out.println("");
        System.out.println("6. MAINTENANCE:");
        System.out.println("   - Review page objects regularly");
        System.out.println("   - Update locators when UI changes");
        System.out.println("   - Keep locators centralized");
        System.out.println("   - Document complex interactions");
    }
}
