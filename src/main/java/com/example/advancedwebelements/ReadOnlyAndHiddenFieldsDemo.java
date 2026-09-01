package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadOnlyAndHiddenFieldsDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.example.com");

            WebElement hidden = driver.findElement(By.id("hiddenElement"));
            System.out.println("Hidden displayed? " + hidden.isDisplayed());

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", hidden);
            System.out.println("Hidden field made visible");

            WebElement readOnly = driver.findElement(By.cssSelector("input[readonly='true']"));
            String value = readOnly.getAttribute("value");
            System.out.println("Read-only field value: " + value);

            WebElement disabled = driver.findElement(By.cssSelector("input[disabled]"));
            System.out.println("Disabled status: " + disabled.isEnabled());

        } catch (Exception e) {
            System.out.println("Read-only/hidden field example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
