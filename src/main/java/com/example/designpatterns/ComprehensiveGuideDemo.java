package com.example.designpatterns;

/**
 * COMPREHENSIVE GUIDE: CHROMEDRIVER OPTIONS vs JAVASCRIPT EXECUTOR vs ACTIONS
 * 
 * When to use each and why
 */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.HashMap;

/**
 * ========================================
 * 1. CHROMEDRIVER OPTIONS
 * ========================================
 * 
 * WHEN TO USE:
 * - Before creating ChromeDriver instance
 * - Setting browser preferences
 * - Disabling plugins/extensions
 * - Setting proxy
 * - Disabling password/notification popups
 * 
 * WHY USE:
 * - Controls browser behavior at startup
 * - Prevents popups before test begins
 * - Improves test stability and performance
 * - Necessary for specific test scenarios
 */
class ChromeDriverOptionsDemo {
    
    static void demonstrateChromeOptions() {
        System.out.println("\n===== CHROMEDRIVER OPTIONS =====");
        
        ChromeOptions options = new ChromeOptions();

        // ===== COMMON CHROMEDRIVER OPTIONS =====

        // 1. Run in headless mode (no GUI)
        options.addArguments("--headless");
        System.out.println("✓ Added: Headless mode");

        // 2. Disable notifications popup
        options.addArguments("--disable-notifications");
        System.out.println("✓ Added: Disable notifications");

        // 3. Disable images loading (faster)
        options.addArguments("--blink-settings=imagesEnabled=false");
        System.out.println("✓ Added: Disable images");

        // 4. Start maximized
        options.addArguments("--start-maximized");
        System.out.println("✓ Added: Start maximized");

        // 5. Disable password manager
        options.addArguments("--disable-password-manager-reauthentication");
        System.out.println("✓ Added: Disable password manager");

        // 6. Disable autocomplete
        options.addArguments("--disable-autofill");
        System.out.println("✓ Added: Disable autofill");

        // 7. Set window size
        options.addArguments("--window-size=1920,1080");
        System.out.println("✓ Added: Window size 1920x1080");

        // 8. Set user data directory (for profile)
        // options.addArguments("user-data-dir=/path/to/profile");
        
        // 9. Ignore SSL errors
        options.setAcceptInsecureCerts(true);
        System.out.println("✓ Added: Accept insecure certs");

        // 10. Disable extensions
        options.addArguments("--disable-extensions");
        System.out.println("✓ Added: Disable extensions");

        // 11. Disable plugins
        options.addArguments("--disable-plugins");
        System.out.println("✓ Added: Disable plugins");

        // 12. Disable default apps
        options.addArguments("--disable-default-apps");
        System.out.println("✓ Added: Disable default apps");

        // 13. Disable background timers
        options.addArguments("--disable-backgrounding-occluded-windows");
        System.out.println("✓ Added: Disable background timers");

        // 14. Set proxy
        // options.setProxy(new org.openqa.selenium.Proxy().setHttpProxy("127.0.0.1:8080"));
        
        // 15. Set user agent
        options.addArguments("user-agent=Custom User Agent");
        System.out.println("✓ Added: Custom user agent");

        // 16. Disable sync
        options.addArguments("--disable-sync");
        System.out.println("✓ Added: Disable sync");

        // 17. Disable GPU (for faster headless)
        options.addArguments("--disable-gpu");
        System.out.println("✓ Added: Disable GPU");

        // 18. Set preferences
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", prefs);
        System.out.println("✓ Added: Set preferences");

        // Create driver with options
        WebDriver driver = new ChromeDriver(options);
        System.out.println("✓ Created ChromeDriver with options");
        
        // Use driver...
        driver.quit();
    }

    // Best use cases for ChromeOptions
    static void bestUseCases() {
        System.out.println("\n--- Best Use Cases for ChromeOptions ---");
        System.out.println("1. CI/CD Pipeline: Use headless mode");
        System.out.println("2. Performance Testing: Disable images");
        System.out.println("3. Notification Testing: --disable-notifications");
        System.out.println("4. Proxy Testing: Configure proxy");
        System.out.println("5. Multi-browser Testing: Run multiple Chrome instances");
        System.out.println("6. Profile-based Testing: Use specific user profile");
        System.out.println("7. SSL Certificate Issues: acceptInsecureCerts");
    }
}

/**
 * ========================================
 * 2. JAVASCRIPT EXECUTOR
 * ========================================
 * 
 * WHEN TO USE:
 * - Need to interact with JavaScript-based elements
 * - Get values from JavaScript variables
 * - Modify DOM dynamically
 * - Execute complex JavaScript logic
 * - Bypass Selenium limitations
 * 
 * WHY USE:
 * - Selenium can't handle JavaScript-rendered content directly
 * - Faster than native Selenium operations
 * - Access to page's JavaScript context
 * - Can modify page behavior at runtime
 */
class JavaScriptExecutorDemo {
    static WebDriver driver;
    static JavascriptExecutor jsExecutor;

    static void demonstrateJavaScriptExecutor() {
        System.out.println("\n===== JAVASCRIPT EXECUTOR =====");
        
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;

        try {
            driver.navigate().to("https://www.google.com");

            // ===== COMMON JAVASCRIPT EXECUTOR OPERATIONS =====

            // 1. Execute script and get return value
            Long scrollPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
            System.out.println("✓ Current scroll position: " + scrollPosition);

            // 2. Execute script without return value
            jsExecutor.executeScript("console.log('Hello from Selenium');");
            System.out.println("✓ Logged to console");

            // 3. Get element text (alternative to getText())
            WebElement element = driver.findElement(By.tagName("h1"));
            String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
            System.out.println("✓ Element text via JS: " + text);

            // 4. Set element value
            WebElement input = driver.findElement(By.name("q"));
            jsExecutor.executeScript("arguments[0].value = 'Selenium';", input);
            System.out.println("✓ Set input value via JavaScript");

            // 5. Click element (alternative to click())
            jsExecutor.executeScript("arguments[0].click();", input);
            System.out.println("✓ Clicked element via JavaScript");

            // 6. Scroll to element
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", input);
            System.out.println("✓ Scrolled to element");

            // 7. Scroll by pixels
            jsExecutor.executeScript("window.scrollBy(0, 500);");
            System.out.println("✓ Scrolled down 500px");

            // 8. Get page title
            String title = (String) jsExecutor.executeScript("return document.title;");
            System.out.println("✓ Page title: " + title);

            // 9. Get page URL
            String url = (String) jsExecutor.executeScript("return window.location.href;");
            System.out.println("✓ Page URL: " + url);

            // 10. Get all elements matching selector
            java.util.List<Object> links = (java.util.List<Object>) jsExecutor
                    .executeScript("return document.querySelectorAll('a').length;");
            System.out.println("✓ Total links on page: " + links);

            // 11. Check if element is visible
            Boolean isVisible = (Boolean) jsExecutor.executeScript(
                "return arguments[0].offsetParent !== null;", element);
            System.out.println("✓ Element is visible: " + isVisible);

            // 12. Get computed style
            String color = (String) jsExecutor.executeScript(
                "return window.getComputedStyle(arguments[0]).color;", element);
            System.out.println("✓ Element color: " + color);

            // 13. Remove element from DOM
            jsExecutor.executeScript("arguments[0].remove();", element);
            System.out.println("✓ Removed element from DOM");

            // 14. Highlight element (useful for debugging)
            jsExecutor.executeScript("arguments[0].style.border='3px solid red';", input);
            System.out.println("✓ Highlighted element");

            // 15. Wait for jQuery (if used)
            jsExecutor.executeScript("return jQuery.active == 0;");
            System.out.println("✓ Checked jQuery AJAX");

            // 16. Wait for Angular (if used)
            jsExecutor.executeScript("return angular.element(document.body).injector().get('$http').pendingRequests.length === 0;");
            System.out.println("✓ Checked Angular requests");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    static void bestUseCases() {
        System.out.println("\n--- Best Use Cases for JavaScript Executor ---");
        System.out.println("1. Set hidden element values");
        System.out.println("2. Get JavaScript variable values");
        System.out.println("3. Click elements hidden by CSS");
        System.out.println("4. Scroll to specific elements");
        System.out.println("5. Remove overlay elements");
        System.out.println("6. Execute complex JavaScript");
        System.out.println("7. Handle dynamic/AJAX content");
        System.out.println("8. Get element's computed styles");
        System.out.println("9. Wait for framework-specific events");
        System.out.println("10. Debug by logging to console");
    }

    static void whenNotToUse() {
        System.out.println("\n--- When NOT to Use JavaScript Executor ---");
        System.out.println("1. Simple element clicks - use click()");
        System.out.println("2. Standard form inputs - use sendKeys()");
        System.out.println("3. Regular element visibility - use isDisplayed()");
        System.out.println("4. Tests should reflect real user behavior");
        System.out.println("5. JavaScript execution won't work if JavaScript is disabled");
    }
}

/**
 * ========================================
 * 3. ACTIONS (KEYBOARD AND MOUSE)
 * ========================================
 * 
 * WHEN TO USE:
 * - Complex mouse interactions (hover, drag-drop)
 * - Keyboard shortcuts (Ctrl+A, Ctrl+C, Tab)
 * - Key combinations
 * - Multi-step interactions
 * 
 * WHY USE:
 * - Selenium click() can be blocked by overlays
 * - Simulates real user interactions
 * - More reliable than JavaScript click
 * - Supports keyboard combinations
 */
class ActionsDemo {
    static WebDriver driver;
    static Actions actions;
    static WebDriverWait wait;

    static void demonstrateActions() {
        System.out.println("\n===== ACTIONS (KEYBOARD AND MOUSE) =====");
        
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.navigate().to("https://www.google.com");

            // ===== COMMON ACTIONS =====

            // 1. Hover over element
            WebElement searchBox = driver.findElement(By.name("q"));
            actions.moveToElement(searchBox).perform();
            System.out.println("✓ Hovered over search box");

            // 2. Click and hold
            actions.moveToElement(searchBox)
                   .clickAndHold()
                   .perform();
            System.out.println("✓ Click and hold performed");

            // 3. Release (after clickAndHold)
            actions.release().perform();
            System.out.println("✓ Released");

            // 4. Send keys
            actions.sendKeys("Selenium WebDriver").perform();
            System.out.println("✓ Typed text");

            // 5. Key combination (Ctrl+A)
            actions.keyDown(Keys.CONTROL)
                   .sendKeys("a")
                   .keyUp(Keys.CONTROL)
                   .perform();
            System.out.println("✓ Pressed Ctrl+A");

            // 6. Double click
            WebElement element = driver.findElement(By.tagName("body"));
            actions.doubleClick(element).perform();
            System.out.println("✓ Double clicked");

            // 7. Right click (context menu)
            actions.contextClick(element).perform();
            System.out.println("✓ Right clicked");

            // 8. Drag and drop
            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));
            actions.dragAndDrop(source, target).perform();
            System.out.println("✓ Dragged and dropped");

            // 9. Drag by offset
            actions.moveToElement(source)
                   .clickAndHold()
                   .moveByOffset(100, 50)
                   .release()
                   .perform();
            System.out.println("✓ Dragged by offset");

            // 10. Tab key
            actions.sendKeys(Keys.TAB).perform();
            System.out.println("✓ Pressed Tab");

            // 11. Enter key
            actions.sendKeys(Keys.ENTER).perform();
            System.out.println("✓ Pressed Enter");

            // 12. Escape key
            actions.sendKeys(Keys.ESCAPE).perform();
            System.out.println("✓ Pressed Escape");

            // 13. Scroll wheel (Selenium 4+)
            WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
            actions.scroll(scrollOrigin, 0, 500).perform();
            System.out.println("✓ Scrolled using wheel");

            // 14. Move to element by offset
            actions.moveToElement(element, 10, 10).perform();
            System.out.println("✓ Moved to element with offset");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    static void bestUseCases() {
        System.out.println("\n--- Best Use Cases for Actions ---");
        System.out.println("1. Hover over elements (tooltips, menus)");
        System.out.println("2. Drag and drop operations");
        System.out.println("3. Right-click context menus");
        System.out.println("4. Double-click actions");
        System.out.println("5. Keyboard shortcuts (Ctrl+A, Ctrl+C, etc.)");
        System.out.println("6. Multi-step interactions");
        System.out.println("7. Clicking covered elements");
        System.out.println("8. Tab navigation");
        System.out.println("9. Scroll wheel operations");
        System.out.println("10. Simulating real user behavior");
    }
}

/**
 * ========================================
 * COMPARISON AND DECISION MATRIX
 * ========================================
 */
class ComparisonGuide {
    static void showComparison() {
        System.out.println("\n===== COMPARISON TABLE =====");
        System.out.println("\n┌─────────────────────┬──────────────────────┬────────────┬─────────────┐");
        System.out.println("│ Feature             │ Chrome Options       │ JS Executor│ Actions     │");
        System.out.println("├─────────────────────┼──────────────────────┼────────────┼─────────────┤");
        System.out.println("│ Browser Setup       │ YES                  │ NO         │ NO          │");
        System.out.println("│ Complex Clicks      │ NO                   │ YES        │ YES         │");
        System.out.println("│ Hidden Elements     │ NO                   │ YES        │ NO          │");
        System.out.println("│ Keyboard Shortcuts  │ NO                   │ SOME       │ YES         │");
        System.out.println("│ Hover Operations    │ NO                   │ LIMITED    │ YES         │");
        System.out.println("│ Drag & Drop         │ NO                   │ LIMITED    │ YES         │");
        System.out.println("│ User Behavior       │ NO                   │ PARTIAL    │ YES         │");
        System.out.println("│ Performance         │ YES                  │ YES        │ NO          │");
        System.out.println("│ Reliability         │ HIGH                 │ HIGH       │ HIGHEST     │");
        System.out.println("└─────────────────────┴──────────────────────┴────────────┴─────────────┘");
    }

    static void decisionMatrix() {
        System.out.println("\n===== DECISION MATRIX =====");
        System.out.println("\nChoose ChromeDriver Options when:");
        System.out.println("- Setting up browser before tests");
        System.out.println("- Disabling popups/notifications");
        System.out.println("- Running in CI/CD (headless)");
        System.out.println("- Configuring proxy/certificate");
        
        System.out.println("\nChoose JavaScript Executor when:");
        System.out.println("- Element is hidden or opacity=0");
        System.out.println("- Need to access JavaScript variables");
        System.out.println("- Modify page behavior dynamically");
        System.out.println("- Click blocked by overlay (last resort)");
        
        System.out.println("\nChoose Actions when:");
        System.out.println("- Hovering over elements");
        System.out.println("- Drag and drop");
        System.out.println("- Right-click");
        System.out.println("- Keyboard combinations");
        System.out.println("- Simulating real user interactions");
    }
}

// MAIN EXECUTION
public class ComprehensiveGuideDemo {
    public static void main(String[] args) {
        ChromeDriverOptionsDemo.demonstrateChromeOptions();
        ChromeDriverOptionsDemo.bestUseCases();
        
        // JavaScriptExecutorDemo.demonstrateJavaScriptExecutor();
        // JavaScriptExecutorDemo.bestUseCases();
        // JavaScriptExecutorDemo.whenNotToUse();
        
        // ActionsDemo.demonstrateActions();
        // ActionsDemo.bestUseCases();
        
        ComparisonGuide.showComparison();
        ComparisonGuide.decisionMatrix();
    }
}
