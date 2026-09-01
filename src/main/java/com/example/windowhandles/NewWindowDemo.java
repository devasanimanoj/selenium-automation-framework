package com.example.windowhandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Selenium 4 - Open New Browser Window
 * 
 * Use WindowType.WINDOW to open a new browser window (not a tab)
 * This is different from WindowType.TAB
 */
public class NewWindowDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        System.out.println("Parent Window Title: " + driver.getTitle());

        // Open new browser window
        driver.switchTo().newWindow(WindowType.WINDOW);

        driver.get("https://google.com");

        System.out.println("New Window Title: " + driver.getTitle());

        driver.quit();
    }
}
