package com.example.webtables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Web Tables - Interview Common Scenarios
 * 
 * Common Interview Scenarios and Best Practices
 */
public class InterviewScenariosDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== SCENARIO 1: Find value and click Edit button =====
        /*
        Requirement: Find "Manoj" and click Edit button in the same row
        */
        findAndClickEdit(driver, "Manoj");

        // ===== SCENARIO 2: Get value from another column in same row =====
        /*
        Requirement: Find "Manoj" and get his department
        */
        getColumnValueFromRow(driver, "Manoj", 2); // Column index 2 = Department

        // ===== SCENARIO 3: Verify entire row data =====
        /*
        Requirement: Find "Manoj" and verify his department is "Testing"
        */
        verifyRowData(driver, "Manoj", "Testing");

        driver.quit();
    }

    /**
     * Scenario 1: Find employee and click Edit button
     */
    private static void findAndClickEdit(WebDriver driver, String employeeName) {

        try {
            String xpath = "//tr[td[text()='" + employeeName + "']]//button[text()='Edit']";
            WebElement editButton = driver.findElement(By.xpath(xpath));
            editButton.click();

            System.out.println("Clicked Edit for " + employeeName);

        } catch (Exception e) {
            System.out.println("Failed to click Edit: " + e.getMessage());
        }
    }

    /**
     * Scenario 2: Get value from specific column in the row containing target employee
     * 
     * @param columnIndex 0=ID, 1=Name, 2=Department, 3=Action
     */
    private static void getColumnValueFromRow(WebDriver driver, String employeeName,
            int columnIndex) {

        try {
            String xpath = "//tr[td[text()='" + employeeName + "']]";
            WebElement row = driver.findElement(By.xpath(xpath));

            // Get all cells and extract specific column
            String cellValue = row.findElements(By.tagName("td")).get(columnIndex)
                    .getText();

            System.out.println("Column value for " + employeeName + ": " + cellValue);

        } catch (Exception e) {
            System.out.println("Failed to get column value: " + e.getMessage());
        }
    }

    /**
     * Scenario 3: Verify row data
     */
    private static void verifyRowData(WebDriver driver, String employeeName,
            String expectedDepartment) {

        try {
            String xpath = "//tr[td[text()='" + employeeName + "']]";
            WebElement row = driver.findElement(By.xpath(xpath));

            String rowText = row.getText();

            if (rowText.contains(expectedDepartment)) {
                System.out.println("Verification passed: " + employeeName
                        + " is in " + expectedDepartment + " department");
            } else {
                System.out.println("Verification failed: Expected department not found");
            }

        } catch (Exception e) {
            System.out.println("Failed to verify row: " + e.getMessage());
        }
    }
}
