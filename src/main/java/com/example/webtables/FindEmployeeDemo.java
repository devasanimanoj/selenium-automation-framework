package com.example.webtables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Find Particular Value in Web Table
 * 
 * Demonstrates how to search for a specific value in a table
 * and identify which row contains it.
 */
public class FindEmployeeDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        WebElement table = driver.findElement(By.id("employeeTable"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String searchEmployee = "Manoj";
        boolean found = false;

        // Loop through rows to find employee
        for (WebElement row : rows) {

            if (row.getText().contains(searchEmployee)) {

                System.out.println("Employee found: " + row.getText());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found: " + searchEmployee);
        }

        driver.quit();
    }
}
