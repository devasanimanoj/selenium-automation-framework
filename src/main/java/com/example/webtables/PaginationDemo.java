package com.example.webtables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Web Table with Pagination
 * 
 * Very common real-world scenario.
 * 
 * Pagination Logic:
 * 1. Search current page for the target record
 * 2. If found: Perform action
 * 3. If not found:
 *    a. Check if Next button is enabled
 *    b. Click Next button
 *    c. Repeat search
 * 4. If Next button is disabled: Record not found
 * 
 * Requirement: Find employee "Kiran" across all pages.
 */
public class PaginationDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        boolean found = false;
        int pageNumber = 1;

        while (!found) {

            System.out.println("Searching page " + pageNumber);

            // Search for "Kiran" on current page using XPath
            if (driver.findElements(By.xpath("//tr[td[text()='Kiran']]"))
                    .size() > 0) {

                System.out.println("Kiran found on page " + pageNumber);
                found = true;

                // Perform action on the found row
                driver.findElement(By.xpath("//tr[td[text()='Kiran']]//button"))
                        .click();

                break;
            }

            // Try to find and click Next button
            try {
                WebElement nextButton = driver.findElement(By.id("next"));

                // Check if button is enabled
                if (!nextButton.isEnabled()) {

                    System.out.println("Next button is disabled. Employee not found.");
                    break;
                }

                // Click Next button
                nextButton.click();
                pageNumber++;

                // Small wait for page load
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println("Error navigating pages: " + e.getMessage());
                break;
            }
        }

        driver.quit();
    }
}
