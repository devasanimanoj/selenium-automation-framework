package com.example.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Alert Handling in Selenium
 * 
 * Three types of alerts in JavaScript:
 * 1. Simple Alert - alert("message")
 * 2. Confirmation Alert - confirm("message")
 * 3. Prompt Alert - prompt("message", "default value")
 * 
 * Alert interface methods:
 * - accept() - Click OK button
 * - dismiss() - Click Cancel button
 * - getText() - Get alert message
 * - sendKeys() - Type text in prompt
 */
public class AlertHandlingDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // ===== SIMPLE ALERT =====
        handleSimpleAlert(driver);

        // ===== CONFIRMATION ALERT =====
        handleConfirmationAlert(driver);

        // ===== PROMPT ALERT =====
        handlePromptAlert(driver);

        driver.quit();
    }

    /**
     * Handle Simple Alert
     * Steps: 1. Click button that triggers alert
     *        2. Switch to alert
     *        3. Get message (optional)
     *        4. Click OK
     */
    public static void handleSimpleAlert(WebDriver driver) {

        try {
            // Click button that shows alert
            driver.findElement(By.id("simpleAlertBtn")).click();

            System.out.println("Simple alert triggered");

            // Switch to alert
            Alert alert = driver.switchTo().alert();

            // Get alert text
            String alertMessage = alert.getText();
            System.out.println("Alert message: " + alertMessage);

            // Accept (click OK)
            alert.accept();

            System.out.println("Simple alert accepted");

        } catch (Exception e) {
            System.out.println("Error handling simple alert: " + e.getMessage());
        }
    }

    /**
     * Handle Confirmation Alert
     * Steps: 1. Click button
     *        2. Get alert message
     *        3. Accept (OK) or Dismiss (Cancel)
     */
    public static void handleConfirmationAlert(WebDriver driver) {

        try {
            // Click button that shows confirm dialog
            driver.findElement(By.id("confirmAlertBtn")).click();

            System.out.println("Confirmation alert triggered");

            // Switch to alert
            Alert alert = driver.switchTo().alert();

            String message = alert.getText();
            System.out.println("Confirm message: " + message);

            // Click OK (accept)
            alert.accept();
            System.out.println("Confirmed");

            // OR Click Cancel (dismiss)
            // alert.dismiss();
            // System.out.println("Cancelled");

        } catch (Exception e) {
            System.out.println("Error handling confirmation alert: " + e.getMessage());
        }
    }

    /**
     * Handle Prompt Alert
     * Steps: 1. Click button
     *        2. Type text in prompt
     *        3. Accept (OK) to submit
     */
    public static void handlePromptAlert(WebDriver driver) {

        try {
            // Click button that shows prompt
            driver.findElement(By.id("promptAlertBtn")).click();

            System.out.println("Prompt alert triggered");

            // Switch to alert
            Alert alert = driver.switchTo().alert();

            String promptMessage = alert.getText();
            System.out.println("Prompt message: " + promptMessage);

            // Type text in prompt
            alert.sendKeys("Manoj");
            System.out.println("Entered text in prompt");

            // Accept to submit
            alert.accept();

            System.out.println("Prompt accepted with value: Manoj");

        } catch (Exception e) {
            System.out.println("Error handling prompt alert: " + e.getMessage());
        }
    }

    /**
     * Check if Alert is Present
     */
    public static boolean isAlertPresent(WebDriver driver) {

        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
