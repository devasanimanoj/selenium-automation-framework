package com.example.advancedwebelements;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Web Element #18: Nested Shadow DOM
 * 
 * Learning Objectives:
 * - Handle shadow DOM inside shadow DOM
 * - Navigate through multiple shadow root layers
 * - Get shadow DOM references and drill down
 * - When to use shadow DOM handling (Web Components)
 * 
 * Key Concepts:
 * - Nested Shadow DOM: Shadow root inside another shadow root
 * - Multiple levels of encapsulation
 * - Each level requires separate getShadowRoot() call
 */
public class NestedShadowDOMDemo {
    static WebDriver driver;
    static JavascriptExecutor jsExecutor;
    static WebDriverWait wait;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Test website with nested shadow DOM
            driver.navigate().to("https://component.gallery/components/tooltip/");

            System.out.println("===== NESTED SHADOW DOM HANDLING =====");
            handleNestedShadowDOM();

        } finally {
            driver.quit();
        }
    }

    static void handleNestedShadowDOM() {
        try {
            // Method 1: Using WebDriver getShadowRoot() - Selenium 4+
            System.out.println("\n--- Method 1: WebDriver getShadowRoot() ---");
            nestedShadowDOMUsingWebDriver();

            // Method 2: Using JavaScript (works for any Selenium version)
            System.out.println("\n--- Method 2: JavaScript Approach ---");
            nestedShadowDOMUsingJavaScript();

            // Method 3: Finding elements within nested shadow DOM
            System.out.println("\n--- Method 3: Finding Elements in Nested Shadow ---");
            findElementsInNestedShadow();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method 1: Using WebDriver getShadowRoot()
    static void nestedShadowDOMUsingWebDriver() throws InterruptedException {
        try {
            // First level: Find host element
            WebElement firstHost = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.tagName("tooltip-component"))
            );
            System.out.println("✓ Found first host element: tooltip-component");

            // Get first shadow root
            SearchContext firstShadowRoot = firstHost.getShadowRoot();
            System.out.println("✓ Accessed first shadow root");

            // Find second host element inside first shadow root
            WebElement secondHost = firstShadowRoot.findElement(By.tagName("inner-component"));
            System.out.println("✓ Found second host element: inner-component");

            // Get second shadow root (nested)
            SearchContext secondShadowRoot = secondHost.getShadowRoot();
            System.out.println("✓ Accessed second shadow root (nested)");

            // Find actual element inside nested shadow root
            WebElement nestedElement = secondShadowRoot.findElement(By.tagName("span"));
            String nestedText = nestedElement.getText();
            System.out.println("✓ Found nested element text: " + nestedText);

            // Interact with nested element
            nestedElement.click();
            System.out.println("✓ Clicked nested element");

        } catch (NoSuchElementException e) {
            System.out.println("✗ Element not found in shadow DOM: " + e.getMessage());
        }
    }

    // Method 2: Using JavaScript (more reliable for complex nesting)
    static void nestedShadowDOMUsingJavaScript() {
        try {
            // JavaScript to navigate nested shadow DOM
            String script = "return document.querySelector('tooltip-component').shadowRoot.querySelector('inner-component').shadowRoot.querySelector('span');";
            
            WebElement nestedElement = (WebElement) jsExecutor.executeScript(script);
            
            if (nestedElement != null) {
                String text = nestedElement.getText();
                System.out.println("✓ Found nested element via JS: " + text);
                
                // Get attributes
                String id = nestedElement.getAttribute("id");
                String className = nestedElement.getAttribute("class");
                System.out.println("✓ ID: " + id + ", Class: " + className);
            } else {
                System.out.println("✗ Nested element not found via JavaScript");
            }

        } catch (Exception e) {
            System.out.println("✗ JavaScript Error: " + e.getMessage());
        }
    }

    // Method 3: Finding multiple elements in nested shadow
    static void findElementsInNestedShadow() {
        try {
            // Complex scenario: Find all buttons in nested shadow DOM
            String script = "return document.querySelector('tooltip-component').shadowRoot.querySelectorAll('button');";
            
            java.util.List<Object> buttons = (java.util.List<Object>) jsExecutor.executeScript(script);
            
            System.out.println("✓ Found " + buttons.size() + " buttons in nested shadow");

            // Alternative: Using CSS selector through nested shadow
            String cssScript = "return Array.from(document.querySelector('tooltip-component').shadowRoot.querySelectorAll('inner-component')).map(el => el.shadowRoot.querySelector('button'));";
            
            java.util.List<Object> nestedButtons = (java.util.List<Object>) jsExecutor.executeScript(cssScript);
            System.out.println("✓ Found " + nestedButtons.size() + " nested buttons");

        } catch (Exception e) {
            System.out.println("✗ Error finding nested elements: " + e.getMessage());
        }
    }

    // Helper method: Get text from deeply nested element
    static String getTextFromNestedShadow(String selectorChain) {
        try {
            String script = "return " + selectorChain + ".textContent;";
            return (String) jsExecutor.executeScript(script);
        } catch (Exception e) {
            System.out.println("✗ Error getting nested text: " + e.getMessage());
            return null;
        }
    }

    // Helper method: Click element in nested shadow DOM
    static void clickNestedShadowElement(String selectorChain) {
        try {
            String script = selectorChain + ".click();";
            jsExecutor.executeScript(script);
            System.out.println("✓ Clicked nested element");
        } catch (Exception e) {
            System.out.println("✗ Error clicking nested element: " + e.getMessage());
        }
    }

    // Real-world example: Handling Material Design nested components
    static void handleMaterialDesignComponent() {
        try {
            System.out.println("\n--- Handling Material Design Component ---");
            
            // Material components often have nested shadow DOM
            String script = "return document.querySelector('mat-form-field').shadowRoot.querySelector('mat-input-container').shadowRoot.querySelector('input');";
            
            WebElement input = (WebElement) jsExecutor.executeScript(script);
            
            if (input != null) {
                input.sendKeys("Test Input");
                System.out.println("✓ Entered text in nested Material input");
            }

        } catch (Exception e) {
            System.out.println("✗ Material component error: " + e.getMessage());
        }
    }
}
