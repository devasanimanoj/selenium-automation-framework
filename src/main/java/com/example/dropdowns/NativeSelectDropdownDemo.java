package com.example.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Native HTML <select> Dropdown using Select Class
 * 
 * The Select class is used for standard HTML <select> dropdowns.
 * It provides convenient methods to select options.
 * 
 * HTML Example:
 * <select id="country">
 *   <option value="ind">India</option>
 *   <option value="usa">USA</option>
 *   <option value="uk">UK</option>
 * </select>
 */
public class NativeSelectDropdownDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Locate the dropdown element
        WebElement dropdownElement = driver.findElement(By.id("country"));

        // Create Select object
        Select select = new Select(dropdownElement);

        // ===== SELECT BY VISIBLE TEXT =====
        select.selectByVisibleText("India");
        System.out.println("Selected by visible text: India");

        // ===== SELECT BY VALUE =====
        select.selectByValue("usa");
        System.out.println("Selected by value: usa");

        // ===== SELECT BY INDEX =====
        select.selectByIndex(0);
        System.out.println("Selected by index: 0");

        // ===== GET SELECTED OPTION =====
        WebElement selectedOption = select.getFirstSelectedOption();
        System.out.println("Currently selected: " + selectedOption.getText());

        // ===== GET ALL OPTIONS =====
        java.util.List<WebElement> allOptions = select.getOptions();
        System.out.println("Total options: " + allOptions.size());

        for (WebElement option : allOptions) {
            System.out.println("Option: " + option.getText());
        }

        driver.quit();
    }
}
