package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Complete Actions Example
 * Combines several advanced Actions concepts:
 * - Mouse hover
 * - Double click
 * - Right click
 * - Keyboard actions
 */
public class ActionsCompleteDemo {

    public static void main(String[] args) {

        // Launch Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open application
        driver.get("https://example.com");

        // Create Actions object
        Actions actions = new Actions(driver);

        // Find menu element
        WebElement menu = driver.findElement(By.id("menu"));

        // 1. Mouse hover
        actions.moveToElement(menu).perform();

        // Find button
        WebElement button = driver.findElement(By.id("button"));

        // 2. Double click
        actions.doubleClick(button).perform();

        // 3. Right click
        actions.contextClick(button).perform();

        // 4. Keyboard action (CTRL + A)
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();

        // Close browser
        driver.quit();
    }
}
