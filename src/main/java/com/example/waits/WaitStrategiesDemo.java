package com.example.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Wait Strategies in Selenium
 * 
 * Three types of waits:
 * 1. Implicit Wait - Applied globally to all elements
 * 2. Explicit Wait - Applied to specific elements
 * 3. Fluent Wait - Custom polling frequency
 * 
 * Best Practice: Use Explicit Wait (WebDriverWait) with ExpectedConditions
 */
public class WaitStrategiesDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== IMPLICIT WAIT =====
        implicitWaitDemo(driver);

        // ===== EXPLICIT WAIT =====
        explicitWaitDemo(driver);

        // ===== FLUENT WAIT =====
        fluentWaitDemo(driver);

        driver.quit();
    }

    /**
     * Implicit Wait
     * 
     * Applied globally to all elements.
     * Selenium waits up to specified time before throwing NoSuchElementException.
     * 
     * Disadvantages:
     * - Applied to all findElement() calls
     * - Can't be customized per element
     * - Can slow down tests
     */
    public static void implicitWaitDemo(WebDriver driver) {

        try {
            // Set implicit wait to 10 seconds
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("Implicit wait set to 10 seconds");

            // Now any findElement() will wait up to 10 seconds
            WebElement element = driver.findElement(By.id("element"));

            System.out.println("Element found using implicit wait");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Explicit Wait (WebDriverWait)
     * 
     * Better approach than implicit wait.
     * Wait for specific conditions on specific elements.
     * 
     * Advantages:
     * - Applied to specific elements
     * - Can use different waits for different elements
     * - More control
     */
    public static void explicitWaitDemo(WebDriver driver) {

        try {
            // Create WebDriverWait with 10 seconds timeout
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By elementLocator = By.id("dynamicElement");

            // ===== WAIT FOR ELEMENT TO BE PRESENT =====
            WebElement element = wait.until(
                    ExpectedConditions.presenceOfElementLocated(elementLocator)
            );
            System.out.println("Element is present in DOM");

            // ===== WAIT FOR ELEMENT TO BE VISIBLE =====
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(elementLocator)
            );
            System.out.println("Element is visible");

            // ===== WAIT FOR ELEMENT TO BE CLICKABLE =====
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(elementLocator)
            );
            element.click();
            System.out.println("Element is clickable, clicked it");

            // ===== WAIT FOR ELEMENT TO BE SELECTED =====
            element = wait.until(
                    ExpectedConditions.elementSelectionStateToBe(elementLocator, true)
            );
            System.out.println("Element is selected");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Fluent Wait
     * 
     * Customizable polling frequency.
     * Wait.until() checks condition every specified time period.
     */
    public static void fluentWaitDemo(WebDriver driver) {

        try {
            // Create Fluent Wait
            org.openqa.selenium.support.ui.FluentWait<WebDriver> fluentWait = 
                    new org.openqa.selenium.support.ui.FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))          // Max wait time
                    .pollingEvery(Duration.ofMillis(500))         // Check every 500ms
                    .ignoring(org.openqa.selenium.NoSuchElementException.class);

            By elementLocator = By.id("element");

            WebElement element = fluentWait.until(
                    ExpectedConditions.visibilityOfElementLocated(elementLocator)
            );

            System.out.println("Element found using fluent wait");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Common ExpectedConditions
     */
    public static void commonExpectedConditions(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By locator = By.id("element");

        try {
            // 1. Presence of Element
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("Element is present");

            // 2. Visibility of Element
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("Element is visible");

            // 3. Invisibility of Element
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println("Element is invisible/hidden");

            // 4. Element to be Clickable
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("Element is clickable");

            // 5. Element is Selected
            wait.until(ExpectedConditions.elementSelectionStateToBe(locator, true));
            System.out.println("Element is selected");

            // 6. Title Contains
            wait.until(ExpectedConditions.titleContains("Example"));
            System.out.println("Title contains 'Example'");

            // 7. URL Contains
            wait.until(ExpectedConditions.urlContains("example.com"));
            System.out.println("URL contains 'example.com'");

            // 8. Alert is Present
            wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert is present");

            // 9. Number of Windows
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            System.out.println("Number of windows is 2");

            // 10. Frame to be Available and Switch
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frameId"));
            System.out.println("Switched to frame");

        } catch (Exception e) {
            System.out.println("Wait condition failed: " + e.getMessage());
        }
    }

    /**
     * Custom Wait Condition
     */
    public static void customWaitCondition(WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for custom condition
            wait.until(driver1 -> {
                WebElement element = driver1.findElement(By.id("element"));
                String text = element.getText();
                return text.contains("Success");
            });

            System.out.println("Element text contains 'Success'");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Wait with Custom Error Message
     */
    public static void waitWithErrorMessage(WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By locator = By.id("element");

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .click();

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Element was not visible within 10 seconds");
            System.out.println("Timeout Exception: " + e.getMessage());
        }
    }

    /**
     * Page Load Wait
     */
    public static void waitForPageLoad(WebDriver driver, Duration timeout) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);

            wait.until(driver1 -> 
                    ((org.openqa.selenium.JavascriptExecutor) driver1)
                    .executeScript("return document.readyState")
                    .equals("complete")
            );

            System.out.println("Page loaded completely");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Wait for AJAX/JavaScript to Complete
     */
    public static void waitForAJAXToComplete(WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(driver1 -> 
                    (Long) ((org.openqa.selenium.JavascriptExecutor) driver1)
                    .executeScript("return jQuery.active == 0")
            );

            System.out.println("AJAX requests completed");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Best Practices
     * 
     * 1. Use Explicit Wait (WebDriverWait) over Implicit Wait
     * 2. Use appropriate ExpectedConditions
     * 3. Set reasonable timeout values (10-15 seconds)
     * 4. Don't mix Implicit and Explicit waits
     * 5. Handle TimeoutException
     * 6. Use waits sparingly - improve test reliability instead
     */
}
