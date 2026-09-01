package com.example.advancedwebelements;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Web Element #26: Web Components
 * 
 * Learning Objectives:
 * - Understand Web Components architecture
 * - Handle custom HTML elements
 * - Work with Shadow DOM in Web Components
 * - Interact with encapsulated component internals
 * 
 * Key Concepts:
 * - Web Components are reusable custom elements
 * - Built on: Custom Elements, Shadow DOM, HTML Templates, ES Modules
 * - Examples: Polymer, Lit, Stencil, Angular Elements
 * - Challenge: Internal structure is encapsulated in Shadow DOM
 * - Solution: Use getShadowRoot(), JavaScript, or component APIs
 */
public class WebComponentsDemo {
    static WebDriver driver;
    static JavascriptExecutor jsExecutor;
    static WebDriverWait wait;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Example: Ionic components or other web component libraries
            driver.navigate().to("https://ionicframework.com/docs/components/button");

            System.out.println("===== WEB COMPONENTS HANDLING =====");
            handleWebComponents();

        } finally {
            driver.quit();
        }
    }

    static void handleWebComponents() {
        try {
            // Method 1: Locate Web Components
            System.out.println("\n--- Method 1: Locate Web Components ---");
            locateWebComponents();

            // Method 2: Access Shadow DOM of Web Component
            System.out.println("\n--- Method 2: Access Shadow DOM ---");
            accessWebComponentShadowDOM();

            // Method 3: Interact with Web Component
            System.out.println("\n--- Method 3: Interact with Web Component ---");
            interactWithWebComponent();

            // Method 4: Call Web Component methods
            System.out.println("\n--- Method 4: Call Component Methods ---");
            callWebComponentMethods();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method 1: Locate Web Components
    static void locateWebComponents() {
        try {
            // Web components have custom tag names (with hyphen)
            java.util.List<WebElement> customElements = driver.findElements(By.tagName("ion-button"));
            System.out.println("✓ Found " + customElements.size() + " ion-button components");

            // Get component properties
            for (int i = 0; i < Math.min(3, customElements.size()); i++) {
                WebElement component = customElements.get(i);
                
                // Get custom properties
                String color = component.getAttribute("color");
                String size = component.getAttribute("size");
                String type = component.getAttribute("type");
                String disabled = component.getAttribute("disabled");
                
                System.out.println("  Component " + (i+1) + ":");
                System.out.println("    Color: " + color);
                System.out.println("    Size: " + size);
                System.out.println("    Type: " + type);
                System.out.println("    Disabled: " + disabled);
            }

        } catch (Exception e) {
            System.out.println("✗ Error locating components: " + e.getMessage());
        }
    }

    // Method 2: Access Shadow DOM of Web Component
    static void accessWebComponentShadowDOM() {
        try {
            // Find a web component
            WebElement component = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.tagName("ion-button"))
            );

            System.out.println("✓ Found web component: " + component.getTagName());

            // Access shadow root (if it has one)
            try {
                SearchContext shadowRoot = component.getShadowRoot();
                System.out.println("✓ Successfully accessed shadow root");

                // Find elements within shadow root
                WebElement button = shadowRoot.findElement(By.tagName("button"));
                System.out.println("✓ Found button element in shadow DOM");

                // Get internal button properties
                String buttonType = button.getAttribute("type");
                String buttonClass = button.getAttribute("class");
                System.out.println("✓ Internal button type: " + buttonType);
                System.out.println("✓ Internal button class: " + buttonClass);

            } catch (NoSuchShadowRootException e) {
                System.out.println("⚠ Component has no shadow root (might use Light DOM)");
            }

        } catch (Exception e) {
            System.out.println("✗ Error accessing shadow DOM: " + e.getMessage());
        }
    }

    // Method 3: Interact with Web Component
    static void interactWithWebComponent() {
        try {
            // Find ion-input component
            WebElement input = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.tagName("ion-input"))
            );

            System.out.println("✓ Found web component input");

            // Method 3A: Set value via component property
            String setValueScript = 
                "arguments[0].value = 'Web Component Test';";
            jsExecutor.executeScript(setValueScript, input);
            System.out.println("✓ Set component value via JavaScript");

            // Method 3B: Access internal input element
            SearchContext shadowRoot = input.getShadowRoot();
            WebElement internalInput = shadowRoot.findElement(By.tagName("input"));
            
            // Check current value
            String currentValue = internalInput.getAttribute("value");
            System.out.println("✓ Current internal input value: " + currentValue);

            // Method 3C: Trigger component event
            String triggerEventScript = 
                "arguments[0].dispatchEvent(new Event('ionChange', { bubbles: true }));";
            jsExecutor.executeScript(triggerEventScript, input);
            System.out.println("✓ Triggered ionChange event");

        } catch (Exception e) {
            System.out.println("✗ Error interacting with component: " + e.getMessage());
        }
    }

    // Method 4: Call Web Component methods
    static void callWebComponentMethods() {
        try {
            // Find a component with methods
            WebElement modal = driver.findElement(By.tagName("ion-modal"));
            if (modal != null) {
                // Web components can have methods
                String callMethodScript = 
                    "return arguments[0].open ? 'open method exists' : 'open method not found';";
                String result = (String) jsExecutor.executeScript(callMethodScript, modal);
                System.out.println("✓ Method check result: " + result);

                // Get component properties
                String componentIdScript = "return arguments[0].el ? arguments[0].el.id : 'no id';";
                String componentId = (String) jsExecutor.executeScript(componentIdScript, modal);
                System.out.println("✓ Component ID: " + componentId);
            }

        } catch (Exception e) {
            System.out.println("⚠ Modal not found in page");
        }
    }

    // Real-world example: Handling Ionic components
    static void handleIonicComponents() {
        System.out.println("\n--- Handling Ionic Components ---");
        try {
            // Ionic buttons
            WebElement ionButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.tagName("ion-button"))
            );
            System.out.println("✓ Found clickable Ionic button");

            // Ionic cards
            java.util.List<WebElement> ionCards = driver.findElements(By.tagName("ion-card"));
            System.out.println("✓ Found " + ionCards.size() + " Ionic cards");

            // Ionic select
            WebElement ionSelect = driver.findElement(By.tagName("ion-select"));
            if (ionSelect != null) {
                // Ionic select uses popover, need special handling
                String openSelectScript = "arguments[0].open();";
                jsExecutor.executeScript(openSelectScript, ionSelect);
                System.out.println("✓ Opened Ionic select via JavaScript");
            }

        } catch (Exception e) {
            System.out.println("✗ Error handling Ionic components: " + e.getMessage());
        }
    }

    // Real-world example: Handling Angular Material components
    static void handleAngularMaterialComponents() {
        System.out.println("\n--- Handling Angular Material Components ---");
        try {
            // Material buttons
            WebElement matButton = driver.findElement(By.tagName("button[mat-raised-button]"));
            if (matButton != null) {
                System.out.println("✓ Found Material raised button");
            }

            // Material form field (uses shadow DOM)
            WebElement matFormField = driver.findElement(By.tagName("mat-form-field"));
            if (matFormField != null) {
                SearchContext shadowRoot = matFormField.getShadowRoot();
                WebElement input = shadowRoot.findElement(By.tagName("input"));
                System.out.println("✓ Found input in Material form field");
            }

        } catch (NoSuchElementException e) {
            System.out.println("⚠ Material components not found on page");
        }
    }

    // Strategy: Working with any Web Component
    static void webComponentStrategy() {
        System.out.println("\n===== WEB COMPONENT AUTOMATION STRATEGY =====");
        System.out.println("1. IDENTIFICATION:");
        System.out.println("   - Look for custom tags with hyphens (e.g., my-component)");
        System.out.println("   - Check if element has Shadow DOM via getShadowRoot()");
        System.out.println("");
        System.out.println("2. INTERACTION:");
        System.out.println("   - Try direct Selenium methods first (click, sendKeys)");
        System.out.println("   - If not working, access shadow root and find internal elements");
        System.out.println("   - Use JavaScript to set properties and call methods");
        System.out.println("");
        System.out.println("3. VERIFICATION:");
        System.out.println("   - Check component properties via getAttribute()");
        System.out.println("   - Verify internal state via JavaScript");
        System.out.println("   - Listen for component-specific events");
        System.out.println("");
        System.out.println("4. COMMON PATTERNS:");
        System.out.println("   - Get value: element.value or shadowRoot.querySelector('input').value");
        System.out.println("   - Set value: element.value = 'new value'; element.dispatchEvent(new Event('input'));");
        System.out.println("   - Open/Close: element.open() / element.close()");
        System.out.println("   - Events: ionChange, ionSelect, change, etc.");
    }
}
