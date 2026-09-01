package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Manual Drag and Drop using clickAndHold, moveToElement, and release
 * Use this when dragAndDrop() doesn't work reliably with custom JavaScript interfaces
 */
public class ManualDragDropDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        WebElement source = driver.findElement(By.id("source"));

        WebElement target = driver.findElement(By.id("target"));

        Actions actions = new Actions(driver);

        // Manual drag and drop sequence
        actions
                .clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();

        driver.quit();
    }
}
