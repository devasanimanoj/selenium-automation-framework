package com.example.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * iFrame (Inline Frame) Handling
 * 
 * iFrame is an HTML element that embeds another HTML page within a page.
 * 
 * HTML Example:
 * <iframe id="myIframe" src="page.html"></iframe>
 * <iframe name="paymentFrame" src="payment.html"></iframe>
 * <iframe class="content-frame" src="content.html"></iframe>
 * 
 * To interact with elements inside iFrame:
 * 1. Switch to the iFrame
 * 2. Find and interact with elements
 * 3. Switch back to default content
 * 
 * Switch methods:
 * - driver.switchTo().frame(int index)
 * - driver.switchTo().frame(String nameOrId)
 * - driver.switchTo().frame(WebElement element)
 * - driver.switchTo().defaultContent()
 * - driver.switchTo().parentFrame()
 */
public class IFrameHandlingDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== SWITCH BY ID =====
        switchByIdDemo(driver);

        // ===== SWITCH BY NAME =====
        switchByNameDemo(driver);

        // ===== SWITCH BY INDEX =====
        switchByIndexDemo(driver);

        // ===== SWITCH BY WEBELEMENT =====
        switchByWebElementDemo(driver);

        // ===== NESTED IFRAMES =====
        nestedIFrameDemo(driver);

        driver.quit();
    }

    /**
     * Switch to iFrame using ID
     */
    public static void switchByIdDemo(WebDriver driver) {

        try {
            // Switch to iFrame by ID
            driver.switchTo().frame("myIframe");
            System.out.println("Switched to iFrame by ID");

            // Find and interact with element inside iFrame
            WebElement textbox = driver.findElement(By.id("textbox"));
            textbox.sendKeys("Hello from iFrame");

            // Switch back to default content
            driver.switchTo().defaultContent();
            System.out.println("Switched back to default content");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Switch to iFrame using Name attribute
     */
    public static void switchByNameDemo(WebDriver driver) {

        try {
            // Switch to iFrame by name
            driver.switchTo().frame("paymentFrame");
            System.out.println("Switched to iFrame by name");

            // Interact with element
            WebElement button = driver.findElement(By.id("submitBtn"));
            button.click();

            // Switch back
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Switch to iFrame using Index
     * Index starts from 0
     */
    public static void switchByIndexDemo(WebDriver driver) {

        try {
            // Get total number of iFrames
            int iFrameCount = driver.findElements(By.tagName("iframe")).size();
            System.out.println("Total iFrames: " + iFrameCount);

            // Switch to first iFrame (index 0)
            driver.switchTo().frame(0);
            System.out.println("Switched to iFrame at index 0");

            // Interact with element
            WebElement link = driver.findElement(By.linkText("Click Here"));
            link.click();

            // Switch back
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Switch to iFrame using WebElement reference
     */
    public static void switchByWebElementDemo(WebDriver driver) {

        try {
            // Find the iFrame element
            WebElement iFrame = driver.findElement(By.className("content-frame"));

            // Switch to iFrame
            driver.switchTo().frame(iFrame);
            System.out.println("Switched to iFrame by WebElement");

            // Interact
            WebElement heading = driver.findElement(By.tagName("h1"));
            System.out.println("Heading in iFrame: " + heading.getText());

            // Switch back
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handle Nested iFrames
     * Structure: Default Content → iFrame1 → iFrame2
     */
    public static void nestedIFrameDemo(WebDriver driver) {

        try {
            // Switch to outer iFrame
            driver.switchTo().frame("outerFrame");
            System.out.println("Switched to outer iFrame");

            // Switch to inner iFrame (relative to outer)
            driver.switchTo().frame("innerFrame");
            System.out.println("Switched to inner iFrame");

            // Interact with element in inner iFrame
            WebElement button = driver.findElement(By.id("nestedButton"));
            button.click();

            // Go back to outer iFrame
            driver.switchTo().parentFrame();
            System.out.println("Switched to parent frame");

            // Go back to default content
            driver.switchTo().defaultContent();
            System.out.println("Back to default content");

        } catch (Exception e) {
            System.out.println("Error with nested iFrames: " + e.getMessage());
        }
    }

    /**
     * Find element inside iFrame using XPath
     * Can also combine with frame switching
     */
    public static void findElementInIFrame(WebDriver driver, String iFrameId, String xpath) {

        try {
            // Switch to iFrame
            driver.switchTo().frame(iFrameId);

            // Find element
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();

            // Switch back
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Interview Question: How to check if element is in iFrame?
     * Answer: Try to find element, if not found in default content,
     *         switch to each iFrame and search
     */
    public static WebElement findElementInAnyIFrame(WebDriver driver, By locator) {

        try {
            // Try in default content first
            return driver.findElement(locator);

        } catch (Exception e) {
            // Not in default content, search in iFrames
            java.util.List<WebElement> iFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < iFrames.size(); i++) {

                try {
                    driver.switchTo().frame(i);
                    WebElement element = driver.findElement(locator);
                    driver.switchTo().defaultContent();
                    return element;

                } catch (Exception ex) {
                    driver.switchTo().defaultContent();
                }
            }
        }

        return null;
    }
}
