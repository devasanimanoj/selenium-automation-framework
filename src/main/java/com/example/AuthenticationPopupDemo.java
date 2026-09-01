package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.net.URL;

/**
 * Authentication Popup Handling
 * 
 * Learning Objectives:
 * - Handle HTTP Basic Authentication popup (browser-level)
 * - Handle HTTP Digest Authentication
 * - Handle OAuth popups
 * - Handle form-based login popups
 * - Understand Alert vs Authentication dialog
 * 
 * Key Concepts:
 * - HTTP Basic Auth popup is a browser dialog (NOT JavaScript Alert)
 * - Cannot be handled with driver.switchTo().alert()
 * - Must use URL embedding or ChromeOptions
 * - Different approach for different authentication types
 */
public class AuthenticationPopupDemo {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        System.out.println("===== AUTHENTICATION POPUP HANDLING =====");
        
        try {
            // Method 1: Basic Auth with embedded credentials
            System.out.println("\n--- Method 1: Embedded Credentials in URL ---");
            basicAuthWithEmbeddedCredentials();

            // Method 2: Basic Auth with proxy
            System.out.println("\n--- Method 2: ChromeOptions Approach ---");
            basicAuthWithChromeOptions();

            // Method 3: Form-based authentication
            System.out.println("\n--- Method 3: Form-based Login ---");
            formBasedAuthentication();

            // Method 4: OAuth authentication
            System.out.println("\n--- Method 4: OAuth Popup ---");
            oauthAuthentication();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    // Method 1: Basic Auth with embedded credentials in URL
    static void basicAuthWithEmbeddedCredentials() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // URL format: http://username:password@website.com
            String username = "admin";
            String password = "password123";
            String baseUrl = "https://the-internet.herokuapp.com/basic_auth";
            String urlWithCredentials = "http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";

            driver.navigate().to(urlWithCredentials);
            System.out.println("✓ Navigated with embedded credentials");

            // Wait for page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));

            // Verify successful authentication
            WebElement successMessage = driver.findElement(By.tagName("h3"));
            String message = successMessage.getText();
            System.out.println("✓ Page message: " + message);

            // Check if authentication was successful
            if (message.contains("Congratulations")) {
                System.out.println("✓ Successfully authenticated with basic auth");
            }

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Method 2: Using ChromeOptions (alternative approach)
    static void basicAuthWithChromeOptions() {
        // Note: Direct username/password in ChromeOptions is not recommended
        // This is a conceptual approach
        
        ChromeOptions options = new ChromeOptions();
        // ChromeDriver doesn't have built-in auth options like some other browsers
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // For sites requiring basic auth, use JavaScript to inject headers
            String username = "admin";
            String password = "password123";

            // Create base64 encoded credentials
            String credentials = java.util.Base64.getEncoder()
                    .encodeToString((username + ":" + password).getBytes());
            
            // Navigate to the page
            driver.navigate().to("https://the-internet.herokuapp.com/basic_auth");
            
            System.out.println("✓ Navigated to authenticated page");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Method 3: Form-based login (most common)
    static void formBasedAuthentication() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
            System.out.println("✓ Navigated to login page");

            // Find username field
            WebElement usernameField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("username"))
            );

            // Find password field
            WebElement passwordField = driver.findElement(By.id("password"));

            // Enter credentials
            usernameField.sendKeys("student");
            passwordField.sendKeys("Password123");
            System.out.println("✓ Entered credentials");

            // Find and click login button
            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();
            System.out.println("✓ Clicked login button");

            // Wait for successful login (redirect to dashboard)
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("✓ Successfully logged in");

            // Verify logged-in state
            WebElement welcomeMessage = driver.findElement(By.tagName("h1"));
            String message = welcomeMessage.getText();
            System.out.println("✓ Page title: " + message);

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Method 4: Handling OAuth popup
    static void oauthAuthentication() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to page with OAuth login
            driver.navigate().to("https://example.com/login");
            System.out.println("✓ Navigated to OAuth login page");

            // Click "Login with Google" button (example)
            WebElement googleLoginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Google')]"))
            );
            googleLoginButton.click();
            System.out.println("✓ Clicked OAuth login button");

            // OAuth opens in a new window/tab
            // Wait for new window to appear
            Thread.sleep(2000); // Wait for popup to open

            // Get all window handles
            java.util.Set<String> allHandles = driver.getWindowHandles();
            String parentHandle = driver.getWindowHandle();
            
            System.out.println("✓ Found " + allHandles.size() + " windows");

            // Switch to OAuth popup window
            for (String handle : allHandles) {
                if (!handle.equals(parentHandle)) {
                    driver.switchTo().window(handle);
                    System.out.println("✓ Switched to OAuth popup");
                    break;
                }
            }

            // Handle OAuth login in popup
            handleOAuthPopup();

            // Switch back to parent window
            driver.switchTo().window(parentHandle);
            System.out.println("✓ Switched back to parent window");

            // Wait for authentication callback
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("✓ OAuth authentication completed");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Helper: Handle OAuth popup window
    static void handleOAuthPopup() {
        try {
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Enter Google email
            WebElement emailField = popupWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("identifierId"))
            );
            emailField.sendKeys("your-email@gmail.com");

            // Click Next
            WebElement nextButton = driver.findElement(By.id("identifierNext"));
            nextButton.click();

            Thread.sleep(1000);

            // Enter password
            WebElement passwordField = popupWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("password"))
            );
            passwordField.sendKeys("your-password");

            // Click Next
            WebElement passwordNext = driver.findElement(By.id("passwordNext"));
            passwordNext.click();

            System.out.println("✓ OAuth credentials entered");

        } catch (Exception e) {
            System.out.println("✗ OAuth popup error: " + e.getMessage());
        }
    }

    // Advanced: Handling Digest Authentication
    static void digestAuthenticationHandling() {
        System.out.println("\n--- Digest Authentication ---");
        System.out.println("Digest Auth cannot be handled directly with WebDriver");
        System.out.println("Approaches:");
        System.out.println("1. Use embedded credentials in URL (if server supports)");
        System.out.println("2. Use proxy server to handle authentication");
        System.out.println("3. Use REST client to authenticate and get session cookie");
        System.out.println("4. Use Selenium Grid with proxy configuration");
    }

    // Best practices
    static void bestPractices() {
        System.out.println("\n===== AUTHENTICATION POPUP BEST PRACTICES =====");
        System.out.println("1. SECURITY:");
        System.out.println("   - Never hardcode credentials in code");
        System.out.println("   - Use environment variables or config files");
        System.out.println("   - Never commit credentials to repository");
        System.out.println("");
        System.out.println("2. TESTING:");
        System.out.println("   - Use test accounts created specifically for testing");
        System.out.println("   - Ensure test accounts don't interfere with production");
        System.out.println("   - Keep authentication credentials separate from test code");
        System.out.println("");
        System.out.println("3. IMPLEMENTATION:");
        System.out.println("   - For form-based auth: Page Object Model is recommended");
        System.out.println("   - For basic auth: Use URL embedding as it's the simplest");
        System.out.println("   - For OAuth: Handle window switching carefully");
        System.out.println("   - Always wait for page to load after authentication");
        System.out.println("");
        System.out.println("4. MAINTENANCE:");
        System.out.println("   - Keep authentication logic in separate utility class");
        System.out.println("   - Use hooks (TestNG/JUnit) to setup/teardown auth");
        System.out.println("   - Handle session timeouts and re-authentication");
        System.out.println("   - Document authentication flow for team");
    }
}
