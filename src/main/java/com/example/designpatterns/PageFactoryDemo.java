package com.example.designpatterns;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * PAGE FACTORY DESIGN PATTERN
 * 
 * What is Page Factory?
 * - A class-based design pattern from Selenium
 * - Reduces code duplication by centralizing element locators
 * - Uses @FindBy annotation to define elements
 * - Automatically initializes WebElements using PageFactory.initElements()
 * 
 * Advantages:
 * 1. Centralized locators - easier to maintain
 * 2. Cleaner code - separates locators from actions
 * 3. Lazy initialization - elements found only when used
 * 4. Better readability - element names are self-documenting
 * 5. Reusability - same page object used across multiple tests
 * 
 * When to use Page Factory:
 * - Single page, multiple tests
 * - Simple to moderate automation
 * - Team prefers annotation-based approach
 * - Quick prototyping
 * 
 * When NOT to use Page Factory:
 * - Complex dynamic elements
 * - Need custom wait strategies
 * - Want more flexibility in element handling
 * - Consider POM (Page Object Model) for large frameworks
 */
public class PageFactoryLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // ===== ELEMENT LOCATORS using @FindBy =====
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "loginButton")
    WebElement loginButton;

    @FindBy(xpath = "//span[@class='error-message']")
    WebElement errorMessage;

    @FindBy(id = "rememberMe")
    WebElement rememberMeCheckbox;

    @FindBy(linkText = "Forgot Password?")
    WebElement forgotPasswordLink;

    @FindBy(css = ".login-container h1")
    WebElement pageTitle;

    // Constructor
    public PageFactoryLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Initialize all WebElements annotated with @FindBy
        PageFactory.initElements(driver, this);
    }

    // ===== PAGE ACTIONS =====

    /**
     * Enter username in the username field
     */
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        System.out.println("✓ Entered username: " + username);
    }

    /**
     * Enter password in the password field
     */
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("✓ Entered password");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        loginButton.click();
        System.out.println("✓ Clicked login button");
    }

    /**
     * Complete login action
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        System.out.println("✓ Login action completed");
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    /**
     * Check remember me checkbox
     */
    public void checkRememberMe() {
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
            System.out.println("✓ Checked remember me checkbox");
        }
    }

    /**
     * Click forgot password link
     */
    public void clickForgotPassword() {
        forgotPasswordLink.click();
        System.out.println("✓ Clicked forgot password link");
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return pageTitle.getText();
    }

    /**
     * Verify login page is loaded
     */
    public boolean isLoginPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.isDisplayed() && usernameField.isDisplayed();
    }
}

// ===== ADVANCED PAGE FACTORY WITH CUSTOM LOCATOR FACTORY =====

class PageFactoryWithCustomLocators {
    // This demonstrates how to extend Page Factory with custom behavior
    
    WebDriver driver;
    WebDriverWait wait;

    /**
     * Custom initialization with wait strategy
     */
    public PageFactoryWithCustomLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Initialize with custom locator factory
        // This allows for custom element initialization
        PageFactory.initElements(driver, this);
    }

    /**
     * Find element with explicit wait
     */
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Find clickable element
     */
    protected WebElement findClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Find visible element
     */
    protected WebElement findVisibleElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

// ===== USAGE EXAMPLE =====

class PageFactoryExample {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new org.openqa.selenium.chrome.ChromeDriver();
            
            // Initialize Page Factory
            driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
            
            // Create page object
            PageFactoryLoginPage loginPage = new PageFactoryLoginPage(driver);
            
            // Use page object
            if (loginPage.isLoginPageLoaded()) {
                System.out.println("✓ Login page loaded successfully");
                System.out.println("  Page title: " + loginPage.getPageTitle());
            }
            
            // Perform login
            loginPage.login("student", "Password123");
            
            // Wait for navigation
            Thread.sleep(2000);
            
            // Check result
            if (loginPage.isErrorMessageDisplayed()) {
                System.out.println("✗ Login failed: " + loginPage.getErrorMessage());
            } else {
                System.out.println("✓ Login successful");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
