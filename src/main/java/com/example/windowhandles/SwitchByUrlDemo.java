package com.example.windowhandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Switching Between Windows Using URL
 * 
 * This is useful when the window title isn't stable or unique.
 * Use getCurrentUrl().contains() to identify windows.
 */
public class SwitchByUrlDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Iterate through all windows and find by URL
        for (String window : driver.getWindowHandles()) {

            driver.switchTo().window(window);

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Window URL: " + currentUrl);

            // Switch to payment window if found
            if (currentUrl.contains("payment")) {

                System.out.println("Payment window found");
                break;
            }
        }

        driver.quit();
    }
}
