package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Drag and Drop - dragAndDrop()
 * Used for dragging elements and dropping them at target locations
 */
public class DragAndDropDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Source element
        WebElement source = driver.findElement(By.id("source"));

        // Target element
        WebElement target = driver.findElement(By.id("target"));

        // Actions object
        Actions actions = new Actions(driver);

        // Drag source to target
        actions.dragAndDrop(source, target).perform();

        driver.quit();
    }
}
