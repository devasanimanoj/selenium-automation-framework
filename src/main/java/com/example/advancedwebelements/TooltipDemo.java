package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TooltipDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.example.com");

            WebElement element = driver.findElement(By.id("tooltipTarget"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            WebElement tooltip = driver.findElement(By.cssSelector(".tooltip"));
            String tooltipText = tooltip.getText();
            System.out.println("Tooltip text: " + tooltipText);

        } catch (Exception e) {
            System.out.println("Tooltip example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
