package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePickerDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/date-picker");

            WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
            dateInput.click();

            String targetMonthYear = "June 2026";
            while (true) {
                String currentMonthYear = driver.findElement(By.className("react-datepicker__current-month")).getText();
                if (currentMonthYear.contains("June") && currentMonthYear.contains("2026")) {
                    break;
                }

                driver.findElement(By.cssSelector("button.react-datepicker__navigation--next")).click();
            }

            driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='15']")).click();
            System.out.println("Selected date successfully.");

        } catch (Exception e) {
            System.out.println("Date-picker example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
