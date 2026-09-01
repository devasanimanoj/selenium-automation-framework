package com.example.webtables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Dynamic XPath Approach for Web Tables
 * 
 * Instead of looping through rows, use dynamic XPath to find the element directly.
 * 
 * This approach is faster and cleaner for simple operations but less flexible
 * than looping when complex logic is needed.
 * 
 * XPath Examples:
 * 
 * 1. Find row containing "Manoj" and click its button:
 *    //tr[td[text()='Manoj']]//button
 * 
 * 2. Find button with specific text in Manoj's row:
 *    //tr[td[text()='Manoj']]//button[text()='Edit']
 * 
 * 3. Find row with multiple conditions (Manoj AND Testing):
 *    //tr[td[text()='Manoj'] and td[text()='Testing']]//button
 * 
 * 4. Find link in Manoj's row:
 *    //tr[td[text()='Manoj']]//a
 * 
 * 5. Find checkbox in Manoj's row:
 *    //tr[td[text()='Manoj']]//input[@type='checkbox']
 */
public class DynamicTableXPathDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        try {
            // Find Manoj's row and click the View button
            driver.findElement(By.xpath("//tr[td[text()='Manoj']]//button"))
                    .click();

            System.out.println("Clicked View button for Manoj using XPath");

        } catch (Exception e) {
            System.out.println("Employee or button not found: " + e.getMessage());
        }

        driver.quit();
    }
}
