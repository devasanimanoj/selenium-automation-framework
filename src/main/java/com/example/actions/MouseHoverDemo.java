package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Mouse Hover - moveToElement()
 * Used for dropdown menus and hover-based interactions
 */
public class MouseHoverDemo {

    public static void main(String[] args) {

        // Launch Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize
        driver.manage().window().maximize();

        // Open application
        driver.get("https://example.com");

        // Locate menu
        WebElement products = driver.findElement(By.id("menu"));

        // Create Actions object
        Actions actions = new Actions(driver);

        // Mouse hover
        actions.moveToElement(products).perform();

        // Close browser
        driver.quit();
    }
}
