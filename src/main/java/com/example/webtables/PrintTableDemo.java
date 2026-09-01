package com.example.webtables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Print Complete Web Table
 * 
 * This is an important interview program.
 * Demonstrates how to:
 * 1. Get all rows
 * 2. Loop through each row
 * 3. Get all cells in each row
 * 4. Print cell values
 */
public class PrintTableDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Locate table
        WebElement table = driver.findElement(By.id("employeeTable"));

        // Get all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        System.out.println("Total rows: " + rows.size());
        System.out.println("\n--- Table Data ---\n");

        // Loop through rows
        for (WebElement row : rows) {

            // Get cells (both th and td)
            List<WebElement> cells = row.findElements(By.tagName("td"));
            
            // If no td elements, try th (for header rows)
            if (cells.isEmpty()) {
                cells = row.findElements(By.tagName("th"));
            }

            // Print each cell value
            for (WebElement cell : cells) {

                System.out.print(cell.getText() + " | ");
            }

            System.out.println();
        }

        driver.quit();
    }
}
