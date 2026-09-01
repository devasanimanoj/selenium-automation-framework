package com.example.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * Custom Dropdowns (Bootstrap, Material UI, React, Angular)
 * 
 * Modern frameworks often use custom HTML structure instead of <select>.
 * These dropdowns typically use <div>, <ul>, <li> with custom styling.
 * 
 * HTML Example (Bootstrap):
 * <div class="dropdown">
 *   <button id="dropdownBtn">Select Country</button>
 *   <div class="dropdown-menu">
 *     <a class="dropdown-item">India</a>
 *     <a class="dropdown-item">USA</a>
 *     <a class="dropdown-item">UK</a>
 *   </div>
 * </div>
 * 
 * Approach:
 * 1. Click to open dropdown
 * 2. Find all options
 * 3. Click the desired option
 */
public class CustomDropdownDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== APPROACH 1: Bootstrap Dropdown =====
        selectBootstrapDropdown(driver, "India");

        // ===== APPROACH 2: Generic Custom Dropdown =====
        selectCustomDropdown(driver, "USA", "//ul[@id='countryDropdown']/li");

        driver.quit();
    }

    /**
     * Select option from Bootstrap dropdown
     */
    private static void selectBootstrapDropdown(WebDriver driver, String optionText) {

        try {
            // Step 1: Click dropdown button to open
            WebElement dropdownBtn = driver.findElement(By.id("dropdownBtn"));
            dropdownBtn.click();

            System.out.println("Opened Bootstrap dropdown");

            // Step 2: Wait a moment for dropdown to appear
            Thread.sleep(500);

            // Step 3: Find and click the option
            WebElement option = driver.findElement(By.xpath(
                    "//div[@class='dropdown-menu']//a[text()='" + optionText + "']"
            ));
            option.click();

            System.out.println("Selected: " + optionText);

        } catch (Exception e) {
            System.out.println("Error selecting from Bootstrap dropdown: " + e.getMessage());
        }
    }

    /**
     * Select option from custom dropdown using XPath
     */
    private static void selectCustomDropdown(WebDriver driver, String optionText,
            String dropdownXPath) {

        try {
            // Step 1: Click dropdown trigger
            WebElement dropdownTrigger = driver.findElement(By.xpath(dropdownXPath.split("li")[0] 
                    + "preceding::button[1]"));
            dropdownTrigger.click();

            System.out.println("Opened custom dropdown");

            // Step 2: Find all options
            List<WebElement> allOptions = driver.findElements(By.xpath(dropdownXPath));

            // Step 3: Find and click matching option
            for (WebElement option : allOptions) {
                if (option.getText().equals(optionText)) {
                    option.click();
                    System.out.println("Selected: " + optionText);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error selecting from custom dropdown: " + e.getMessage());
        }
    }

    /**
     * More Robust Approach: Click Dropdown and Select
     */
    public static void selectFromCustomDropdownRobust(WebDriver driver, 
            String dropdownId, String optionText) {

        try {
            // Find and click the dropdown button
            WebElement dropdown = driver.findElement(By.id(dropdownId));
            dropdown.click();

            // Wait for options to appear
            Thread.sleep(300);

            // Find all options in the dropdown
            List<WebElement> options = driver.findElements(
                    By.xpath("//div[@class='dropdown-content']//span")
            );

            // Click the matching option
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    option.click();
                    System.out.println("Selected from dropdown: " + optionText);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
