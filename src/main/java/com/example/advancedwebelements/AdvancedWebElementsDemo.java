package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Advanced WebElement Handling
 * 
 * Covers:
 * 1. Hidden Elements
 * 2. Disabled Elements
 * 3. Stale Elements
 * 4. Dynamic Elements
 * 5. Element Properties
 */
public class AdvancedWebElementsDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== HIDDEN ELEMENTS =====
        handleHiddenElements(driver);

        // ===== DISABLED ELEMENTS =====
        handleDisabledElements(driver);

        // ===== STALE ELEMENTS =====
        handleStaleElements(driver);

        // ===== DYNAMIC ELEMENTS =====
        handleDynamicElements(driver);

        driver.quit();
    }

    /**
     * Hidden Elements Handling
     * 
     * Hidden elements have display:none or visibility:hidden
     * Cannot interact with them unless made visible first
     */
    public static void handleHiddenElements(WebDriver driver) {

        try {
            WebElement hiddenElement = driver.findElement(By.id("hiddenDiv"));

            // Check if element is displayed
            boolean isDisplayed = hiddenElement.isDisplayed();
            System.out.println("Is element displayed: " + isDisplayed);

            // Method 1: Make element visible using JavaScript
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.display='block';", hiddenElement);
            System.out.println("Made hidden element visible");

            // Now interact with it
            if (hiddenElement.isDisplayed()) {
                hiddenElement.click();
                System.out.println("Clicked hidden element");
            }

            // Method 2: Interact directly (some apps allow this)
            // jsExecutor.executeScript("arguments[0].click();", hiddenElement);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Disabled Elements Handling
     */
    public static void handleDisabledElements(WebDriver driver) {

        try {
            WebElement disabledButton = driver.findElement(By.id("submitBtn"));

            // Check if element is enabled
            boolean isEnabled = disabledButton.isEnabled();
            System.out.println("Is button enabled: " + isEnabled);

            if (!isEnabled) {
                System.out.println("Button is disabled. Enabling it...");

                // Enable using JavaScript
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].disabled = false;", disabledButton);

                System.out.println("Button enabled");

                // Now click
                disabledButton.click();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Stale Element Handling
     * 
     * StaleElementReferenceException occurs when:
     * - Element is removed from DOM
     * - Page is refreshed
     * - DOM structure changed
     */
    public static void handleStaleElements(WebDriver driver) {

        try {
            // Find element
            WebElement element = driver.findElement(By.id("dynamicElement"));

            // Try to interact
            try {
                element.click();
                System.out.println("Clicked element");

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Element is stale. Re-finding it...");

                // Re-find the element
                element = driver.findElement(By.id("dynamicElement"));
                element.click();
                System.out.println("Clicked element after re-finding");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handle Stale Elements - Retry Approach
     */
    public static void clickWithRetry(WebDriver driver, By locator, int maxRetries) {

        for (int i = 0; i < maxRetries; i++) {

            try {
                WebElement element = driver.findElement(locator);
                element.click();
                System.out.println("Clicked successfully");
                return;

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Attempt " + (i + 1) + ": Element stale. Retrying...");

                if (i == maxRetries - 1) {
                    System.out.println("Failed after " + maxRetries + " attempts");
                    throw e;
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Dynamic Elements Handling
     * 
     * Elements that appear/disappear dynamically
     * Use explicit waits instead of findElement
     */
    public static void handleDynamicElements(WebDriver driver) {

        try {
            // Wait for element to be present
            org.openqa.selenium.support.ui.WebDriverWait wait = 
                    new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));

            By elementLocator = By.id("dynamicButton");

            // Wait until element is visible
            WebElement element = wait.until(
                    org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(elementLocator)
            );

            element.click();
            System.out.println("Clicked dynamic element");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Element Properties
     */
    public static void getElementProperties(WebDriver driver) {

        try {
            WebElement element = driver.findElement(By.id("username"));

            // Get text
            String text = element.getText();
            System.out.println("Text: " + text);

            // Get value attribute
            String value = element.getAttribute("value");
            System.out.println("Value: " + value);

            // Get tag name
            String tagName = element.getTagName();
            System.out.println("Tag: " + tagName);

            // Get class
            String className = element.getAttribute("class");
            System.out.println("Class: " + className);

            // Get ID
            String id = element.getAttribute("id");
            System.out.println("ID: " + id);

            // Get location
            org.openqa.selenium.Point location = element.getLocation();
            System.out.println("Location: " + location);

            // Get size
            org.openqa.selenium.Dimension size = element.getSize();
            System.out.println("Size: " + size);

            // Get color
            String color = element.getCssValue("color");
            System.out.println("Color: " + color);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Check Element State
     */
    public static void checkElementState(WebDriver driver) {

        try {
            WebElement element = driver.findElement(By.id("element"));

            // Is displayed
            System.out.println("Is displayed: " + element.isDisplayed());

            // Is enabled
            System.out.println("Is enabled: " + element.isEnabled());

            // Is selected (for checkboxes, radio buttons)
            System.out.println("Is selected: " + element.isSelected());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
