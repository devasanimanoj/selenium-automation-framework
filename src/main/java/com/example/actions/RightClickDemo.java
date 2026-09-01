package com.example.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Right Click - contextClick()
 * A right-click is called a context click in Selenium
 */
public class RightClickDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        WebElement element = driver.findElement(By.id("rightClick"));

        Actions actions = new Actions(driver);

        // Right click
        actions.contextClick(element).perform();

        driver.quit();
    }
}
