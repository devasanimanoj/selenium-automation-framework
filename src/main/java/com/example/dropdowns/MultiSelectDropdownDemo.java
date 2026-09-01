package com.example.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

/**
 * Multi-Select Dropdown using Select Class
 * 
 * HTML Example:
 * <select id="skills" multiple>
 *   <option value="java">Java</option>
 *   <option value="python">Python</option>
 *   <option value="selenium">Selenium</option>
 * </select>
 * 
 * Multi-select allows selecting multiple options.
 */
public class MultiSelectDropdownDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        // Locate multi-select dropdown
        WebElement dropdownElement = driver.findElement(By.id("skills"));

        // Create Select object
        Select select = new Select(dropdownElement);

        // Check if dropdown supports multiple selection
        System.out.println("Is multi-select: " + select.isMultiple());

        // ===== SELECT MULTIPLE OPTIONS =====
        select.selectByVisibleText("Java");
        select.selectByVisibleText("Python");
        select.selectByVisibleText("Selenium");

        System.out.println("Selected multiple options");

        // ===== GET ALL SELECTED OPTIONS =====
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        System.out.println("Total selected options: " + selectedOptions.size());

        for (WebElement option : selectedOptions) {
            System.out.println("Selected: " + option.getText());
        }

        // ===== DESELECT OPTIONS =====
        select.deselectByVisibleText("Python");
        System.out.println("Deselected: Python");

        // ===== DESELECT ALL =====
        select.deselectAll();
        System.out.println("Deselected all options");

        driver.quit();
    }
}
