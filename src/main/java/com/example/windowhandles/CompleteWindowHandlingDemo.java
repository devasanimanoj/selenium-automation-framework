package com.example.windowhandles;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Complete Real-World Window Handling Pattern
 * 
 * This demonstrates the recommended approach for handling parent and child windows:
 * 1. Launch browser
 * 2. Store parent handle
 * 3. Perform action that opens new window
 * 4. Get all windows
 * 5. Find and switch to child window
 * 6. Work in child window
 * 7. Close child window
 * 8. Switch back to parent
 * 9. Continue parent automation
 */
public class CompleteWindowHandlingDemo {

    public static void main(String[] args) {

        // 1. Launch browser
        WebDriver driver = new ChromeDriver();

        // 2. Maximize
        driver.manage().window().maximize();

        // 3. Open application
        driver.get("https://example.com");

        // 4. Store parent handle
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window: " + parentWindow);

        // 5. Click link/button that opens new window
        WebElement link = driver.findElement(By.id("openWindow"));
        link.click();

        // 6. Get all windows
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("Total windows: " + allWindows.size());

        // 7. Find child window
        String childWindow = null;
        for (String window : allWindows) {

            if (!window.equals(parentWindow)) {

                childWindow = window;
                driver.switchTo().window(window);
                break;
            }
        }

        // 8. Work in child window
        System.out.println("Child URL: " + driver.getCurrentUrl());
        System.out.println("Child Title: " + driver.getTitle());

        // Perform actions in child window here

        // 9. Close child window
        driver.close();
        System.out.println("Child window closed");

        // 10. Switch back to parent window
        driver.switchTo().window(parentWindow);

        // 11. Continue parent automation
        System.out.println("Back to Parent URL: " + driver.getCurrentUrl());

        // 12. End session
        driver.quit();
    }
}
