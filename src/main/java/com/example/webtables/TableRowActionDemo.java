package com.example.webtables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Find Value and Click Corresponding Button - VERY IMPORTANT
 * 
 * This is an extremely common and important interview scenario.
 * 
 * Requirement: Find "Manoj" in the table and click the "View" button in the same row.
 * 
 * Table Structure:
 * ID    | Name   | Department | Action
 * 101   | Manoj  | Testing    | View
 * 102   | Rahul  | Development| View
 * 
 * Logic:
 * 1. Find row containing "Manoj"
 * 2. Stay inside that row
 * 3. Find View button
 * 4. Click it
 */
public class TableRowActionDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Locate table
        WebElement table = driver.findElement(By.id("employeeTable"));

        // Get all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String targetEmployee = "Manoj";

        // Iterate through rows
        for (WebElement row : rows) {

            // Check if this row contains the target employee
            if (row.getText().contains(targetEmployee)) {

                System.out.println("Found row: " + row.getText());

                // Find button inside this specific row (not the entire page)
                WebElement viewButton = row.findElement(By.tagName("button"));

                // Click the button
                viewButton.click();

                System.out.println("Clicked View button for " + targetEmployee);

                break;
            }
        }

        driver.quit();
    }
}
