package com.example.advancedwebelements;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

/**
 * ADVANCED & PRACTICAL EXAMPLES
 * 
 * Real-world scenarios and practical solutions for complex web element handling
 * Combines multiple concepts for production-ready automation
 */
public class AdvancedPracticalExamplesDemo {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;
    static JavascriptExecutor jsExecutor;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

        try {
            System.out.println("===== ADVANCED & PRACTICAL EXAMPLES =====");
            
            handleDynamicTable();
            handleInfiniteScroll();
            handleModalDialogs();
            handleComplexDropdown();
            handleFileOperations();
            handleJavaScriptFramework();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // ===== EXAMPLE 1: DYNAMIC TABLE WITH SORTING & FILTERING =====
    static void handleDynamicTable() {
        System.out.println("\n--- Advanced: Dynamic Table Handling ---");
        try {
            driver.navigate().to("https://demoqa.com/webtables");
            
            // Wait for table to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ReactTable")));
            
            // Get all rows
            List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row']"));
            System.out.println("✓ Found " + rows.size() + " rows in table");

            // Find specific row by text
            String targetName = "John Doe";
            WebElement targetRow = driver.findElement(
                By.xpath("//div[@role='row' and contains(., '" + targetName + "')]")
            );
            System.out.println("✓ Found row with: " + targetName);

            // Get specific cell value
            String cellValue = targetRow.findElement(
                By.xpath(".//div[@role='gridcell'][2]")
            ).getText();
            System.out.println("✓ Cell value: " + cellValue);

            // Click edit button in row
            WebElement editButton = targetRow.findElement(By.xpath(".//button[contains(text(), 'Edit')]"));
            editButton.click();
            System.out.println("✓ Clicked edit button");

            // Sort table by clicking header
            WebElement sortHeader = driver.findElement(
                By.xpath("//div[@role='columnheader' and contains(text(), 'First Name')]")
            );
            sortHeader.click();
            System.out.println("✓ Sorted table");

            // Filter table
            WebElement filterInput = driver.findElement(By.id("searchBox"));
            filterInput.clear();
            filterInput.sendKeys("John");
            System.out.println("✓ Applied filter");

            // Wait for filtered results
            Thread.sleep(1000);
            List<WebElement> filteredRows = driver.findElements(By.xpath("//div[@role='row']"));
            System.out.println("✓ Filtered rows: " + filteredRows.size());

        } catch (Exception e) {
            System.out.println("✗ Dynamic table error: " + e.getMessage());
        }
    }

    // ===== EXAMPLE 2: INFINITE SCROLL HANDLING =====
    static void handleInfiniteScroll() {
        System.out.println("\n--- Advanced: Infinite Scroll ---");
        try {
            driver.navigate().to("https://demoqa.com/text-box");
            
            // Scroll to load more content
            int previousHeight = 0;
            int attempts = 0;
            int maxAttempts = 5;

            while (attempts < maxAttempts) {
                // Get current page height
                long currentHeight = (Long) jsExecutor.executeScript("return document.body.scrollHeight");
                
                if (currentHeight == previousHeight) {
                    System.out.println("✓ Reached end of scrollable content");
                    break;
                }

                // Scroll to bottom
                jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                System.out.println("✓ Scrolled down (attempt " + (attempts + 1) + ")");

                // Wait for new content to load
                Thread.sleep(2000);

                previousHeight = (int) (long) currentHeight;
                attempts++;
            }

            // Get all loaded items
            List<WebElement> items = driver.findElements(By.className("item"));
            System.out.println("✓ Total items loaded: " + items.size());

        } catch (Exception e) {
            System.out.println("✗ Infinite scroll error: " + e.getMessage());
        }
    }

    // ===== EXAMPLE 3: MODAL DIALOGS =====
    static void handleModalDialogs() {
        System.out.println("\n--- Advanced: Modal Dialog Handling ---");
        try {
            driver.navigate().to("https://demoqa.com/modal-dialogs");
            
            // Click button to open modal
            WebElement openButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Open')]"))
            );
            openButton.click();
            System.out.println("✓ Clicked to open modal");

            // Wait for modal to appear
            WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("modal"))
            );
            System.out.println("✓ Modal appeared");

            // Get modal text
            WebElement modalBody = modal.findElement(By.className("modal-body"));
            String modalText = modalBody.getText();
            System.out.println("✓ Modal text: " + modalText);

            // Fill form in modal
            List<WebElement> inputs = modal.findElements(By.tagName("input"));
            inputs.get(0).sendKeys("Test Data");
            System.out.println("✓ Filled modal input");

            // Click modal button
            WebElement confirmButton = modal.findElement(
                By.xpath(".//button[contains(text(), 'Ok')]")
            );
            confirmButton.click();
            System.out.println("✓ Clicked modal button");

            // Wait for modal to close
            wait.until(ExpectedConditions.invisibilityOfElement(modal));
            System.out.println("✓ Modal closed");

        } catch (Exception e) {
            System.out.println("✗ Modal dialog error: " + e.getMessage());
        }
    }

    // ===== EXAMPLE 4: COMPLEX DROPDOWN WITH SEARCH =====
    static void handleComplexDropdown() {
        System.out.println("\n--- Advanced: Complex Dropdown with Search ---");
        try {
            driver.navigate().to("https://demoqa.com/select-menu");
            
            // Click dropdown to open
            WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Select']"))
            );
            dropdown.click();
            System.out.println("✓ Opened dropdown");

            // Type in search field (if available)
            WebElement searchField = dropdown.findElement(By.xpath(".//input[@placeholder='Search']"));
            searchField.sendKeys("Option 1");
            System.out.println("✓ Typed in dropdown search");

            // Wait for filtered options
            Thread.sleep(1000);

            // Get all visible options
            List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
            System.out.println("✓ Found " + options.size() + " options");

            // Click specific option
            if (!options.isEmpty()) {
                options.get(0).click();
                System.out.println("✓ Selected option");
            }

            // Verify selection
            String selectedValue = dropdown.getText();
            System.out.println("✓ Selected value: " + selectedValue);

        } catch (Exception e) {
            System.out.println("✗ Complex dropdown error: " + e.getMessage());
        }
    }

    // ===== EXAMPLE 5: FILE UPLOAD & DOWNLOAD =====
    static void handleFileOperations() {
        System.out.println("\n--- Advanced: File Upload ---");
        try {
            driver.navigate().to("https://demoqa.com/upload-download");
            
            // Find file input
            WebElement fileInput = driver.findElement(By.id("uploadFile"));
            
            // Upload file (provide full file path)
            String filePath = "C:\\Users\\varsh\\OneDrive\\Desktop\\test-file.txt";
            fileInput.sendKeys(filePath);
            System.out.println("✓ File uploaded: " + filePath);

            // Verify upload success
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadedFileName")));
            String uploadedFileName = driver.findElement(By.id("uploadedFileName")).getText();
            System.out.println("✓ Upload confirmed: " + uploadedFileName);

            // Download file
            WebElement downloadButton = driver.findElement(By.id("downloadButton"));
            downloadButton.click();
            System.out.println("✓ Clicked download button");
            
            // Note: Actual download handling requires browser configuration
            // See ChromeOptions for download directory configuration

        } catch (Exception e) {
            System.out.println("✗ File operations error: " + e.getMessage());
        }
    }

    // ===== EXAMPLE 6: JAVASCRIPT FRAMEWORK SPECIFIC (Angular, React) =====
    static void handleJavaScriptFramework() {
        System.out.println("\n--- Advanced: JavaScript Framework Handling ---");
        try {
            // Check for Angular
            String angularCheck = (String) jsExecutor.executeScript(
                "return (typeof angular !== 'undefined') ? 'Angular Detected' : 'No Angular';"
            );
            System.out.println("✓ " + angularCheck);

            // Check for React
            String reactCheck = (String) jsExecutor.executeScript(
                "return (typeof React !== 'undefined') ? 'React Detected' : 'No React';"
            );
            System.out.println("✓ " + reactCheck);

            // Wait for Angular to stabilize (if Angular app)
            jsExecutor.executeScript(
                "return angular.element(document.body).injector().get('$http').pendingRequests.length === 0;"
            );
            System.out.println("✓ Waited for Angular requests");

            // Wait for jQuery AJAX (if jQuery is used)
            jsExecutor.executeScript("return jQuery.active == 0;");
            System.out.println("✓ Waited for jQuery AJAX");

            // Get component state (React DevTools)
            Object componentState = jsExecutor.executeScript(
                "return window.__REACT_DEVTOOLS_GLOBAL_HOOK__;"
            );
            System.out.println("✓ React DevTools available: " + (componentState != null));

        } catch (Exception e) {
            System.out.println("⚠ Framework check: " + e.getMessage());
        }
    }

    // ===== HELPER: HANDLE OVERLAY =====
    static void handleOverlayBlocking() {
        System.out.println("\n--- Helper: Remove Overlay Blocking ---");
        try {
            // Find overlay
            WebElement overlay = driver.findElement(By.className("overlay"));
            
            // Method 1: Remove via JavaScript
            jsExecutor.executeScript("arguments[0].remove();", overlay);
            System.out.println("✓ Removed overlay");

            // Method 2: Wait for overlay to disappear
            wait.until(ExpectedConditions.invisibilityOf(overlay));
            System.out.println("✓ Overlay disappeared");

        } catch (Exception e) {
            System.out.println("⚠ No overlay found");
        }
    }

    // ===== HELPER: RETRY LOGIC =====
    static <T> T retryOperation(int maxAttempts, java.util.function.Supplier<T> operation) {
        System.out.println("\n--- Helper: Retry with Backoff ---");
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                T result = operation.get();
                System.out.println("✓ Operation succeeded on attempt " + attempt);
                return result;
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw new RuntimeException("Operation failed after " + maxAttempts + " attempts");
                }
                // Exponential backoff
                long delay = (long) Math.pow(2, attempt - 1) * 1000;
                System.out.println("⚠ Attempt " + attempt + " failed, retrying in " + delay + "ms");
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null;
    }

    // ===== HELPER: WAIT FOR ELEMENT WITH CONDITION =====
    static void waitForElementWithCondition() {
        System.out.println("\n--- Helper: Custom Wait Condition ---");
        try {
            wait.until(driver -> {
                WebElement element = driver.findElement(By.id("dynamicElement"));
                String text = element.getText();
                return text.contains("Expected Text");
            });
            System.out.println("✓ Element condition met");
        } catch (Exception e) {
            System.out.println("✗ Wait condition failed: " + e.getMessage());
        }
    }

    // ===== BEST PRACTICES SUMMARY =====
    static void showBestPractices() {
        System.out.println("\n===== ADVANCED BEST PRACTICES =====");
        System.out.println("1. DYNAMIC ELEMENTS:");
        System.out.println("   - Use explicit waits for all dynamic elements");
        System.out.println("   - Re-find elements after DOM updates");
        System.out.println("   - Handle stale element references");
        System.out.println("");
        System.out.println("2. COMPLEX INTERACTIONS:");
        System.out.println("   - Use Actions for multi-step operations");
        System.out.println("   - Combine methods: click → wait → find → click");
        System.out.println("   - Log each step for debugging");
        System.out.println("");
        System.out.println("3. ERROR HANDLING:");
        System.out.println("   - Implement retry logic for flaky operations");
        System.out.println("   - Catch specific exceptions (Stale, Timeout, NoSuch)");
        System.out.println("   - Log detailed error information");
        System.out.println("");
        System.out.println("4. PERFORMANCE:");
        System.out.println("   - Use CSS selectors instead of XPath");
        System.out.println("   - Disable images in headless mode");
        System.out.println("   - Implement parallel test execution");
        System.out.println("");
        System.out.println("5. MAINTAINABILITY:");
        System.out.println("   - Use Page Object Model pattern");
        System.out.println("   - Keep locators in constants");
        System.out.println("   - Create reusable helper methods");
        System.out.println("   - Document complex scenarios");
    }
}
