package com.example.windowhandles;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Parent and Child Window Handling
 * 
 * When a button/link opens a new window/tab:
 * 1. Store parent window handle
 * 2. Perform action that opens new window
 * 3. Get all windows
 * 4. Find the child window by comparing handles
 * 5. Switch to child window
 */
public class ParentChildWindowDemo {

    public static void main(String[] args) {

        // Launch Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize
        driver.manage().window().maximize();

        // Open application
        driver.get("https://example.com");

        // Store parent window handle
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window: " + parentWindow);

        // Click button that opens new window
        WebElement button = driver.findElement(By.id("openWindow"));
        button.click();

        // Get all windows
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("All Windows: " + allWindows);

        // Find and switch to child window
        for (String window : allWindows) {

            if (!window.equals(parentWindow)) {

                // Switch to child window
                driver.switchTo().window(window);
                System.out.println("Switched to child window");

                break;
            }
        }

        // Now automation is in child window
        System.out.println("Child title: " + driver.getTitle());

        // Switch back to parent
        driver.switchTo().window(parentWindow);
        System.out.println("Parent title: " + driver.getTitle());

        // Close browser
        driver.quit();
    }
}
