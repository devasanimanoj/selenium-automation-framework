package com.example.advancedwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RichTextEditorDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://ckeditor.com/ckeditor-5/demo/");

            driver.switchTo().frame(0);
            WebElement editor = driver.findElement(By.cssSelector(".ck-editor__editable"));
            editor.click();
            editor.sendKeys("This text is typed inside a rich text editor.");

            String text = editor.getText();
            System.out.println("Editor content: " + text);

        } catch (Exception e) {
            System.out.println("Rich text editor example failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
