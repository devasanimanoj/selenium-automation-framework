package com.example.advancedwebelements;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

/**
 * Web Element #24-25: Canvas Elements & Canvas Charts
 * 
 * Learning Objectives:
 * - Understand Canvas element limitations in Selenium
 * - Canvas is a drawing surface - doesn't have DOM elements
 * - Techniques: JavaScript execution, pixel analysis, coordinate-based interaction
 * - When canvas cannot be automated directly, use alternative approaches
 * 
 * Key Concepts:
 * - Canvas is NOT a DOM element, it's a drawing surface
 * - Cannot locate elements inside canvas using XPath/CSS selectors
 * - Cannot directly get text or check states
 * - Must use JavaScript to access canvas data or simulate interactions
 * - Charts built on Canvas (Chart.js, D3.js) require special handling
 * 
 * Limitations:
 * - Cannot access individual shapes/elements within canvas
 * - Cannot read rendered text
 * - Must work with coordinates
 * - Often requires JavaScript or OCR techniques
 */
public class CanvasElementsDemo {
    static WebDriver driver;
    static JavascriptExecutor jsExecutor;
    static WebDriverWait wait;
    static Actions actions;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        try {
            // Example with Chart.js
            driver.navigate().to("https://www.chartjs.org/docs/latest/");

            System.out.println("===== CANVAS ELEMENTS HANDLING =====");
            handleCanvasElements();

        } finally {
            driver.quit();
        }
    }

    static void handleCanvasElements() {
        try {
            // Method 1: Find Canvas element
            System.out.println("\n--- Method 1: Locate Canvas Element ---");
            locateCanvasElement();

            // Method 2: Verify Canvas existence and properties
            System.out.println("\n--- Method 2: Canvas Properties ---");
            getCanvasProperties();

            // Method 3: Simulate interaction with canvas
            System.out.println("\n--- Method 3: Interact with Canvas ---");
            interactWithCanvas();

            // Method 4: Extract data from canvas
            System.out.println("\n--- Method 4: Extract Canvas Data ---");
            extractCanvasData();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method 1: Locate canvas element
    static void locateCanvasElement() {
        try {
            // Find all canvas elements
            java.util.List<WebElement> canvasElements = driver.findElements(By.tagName("canvas"));
            System.out.println("✓ Found " + canvasElements.size() + " canvas elements");

            if (!canvasElements.isEmpty()) {
                WebElement canvas = canvasElements.get(0);
                
                // Get canvas properties
                String id = canvas.getAttribute("id");
                String width = canvas.getAttribute("width");
                String height = canvas.getAttribute("height");
                String className = canvas.getAttribute("class");
                
                System.out.println("✓ Canvas ID: " + id);
                System.out.println("✓ Canvas Width: " + width);
                System.out.println("✓ Canvas Height: " + height);
                System.out.println("✓ Canvas Class: " + className);
            }

        } catch (Exception e) {
            System.out.println("✗ Error locating canvas: " + e.getMessage());
        }
    }

    // Method 2: Get canvas properties using JavaScript
    static void getCanvasProperties() {
        try {
            // Get canvas context (important for interactive canvas)
            String script = "return !!document.querySelector('canvas').getContext('2d');";
            Boolean hasContext = (Boolean) jsExecutor.executeScript(script);
            System.out.println("✓ Canvas has 2D context: " + hasContext);

            // Get canvas dimensions via JavaScript
            String dimensionScript = "var canvas = document.querySelector('canvas'); return {width: canvas.width, height: canvas.height};";
            java.util.Map<String, Object> dimensions = 
                (java.util.Map<String, Object>) jsExecutor.executeScript(dimensionScript);
            System.out.println("✓ Canvas dimensions (JS): " + dimensions);

            // Check if canvas is visible
            String visibilityScript = "var canvas = document.querySelector('canvas'); return window.getComputedStyle(canvas).display !== 'none';";
            Boolean isVisible = (Boolean) jsExecutor.executeScript(visibilityScript);
            System.out.println("✓ Canvas is visible: " + isVisible);

        } catch (Exception e) {
            System.out.println("✗ Error getting properties: " + e.getMessage());
        }
    }

    // Method 3: Interact with canvas (coordinate-based)
    static void interactWithCanvas() {
        try {
            WebElement canvas = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.tagName("canvas"))
            );

            // Get canvas position
            Point location = canvas.getLocation();
            Dimension size = canvas.getSize();
            
            System.out.println("✓ Canvas location: " + location);
            System.out.println("✓ Canvas size: " + size);

            // Calculate center of canvas
            int centerX = location.getX() + (size.getWidth() / 2);
            int centerY = location.getY() + (size.getHeight() / 2);
            
            System.out.println("✓ Canvas center: (" + centerX + ", " + centerY + ")");

            // Simulate click on canvas center
            actions.moveToElement(canvas, size.getWidth() / 2, size.getHeight() / 2)
                   .click()
                   .perform();
            System.out.println("✓ Clicked canvas center");

            // Simulate drag on canvas
            actions.moveToElement(canvas, 50, 50)
                   .clickAndHold()
                   .moveByOffset(100, 100)
                   .release()
                   .perform();
            System.out.println("✓ Performed drag operation on canvas");

        } catch (Exception e) {
            System.out.println("✗ Error interacting with canvas: " + e.getMessage());
        }
    }

    // Method 4: Extract data from canvas
    static void extractCanvasData() {
        try {
            // Method 4A: Get canvas image data (advanced)
            String imageDataScript = 
                "var canvas = document.querySelector('canvas');" +
                "var ctx = canvas.getContext('2d');" +
                "var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);" +
                "return imageData.data.length;";
            
            Long pixelDataLength = (Long) jsExecutor.executeScript(imageDataScript);
            System.out.println("✓ Canvas pixel data length: " + pixelDataLength);

            // Method 4B: Check if canvas is drawn (has non-zero pixels)
            String hasDrawingScript = 
                "var canvas = document.querySelector('canvas');" +
                "var ctx = canvas.getContext('2d');" +
                "var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);" +
                "var data = imageData.data;" +
                "for (var i = 3; i < data.length; i += 4) {" +
                "  if (data[i] > 0) return true;" +
                "}" +
                "return false;";
            
            Boolean isDrawn = (Boolean) jsExecutor.executeScript(hasDrawingScript);
            System.out.println("✓ Canvas has drawing content: " + isDrawn);

            // Method 4C: Convert canvas to image (for screenshot)
            String toImageScript = "return document.querySelector('canvas').toDataURL('image/png');";
            String imageData = (String) jsExecutor.executeScript(toImageScript);
            if (imageData != null && imageData.length() > 50) {
                System.out.println("✓ Canvas converted to image data (length: " + imageData.length() + ")");
            }

        } catch (Exception e) {
            System.out.println("✗ Error extracting canvas data: " + e.getMessage());
        }
    }

    // Real-world example: Handling Chart.js chart
    static void handleChartJsChart() {
        try {
            System.out.println("\n--- Handling Chart.js ---");
            
            // Chart.js creates canvas element with Chart.js instance
            // Access chart data through JavaScript
            String chartDataScript = 
                "var canvas = document.querySelector('canvas');" +
                "if (canvas && canvas.chart) {" +
                "  return {" +
                "    type: canvas.chart.config.type," +
                "    labels: canvas.chart.data.labels," +
                "    datasetCount: canvas.chart.data.datasets.length" +
                "  };" +
                "}" +
                "return null;";
            
            java.util.Map<String, Object> chartData = 
                (java.util.Map<String, Object>) jsExecutor.executeScript(chartDataScript);
            
            if (chartData != null) {
                System.out.println("✓ Chart Type: " + chartData.get("type"));
                System.out.println("✓ Chart Labels: " + chartData.get("labels"));
                System.out.println("✓ Dataset Count: " + chartData.get("datasetCount"));
            }

        } catch (Exception e) {
            System.out.println("✗ Error handling Chart.js: " + e.getMessage());
        }
    }

    // Alternative approach: Use OCR for canvas content (if available)
    static void canvasOCRApproach() {
        System.out.println("\n--- OCR Approach for Canvas ---");
        System.out.println("For canvas with text content, consider:");
        System.out.println("1. Use Tesseract OCR library");
        System.out.println("2. Take screenshot of canvas element");
        System.out.println("3. Process image with OCR");
        System.out.println("4. Verify text content");
        // Implementation would require Tesseract dependency
    }

    // Best practices for canvas automation
    static void bestPractices() {
        System.out.println("\n===== CANVAS AUTOMATION BEST PRACTICES =====");
        System.out.println("1. Check if canvas is visible before interacting");
        System.out.println("2. Use JavaScript to extract data when possible");
        System.out.println("3. Use coordinate-based interactions for drawing");
        System.out.println("4. Verify canvas has been rendered before testing");
        System.out.println("5. Consider alternative selectors if canvas is used for display only");
        System.out.println("6. For charts, access chart library API if available");
        System.out.println("7. Use screenshot comparison for visual verification");
        System.out.println("8. Document canvas limitations in your tests");
    }
}
