package com.example.webtables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Access Specific Columns in Web Table
 * 
 * Table Structure:
 * Index | Column
 * 0     | ID
 * 1     | Name
 * 2     | Department
 * 3     | Action
 * 
 * Demonstrates how to extract data from specific columns.
 */
public class TableColumnDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        WebElement table = driver.findElement(By.id("employeeTable"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        System.out.println("--- Table Data (ID | Name | Department) ---\n");

        // Loop through rows
        for (WebElement row : rows) {

            // Get all cells in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Skip header row if no cells found (try th instead)
            if (cells.isEmpty()) {
                cells = row.findElements(By.tagName("th"));
            }

            // Check if row has enough cells
            if (cells.size() >= 3) {

                // Extract specific columns using index
                String id = cells.get(0).getText();           // Column 0 - ID
                String name = cells.get(1).getText();         // Column 1 - Name
                String department = cells.get(2).getText();   // Column 2 - Department

                System.out.println(id + " | " + name + " | " + department);
            }
        }

        driver.quit();
    }
}
