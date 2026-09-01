package com.example.windowhandles;

import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Window Handles - Basic Demo
 * 
 * When Selenium opens multiple tabs/windows, each has a unique identifier called a window handle.
 * - getWindowHandle() - Returns the current window handle (String)
 * - getWindowHandles() - Returns all open window handles (Set<String>)
 */
public class WindowHandleBasicDemo {

    public static void main(String[] args) {

        // Launch Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open application
        driver.get("https://example.com");

        // Get current window handle (Parent window)
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window: " + parentWindow);

        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("All Windows: " + allWindows);

        // Close browser
        driver.quit();
    }
}
