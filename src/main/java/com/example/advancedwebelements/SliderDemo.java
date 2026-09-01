package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SliderDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.example.com");

            WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='70'; arguments[0].dispatchEvent(new Event('input'));", slider);

            String value = slider.getAttribute("value");
            System.out.println("Slider value: " + value);

        } catch (Exception e) {
            System.out.println("Slider example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
