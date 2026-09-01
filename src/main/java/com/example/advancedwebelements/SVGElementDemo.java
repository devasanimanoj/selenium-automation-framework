package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SVGElementDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.w3schools.com/graphics/svg_circle.asp");

            WebElement svg = driver.findElement(By.tagName("svg"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement circle = (WebElement) js.executeScript("return document.querySelector('svg circle');");

            String cx = circle.getAttribute("cx");
            String cy = circle.getAttribute("cy");
            System.out.println("SVG Circle coordinates: cx=" + cx + ", cy=" + cy);

            js.executeScript("arguments[0].setAttribute('fill', 'red')", circle);
            System.out.println("SVG filled color changed to red.");

        } catch (Exception e) {
            System.out.println("SVG example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
