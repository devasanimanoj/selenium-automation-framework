package com.example.advancedwebelements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoSuggestionDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("selenium");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> suggestions = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul li"))
            );

            if (!suggestions.isEmpty()) {
                suggestions.get(0).click();
                System.out.println("Selected first suggestion.");
            } else {
                System.out.println("No suggestions found.");
            }

        } catch (Exception e) {
            System.out.println("Auto-suggestion example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
