package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContentEditableDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.example.com");

            WebElement editor = driver.findElement(By.cssSelector("div[contenteditable='true']"));
            editor.clear();
            editor.sendKeys("This is rich text content");

            String text = editor.getText();
            System.out.println("Editor text: " + text);

        } catch (Exception e) {
            System.out.println("Contenteditable example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
