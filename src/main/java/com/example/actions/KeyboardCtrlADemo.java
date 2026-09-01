package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Keyboard Actions - CTRL + A (Select All)
 * Demonstrates how to perform keyboard combinations using keyDown and keyUp
 */
public class KeyboardCtrlADemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        WebElement input = driver.findElement(By.id("username"));

        input.click();

        input.sendKeys("Old Value");

        Actions actions = new Actions(driver);

        // CTRL + A (Select All)
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();

        // Type new value to replace selected text
        actions.sendKeys("Manoj")
                .perform();

        driver.quit();
    }
}
