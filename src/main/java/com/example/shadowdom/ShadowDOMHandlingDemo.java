package com.example.shadowdom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Shadow DOM Handling in Selenium
 * 
 * Shadow DOM is a web standard that encapsulates part of the DOM tree
 * and CSS styles. Elements inside Shadow DOM cannot be accessed using
 * normal Selenium locators.
 * 
 * Example:
 * <custom-element>
 *   #shadow-root
 *     <input id="shadowInput">
 * </custom-element>
 * 
 * Cannot do: driver.findElement(By.id("shadowInput"))
 * 
 * Solution: Use JavaScript Executor to access Shadow DOM
 * 
 * Shadow DOM Methods:
 * 1. penetrateDOM() - Navigate through shadow roots
 * 2. querySelector() - JavaScript method
 * 3. Custom JavaScript execution
 */
public class ShadowDOMHandlingDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== ACCESS SHADOW DOM ELEMENT =====
        accessShadowDOMElement(driver);

        // ===== INTERACT WITH SHADOW DOM =====
        interactWithShadowDOM(driver);

        // ===== FIND SHADOW DOM ELEMENT BY TEXT =====
        findShadowElementByText(driver);

        driver.quit();
    }

    /**
     * Access Shadow DOM Element
     * 
     * Structure: <custom-element>#shadow-root
     *               <input id="username">
     */
    public static void accessShadowDOMElement(WebDriver driver) {

        try {
            // Get JavaScript executor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Find the host element (element with shadow root)
            WebElement customElement = driver.findElement(By.tagName("custom-element"));

            // Method 1: Using JavaScript to access shadow DOM
            WebElement shadowElement = (WebElement) jsExecutor.executeScript(
                    "return arguments[0].shadowRoot.querySelector('#username')",
                    customElement
            );

            if (shadowElement != null) {
                System.out.println("Found shadow DOM element");
                System.out.println("Element tag: " + shadowElement.getTagName());
            }

        } catch (Exception e) {
            System.out.println("Error accessing shadow DOM: " + e.getMessage());
        }
    }

    /**
     * Interact with Shadow DOM Elements
     * - Type text
     * - Click
     * - Get value
     */
    public static void interactWithShadowDOM(WebDriver driver) {

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Find host element
            WebElement hostElement = driver.findElement(By.tagName("custom-element"));

            // ===== TYPE TEXT =====
            jsExecutor.executeScript(
                    "arguments[0].shadowRoot.querySelector('#username').value = 'Manoj';",
                    hostElement
            );
            System.out.println("Typed text in shadow DOM input");

            // ===== CLICK ELEMENT =====
            jsExecutor.executeScript(
                    "arguments[0].shadowRoot.querySelector('#submitBtn').click();",
                    hostElement
            );
            System.out.println("Clicked shadow DOM button");

            // ===== GET VALUE =====
            String value = (String) jsExecutor.executeScript(
                    "return arguments[0].shadowRoot.querySelector('#username').value;",
                    hostElement
            );
            System.out.println("Value: " + value);

        } catch (Exception e) {
            System.out.println("Error interacting with shadow DOM: " + e.getMessage());
        }
    }

    /**
     * Find Shadow DOM Element by Text
     */
    public static void findShadowElementByText(WebDriver driver) {

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            WebElement hostElement = driver.findElement(By.tagName("custom-element"));

            // Find element by text
            WebElement element = (WebElement) jsExecutor.executeScript(
                    "return arguments[0].shadowRoot.querySelector('button:contains(\"Submit\")') || "
                    + "Array.from(arguments[0].shadowRoot.querySelectorAll('button')).find(btn => btn.textContent === 'Submit');",
                    hostElement
            );

            if (element != null) {
                System.out.println("Found shadow element by text");
                element.click();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handle Multiple Nested Shadow DOMs
     * 
     * Structure: <custom-element1>
     *              #shadow-root
     *                <custom-element2>
     *                  #shadow-root
     *                    <input>
     */
    public static void handleNestedShadowDOM(WebDriver driver) {

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Navigate through multiple shadow roots
            WebElement nestedElement = (WebElement) jsExecutor.executeScript(
                    "return arguments[0]" +
                    ".shadowRoot.querySelector('custom-element2')" +
                    ".shadowRoot.querySelector('#input');",
                    driver.findElement(By.tagName("custom-element1"))
            );

            if (nestedElement != null) {
                System.out.println("Found nested shadow DOM element");
            }

        } catch (Exception e) {
            System.out.println("Error with nested shadow DOM: " + e.getMessage());
        }
    }

    /**
     * Helper Method: Get All Elements in Shadow DOM
     */
    public static WebElement[] getShadowDOMElements(WebDriver driver, 
            String hostSelector, String shadowSelector) {

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            WebElement hostElement = driver.findElement(By.cssSelector(hostSelector));

            Object[] elements = (Object[]) jsExecutor.executeScript(
                    "return Array.from(arguments[0].shadowRoot.querySelectorAll('" + shadowSelector + "'));",
                    hostElement
            );

            WebElement[] shadowElements = new WebElement[elements.length];
            for (int i = 0; i < elements.length; i++) {
                shadowElements[i] = (WebElement) elements[i];
            }

            return shadowElements;

        } catch (Exception e) {
            System.out.println("Error getting shadow elements: " + e.getMessage());
            return new WebElement[0];
        }
    }

    /**
     * Interview Note:
     * Q: How to find element in Shadow DOM?
     * A: Normal Selenium locators cannot find elements in Shadow DOM.
     *    Use JavaScript Executor with shadowRoot.querySelector() method.
     *    
     * Q: Why can't normal Selenium find shadow DOM elements?
     * A: Shadow DOM is encapsulated and not part of the light DOM.
     *    Only JavaScript can penetrate the shadow root.
     */
}
