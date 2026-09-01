package com.example.windowhandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Switching Between Windows Using Title or URL
 * 
 * Instead of relying on window handle order, identify windows by:
 * - Title using getTitle()
 * - URL using getCurrentUrl()
 * 
 * This is more robust when dealing with multiple windows.
 */
public class SwitchByTitleDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Iterate through all windows and find by title
        for (String window : driver.getWindowHandles()) {

            driver.switchTo().window(window);

            String title = driver.getTitle();
            System.out.println("Window Title: " + title);

            // Switch to Payment Page if found
            if (title.equals("Payment Page")) {

                System.out.println("Payment window found");
                break;
            }
        }

        driver.quit();
    }
}
