package com.example.windowhandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Selenium 4 - Open New Tab
 * 
 * Selenium 4 introduced switchTo().newWindow(WindowType.TAB) method
 * to programmatically open a new tab without clicking any link.
 */
public class NewTabDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        System.out.println("Parent Tab Title: " + driver.getTitle());

        // Open new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Navigate in new tab
        driver.get("https://google.com");

        System.out.println("New Tab Title: " + driver.getTitle());

        driver.quit();
    }
}
