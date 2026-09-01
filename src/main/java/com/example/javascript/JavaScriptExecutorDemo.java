package com.example.javascript;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * JavaScript Executor in Selenium
 * 
 * Used to execute JavaScript code in the browser.
 * Useful when normal Selenium methods don't work.
 * 
 * Usage:
 * JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
 * jsExecutor.executeScript("JavaScript code", arguments);
 * 
 * Common Uses:
 * 1. Click element (when normal click doesn't work)
 * 2. Type text
 * 3. Scroll
 * 4. Get/Set element properties
 * 5. Execute complex JavaScript logic
 * 6. Access DOM elements
 */
public class JavaScriptExecutorDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // ===== CLICK USING JAVASCRIPT =====
        clickUsingJavaScript(driver, jsExecutor);

        // ===== TYPE TEXT USING JAVASCRIPT =====
        typeTextUsingJavaScript(driver, jsExecutor);

        // ===== SCROLL PAGE =====
        scrollPage(driver, jsExecutor);

        // ===== GET PAGE TITLE =====
        getPageTitle(jsExecutor);

        // ===== HIGHLIGHT ELEMENT =====
        highlightElement(driver, jsExecutor);

        // ===== DISABLE ELEMENT =====
        disableElement(driver, jsExecutor);

        driver.quit();
    }

    /**
     * Click element using JavaScript
     * Useful when normal click doesn't work (ElementClickIntercepted)
     */
    public static void clickUsingJavaScript(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            WebElement element = driver.findElement(By.id("submitBtn"));

            // Method 1: Direct click
            jsExecutor.executeScript("arguments[0].click();", element);
            System.out.println("Clicked element using JavaScript");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Type text using JavaScript
     */
    public static void typeTextUsingJavaScript(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            WebElement input = driver.findElement(By.id("username"));

            // Method 1: Set value
            jsExecutor.executeScript("arguments[0].value = 'Manoj';", input);
            System.out.println("Set input value using JavaScript");

            // Method 2: Trigger change event
            jsExecutor.executeScript("arguments[0].value = 'Manoj'; arguments[0].dispatchEvent(new Event('change'));", input);
            System.out.println("Set value and triggered change event");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Scroll Page
     */
    public static void scrollPage(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            // Scroll to bottom
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            System.out.println("Scrolled to bottom");

            // Scroll to top
            jsExecutor.executeScript("window.scrollTo(0, 0);");
            System.out.println("Scrolled to top");

            // Scroll by specific pixels
            jsExecutor.executeScript("window.scrollBy(0, 500);");
            System.out.println("Scrolled by 500 pixels");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Scroll to Element
     */
    public static void scrollToElement(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            WebElement element = driver.findElement(By.id("footer"));

            // Scroll element into view
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            System.out.println("Scrolled to element");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Page Title
     */
    public static void getPageTitle(JavascriptExecutor jsExecutor) {

        try {
            String title = (String) jsExecutor.executeScript("return document.title;");
            System.out.println("Page Title: " + title);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Page URL
     */
    public static void getPageURL(JavascriptExecutor jsExecutor) {

        try {
            String url = (String) jsExecutor.executeScript("return window.location.href;");
            System.out.println("Page URL: " + url);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Highlight Element (Change background color)
     */
    public static void highlightElement(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            WebElement element = driver.findElement(By.id("username"));

            // Highlight element
            jsExecutor.executeScript("arguments[0].style.backgroundColor = 'yellow';", element);
            System.out.println("Highlighted element");

            // Wait to see highlight
            Thread.sleep(2000);

            // Remove highlight
            jsExecutor.executeScript("arguments[0].style.backgroundColor = '';", element);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Disable/Enable Element
     */
    public static void disableElement(WebDriver driver, JavascriptExecutor jsExecutor) {

        try {
            WebElement button = driver.findElement(By.id("submitBtn"));

            // Disable
            jsExecutor.executeScript("arguments[0].disabled = true;", button);
            System.out.println("Disabled element");

            // Enable
            jsExecutor.executeScript("arguments[0].disabled = false;", button);
            System.out.println("Enabled element");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Check Element Visibility
     */
    public static boolean isElementVisible(WebElement element, JavascriptExecutor jsExecutor) {

        try {
            Boolean visible = (Boolean) jsExecutor.executeScript(
                    "return arguments[0].offsetParent !== null;",
                    element
            );
            return visible;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Element Attribute
     */
    public static String getElementAttribute(WebElement element, String attributeName, 
            JavascriptExecutor jsExecutor) {

        try {
            return (String) jsExecutor.executeScript(
                    "return arguments[0].getAttribute('" + attributeName + "');",
                    element
            );

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Remove Element from DOM
     */
    public static void removeElement(WebElement element, JavascriptExecutor jsExecutor) {

        try {
            jsExecutor.executeScript("arguments[0].remove();", element);
            System.out.println("Element removed from DOM");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Element Text
     */
    public static String getElementText(WebElement element, JavascriptExecutor jsExecutor) {

        try {
            return (String) jsExecutor.executeScript(
                    "return arguments[0].textContent;",
                    element
            );

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Execute Complex JavaScript
     */
    public static Object executeComplexScript(JavascriptExecutor jsExecutor) {

        try {
            Object result = jsExecutor.executeScript(
                    "var sum = 0; "
                    + "for(var i = 1; i <= 10; i++) { sum += i; } "
                    + "return sum;"
            );
            System.out.println("Result of complex script: " + result);
            return result;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Interview Note:
     * Q: When should you use JavaScript Executor?
     * A: When normal Selenium methods fail:
     *    - Element not clickable
     *    - Shadow DOM elements
     *    - Hidden elements
     *    - Need to access DOM directly
     *    
     * Q: Can you use JS to interact with all elements?
     * A: Yes, but it's not user-like behavior. 
     *    Always try normal Selenium methods first.
     *    
     * Q: Why avoid overusing JavaScript?
     * A: It doesn't simulate real user interactions.
     *    Automated tests should mimic actual user behavior.
     */
}
