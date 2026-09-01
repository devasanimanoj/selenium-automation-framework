package com.example.webtables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Web Tables - Basic Demo
 * 
 * Basic HTML table structure:
 * <table id="employeeTable">
 *   <thead>
 *     <tr>
 *       <th>ID</th>
 *       <th>Name</th>
 *       <th>Department</th>
 *       <th>Action</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>101</td>
 *       <td>Manoj</td>
 *       <td>Testing</td>
 *       <td><button>View</button></td>
 *     </tr>
 *   </tbody>
 * </table>
 */
public class WebTableBasicDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Locate the table
        WebElement table = driver.findElement(By.id("employeeTable"));

        // Find all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Print row count
        System.out.println("Total rows: " + rows.size());

        // Close browser
        driver.quit();
    }
}
