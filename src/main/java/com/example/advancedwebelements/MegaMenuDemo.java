package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MegaMenuDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.amazon.in/");

            WebElement accountMenu = driver.findElement(By.id("nav-link-accountList"));
            Actions actions = new Actions(driver);
            actions.moveToElement(accountMenu).perform();

            WebElement yourAccount = driver.findElement(By.xpath("//div[@id='nav-flyout-accountList']//span[contains(text(),'Your Account')]"));
            System.out.println("Menu item text: " + yourAccount.getText());
            yourAccount.click();

        } catch (Exception e) {
            System.out.println("Mega menu example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
