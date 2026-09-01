package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Web Element #1-5: Text Box, Button, Checkbox, Radio Button, Links
 * 
 * Learning Objectives:
 * - How to interact with basic HTML elements
 * - Methods: sendKeys(), click(), isEnabled(), isDisplayed(), isSelected(), getText(), getAttribute()
 * - Understanding element state verification
 */
public class BasicWebElementsDemo {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Demo website - replace with your test website
            driver.navigate().to("https://demoqa.com/text-box");

            // ===== TEXT BOX OPERATIONS #1 =====
            System.out.println("===== TEXT BOX DEMO =====");
            textBoxOperations();

            // ===== BUTTON OPERATIONS #2 =====
            driver.navigate().to("https://demoqa.com/buttons");
            System.out.println("\n===== BUTTON DEMO =====");
            buttonOperations();

            // ===== CHECKBOX OPERATIONS #3 =====
            driver.navigate().to("https://demoqa.com/checkbox");
            System.out.println("\n===== CHECKBOX DEMO =====");
            checkboxOperations();

            // ===== RADIO BUTTON OPERATIONS #4 =====
            driver.navigate().to("https://demoqa.com/radio-button");
            System.out.println("\n===== RADIO BUTTON DEMO =====");
            radioButtonOperations();

            // ===== LINKS OPERATIONS #5 =====
            driver.navigate().to("https://demoqa.com/links");
            System.out.println("\n===== LINKS DEMO =====");
            linksOperations();

        } finally {
            driver.quit();
        }
    }

    // ===== #1: TEXT BOX OPERATIONS =====
    static void textBoxOperations() {
        try {
            // Find text box element
            WebElement fullNameTextBox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("fullName"))
            );

            // Clear any existing text
            fullNameTextBox.clear();
            System.out.println("✓ Text box cleared");

            // Send keys to text box
            fullNameTextBox.sendKeys("Manoj Kumar");
            System.out.println("✓ Text entered: Manoj Kumar");

            // Get the value using getAttribute
            String enteredText = fullNameTextBox.getAttribute("value");
            System.out.println("✓ Text from getAttribute: " + enteredText);

            // Get the text using getText (usually for display text)
            String displayedText = fullNameTextBox.getText();
            System.out.println("✓ Text from getText: " + displayedText);

            // Check if enabled
            boolean isEnabled = fullNameTextBox.isEnabled();
            System.out.println("✓ Is text box enabled? " + isEnabled);

            // Check if displayed
            boolean isDisplayed = fullNameTextBox.isDisplayed();
            System.out.println("✓ Is text box displayed? " + isDisplayed);

        } catch (Exception e) {
            System.out.println("✗ Text Box Error: " + e.getMessage());
        }
    }

    // ===== #2: BUTTON OPERATIONS =====
    static void buttonOperations() {
        try {
            // Different button types
            WebElement doubleClickBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("doubleClickBtn"))
            );

            // Check if button is enabled
            boolean isButtonEnabled = doubleClickBtn.isEnabled();
            System.out.println("✓ Button enabled: " + isButtonEnabled);

            // Check if button is displayed
            boolean isButtonDisplayed = doubleClickBtn.isDisplayed();
            System.out.println("✓ Button displayed: " + isButtonDisplayed);

            // Get button text
            String buttonText = doubleClickBtn.getText();
            System.out.println("✓ Button text: " + buttonText);

            // Simple click
            WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
            rightClickBtn.click();
            System.out.println("✓ Button clicked");

            // Verify button state after click
            String messageText = driver.findElement(By.id("doubleClickMessage")).getText();
            System.out.println("✓ Message after click: " + messageText);

            // Get button tag name
            String tagName = doubleClickBtn.getTagName();
            System.out.println("✓ Button tag name: " + tagName);

        } catch (Exception e) {
            System.out.println("✗ Button Error: " + e.getMessage());
        }
    }

    // ===== #3: CHECKBOX OPERATIONS =====
    static void checkboxOperations() {
        try {
            // Locate checkbox
            WebElement checkbox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))
            );

            // Check if checkbox is selected
            boolean isSelected = checkbox.isSelected();
            System.out.println("✓ Initially selected: " + isSelected);

            // Click to select
            if (!isSelected) {
                checkbox.click();
                System.out.println("✓ Checkbox clicked");
            }

            // Verify if now selected
            isSelected = checkbox.isSelected();
            System.out.println("✓ After click selected: " + isSelected);

            // Get the value attribute
            String value = checkbox.getAttribute("value");
            System.out.println("✓ Checkbox value: " + value);

            // Check if enabled
            boolean isEnabled = checkbox.isEnabled();
            System.out.println("✓ Checkbox enabled: " + isEnabled);

        } catch (Exception e) {
            System.out.println("✗ Checkbox Error: " + e.getMessage());
        }
    }

    // ===== #4: RADIO BUTTON OPERATIONS =====
    static void radioButtonOperations() {
        try {
            // Locate radio button
            WebElement radioButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='radio'][@value='Impressive']"))
            );

            // Check if radio button is selected
            boolean isSelected = radioButton.isSelected();
            System.out.println("✓ Initially selected: " + isSelected);

            // Click to select
            if (!isSelected) {
                radioButton.click();
                System.out.println("✓ Radio button clicked");
            }

            // Verify selection
            isSelected = radioButton.isSelected();
            System.out.println("✓ After click selected: " + isSelected);

            // Get value
            String value = radioButton.getAttribute("value");
            System.out.println("✓ Radio button value: " + value);

            // Get name attribute (usually all related radio buttons have same name)
            String name = radioButton.getAttribute("name");
            System.out.println("✓ Radio button name: " + name);

        } catch (Exception e) {
            System.out.println("✗ Radio Button Error: " + e.getMessage());
        }
    }

    // ===== #5: LINKS OPERATIONS =====
    static void linksOperations() {
        try {
            // Locate a link
            WebElement link = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Home')]"))
            );

            // Get link text
            String linkText = link.getText();
            System.out.println("✓ Link text: " + linkText);

            // Get href attribute
            String href = link.getAttribute("href");
            System.out.println("✓ Link href: " + href);

            // Get the tag name
            String tagName = link.getTagName();
            System.out.println("✓ Link tag name: " + tagName);

            // Check if link is enabled and displayed
            boolean isEnabled = link.isEnabled();
            boolean isDisplayed = link.isDisplayed();
            System.out.println("✓ Link enabled: " + isEnabled + ", displayed: " + isDisplayed);

            // Get title attribute if exists
            String title = link.getAttribute("title");
            System.out.println("✓ Link title: " + (title != null ? title : "N/A"));

            // Click the link
            link.click();
            System.out.println("✓ Link clicked");

        } catch (Exception e) {
            System.out.println("✗ Links Error: " + e.getMessage());
        }
    }
}
