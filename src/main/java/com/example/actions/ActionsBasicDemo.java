package com.example.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Basic Actions class demonstration
 * Actions is used for advanced mouse and keyboard interactions
 */
public class ActionsBasicDemo {

    public static void main(String[] args) {

        // Launch Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open application
        driver.get("https://www.google.com");

        // Create Actions object
        Actions actions = new Actions(driver);

        // Actions code goes here

        // Close browser
        driver.quit();
    }
}
