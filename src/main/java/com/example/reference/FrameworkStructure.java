package com.example.reference;

/**
 * COMPLETE SELENIUM JAVA FRAMEWORK - FILE STRUCTURE & QUICK NAVIGATION
 * 
 * ============================================================================
 * BEGINNER TO EXPERT LEVEL - ALL TOPICS COVERED
 * ============================================================================
 * 
 * Total Files Created: 25+ Java Classes
 * Topics Covered: 13 Major Categories
 * Interview Questions: 50+
 * 
 * ============================================================================
 */

public class FrameworkStructure {

    /*
     * ============================================================================
     * ACTIONS PACKAGE (9 Files)
     * ============================================================================
     * Package: com.example.actions
     * 
     * Files:
     * 1. ActionsBasicDemo.java
     *    - Basic setup of Actions class
     *    - Driver initialization
     *    - Actions object creation
     * 
     * 2. MouseHoverDemo.java
     *    - moveToElement() method
     *    - Hover interactions
     *    - Dropdown menu hovering
     * 
     * 3. RightClickDemo.java
     *    - contextClick() method
     *    - Context menu operations
     *    - Right-click at current location
     * 
     * 4. DoubleClickDemo.java
     *    - doubleClick() method
     *    - Element double-click handling
     *    - Double-click events
     * 
     * 5. DragAndDropDemo.java
     *    - dragAndDrop() method
     *    - Drag source to target
     *    - Simple drag-drop implementation
     * 
     * 6. ManualDragDropDemo.java
     *    - clickAndHold() + moveToElement() + release()
     *    - Advanced drag-drop for custom UI
     *    - Fallback when dragAndDrop() fails
     * 
     * 7. KeyboardCtrlADemo.java
     *    - CTRL + A (Select All)
     *    - keyDown() and keyUp() methods
     *    - Keyboard combination example
     * 
     * 8. KeyboardShortcutsReference.java
     *    - Utility class for keyboard shortcuts
     *    - Common combinations (CTRL, SHIFT, ALT)
     *    - Reusable shortcut methods
     * 
     * 9. ActionsCompleteDemo.java
     *    - Combined example with multiple actions
     *    - Integration of hover, click, type, keyboard
     *    - Real-world scenario demonstration
     * 
     * Key Interview Questions:
     * - What is Actions class used for?
     * - Difference between Actions and WebElement.click()
     * - What is perform() and build()?
     * - How to perform drag-and-drop if normal method fails?
     * - How to handle keyboard combinations?
     */

    /*
     * ============================================================================
     * WINDOW HANDLES PACKAGE (7 Files)
     * ============================================================================
     * Package: com.example.windowhandles
     * 
     * Files:
     * 1. WindowHandleBasicDemo.java
     *    - getWindowHandle() for current window
     *    - getWindowHandles() for all windows
     *    - Basic window operations
     * 
     * 2. ParentChildWindowDemo.java
     *    - Handle parent and child windows
     *    - Store parent handle
     *    - Switch between parent and child
     * 
     * 3. SwitchByTitleDemo.java
     *    - Identify windows by page title
     *    - getTitle() method
     *    - Search for specific window
     * 
     * 4. SwitchByUrlDemo.java
     *    - Identify windows by URL
     *    - getCurrentUrl() method
     *    - Switch to specific window by URL
     * 
     * 5. NewTabDemo.java
     *    - Selenium 4: Open new tab
     *    - WindowType.TAB
     *    - Programmatic tab opening
     * 
     * 6. NewWindowDemo.java
     *    - Selenium 4: Open new browser window
     *    - WindowType.WINDOW
     *    - New window opening
     * 
     * 7. CompleteWindowHandlingDemo.java
     *    - Complete real-world pattern
     *    - All window handling scenarios
     *    - Best practices example
     * 
     * Key Interview Questions:
     * - What is window handle?
     * - getWindowHandle() vs getWindowHandles()?
     * - How to switch windows?
     * - close() vs quit()?
     * - How to handle multiple windows?
     */

    /*
     * ============================================================================
     * WEB TABLES PACKAGE (8 Files)
     * ============================================================================
     * Package: com.example.webtables
     * 
     * Files:
     * 1. WebTableBasicDemo.java
     *    - Basic table operations
     *    - Count rows
     *    - Locate table element
     * 
     * 2. PrintTableDemo.java
     *    - Print complete table data
     *    - Loop through rows and cells
     *    - Extract all table content
     * 
     * 3. FindEmployeeDemo.java
     *    - Find specific value in table
     *    - Search through rows
     *    - Locate target record
     * 
     * 4. TableRowActionDemo.java
     *    - *** VERY IMPORTANT ***
     *    - Find row and click button in same row
     *    - Scope search to row
     *    - Common interview scenario
     * 
     * 5. TableColumnDemo.java
     *    - Access specific columns
     *    - Get cell by column index
     *    - Extract column data
     * 
     * 6. DynamicTableXPathDemo.java
     *    - Dynamic XPath approach
     *    - XPath for finding rows by value
     *    - Fast element location
     * 
     * 7. PaginationDemo.java
     *    - Handle table pagination
     *    - Search across pages
     *    - Navigate through pages
     * 
     * 8. InterviewScenariosDemo.java
     *    - Common interview scenarios
     *    - Find and edit patterns
     *    - Row verification
     * 
     * Key Interview Questions:
     * - How to find and click button in specific row?
     * - How to handle pagination?
     * - Why avoid row indexes?
     * - How to get data from specific column?
     */

    /*
     * ============================================================================
     * DROPDOWNS PACKAGE (3 Files)
     * ============================================================================
     * Package: com.example.dropdowns
     * 
     * Files:
     * 1. NativeSelectDropdownDemo.java
     *    - HTML <select> dropdowns
     *    - Select class usage
     *    - selectByVisibleText, selectByValue, selectByIndex
     *    - Get selected option and all options
     * 
     * 2. MultiSelectDropdownDemo.java
     *    - Multi-select dropdowns
     *    - Select multiple options
     *    - getAllSelectedOptions()
     *    - deselectByVisibleText() and deselectAll()
     * 
     * 3. CustomDropdownDemo.java
     *    - Bootstrap, Material UI, React/Angular dropdowns
     *    - Custom HTML structure (div, ul, li)
     *    - Click, find, click pattern
     *    - Generic custom dropdown handling
     * 
     * Key Interview Questions:
     * - How to handle native dropdowns?
     * - Difference between native and custom dropdowns?
     * - How to handle multi-select?
     * - How to select from custom dropdowns?
     */

    /*
     * ============================================================================
     * ALERTS PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.alerts
     * 
     * Files:
     * 1. AlertHandlingDemo.java
     *    - Simple alerts
     *    - Confirmation alerts (OK/Cancel)
     *    - Prompt alerts (Input + OK/Cancel)
     *    - accept(), dismiss(), getText(), sendKeys()
     *    - Check if alert present
     * 
     * Key Interview Questions:
     * - Types of JavaScript alerts?
     * - How to handle each type?
     * - How to check if alert exists?
     * - Accept vs Dismiss?
     */

    /*
     * ============================================================================
     * IFRAMES PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.iframes
     * 
     * Files:
     * 1. IFrameHandlingDemo.java
     *    - Switch by ID, name, index, WebElement
     *    - Find elements inside iFrame
     *    - Nested iFrames handling
     *    - parentFrame() vs defaultContent()
     *    - Find element in any iFrame
     * 
     * Key Interview Questions:
     * - How to interact with iFrame elements?
     * - How to handle nested iFrames?
     * - Switch methods?
     * - Common iFrame mistakes?
     */

    /*
     * ============================================================================
     * SHADOW DOM PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.shadowdom
     * 
     * Files:
     * 1. ShadowDOMHandlingDemo.java
     *    - Access shadow DOM elements
     *    - shadowRoot.querySelector() JavaScript
     *    - Nested shadow DOM navigation
     *    - Click, type in shadow elements
     *    - Find elements by text
     * 
     * Key Interview Questions:
     * - Why can't Selenium find shadow DOM?
     * - How to access shadow DOM?
     * - JavaScript approach?
     * - When to suspect shadow DOM?
     */

    /*
     * ============================================================================
     * JAVASCRIPT EXECUTOR PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.javascript
     * 
     * Files:
     * 1. JavaScriptExecutorDemo.java
     *    - Click, type, scroll using JavaScript
     *    - Get/set element properties
     *    - Highlight elements
     *    - Check visibility
     *    - Remove elements
     *    - Execute complex JavaScript
     *    - Get page information
     * 
     * Key Interview Questions:
     * - When to use JavaScript Executor?
     * - Disadvantages of JavaScript?
     * - User behavior vs automated?
     * - Common JavaScript operations?
     */

    /*
     * ============================================================================
     * EXCEL DATA PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.exceldata
     * 
     * Files:
     * 1. ExcelDataHandlingDemo.java
     *    - Read Excel files
     *    - Write Excel files
     *    - Read specific cells
     *    - Update cell values
     *    - Find row by search value
     *    - Get data as 2D array (data-driven testing)
     *    - Handle different cell types
     *    - Auto-size columns
     * 
     * Dependencies:
     *    - poi-X.X.X.jar
     *    - poi-ooxml-X.X.X.jar
     * 
     * Key Interview Questions:
     * - How to read Excel?
     * - How to write Excel?
     * - Data-driven testing approach?
     * - Different cell types?
     */

    /*
     * ============================================================================
     * PROPERTIES FILE PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.propertyfiles
     * 
     * Files:
     * 1. PropertiesFileDemo.java
     *    - Read properties files
     *    - Write properties files
     *    - Get property with default value
     *    - Update properties
     *    - Configuration wrapper class
     *    - Load all properties
     * 
     * Key Interview Questions:
     * - Why use properties files?
     * - How to read/write properties?
     * - Configuration wrapper pattern?
     * - Externalized config benefits?
     */

    /*
     * ============================================================================
     * ADVANCED WEBELEMENTS PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.advancedwebelements
     * 
     * Files:
     * 1. AdvancedWebElementsDemo.java
     *    - Hidden elements handling
     *    - Disabled elements handling
     *    - Stale elements and retry logic
     *    - Dynamic elements waiting
     *    - Get element properties
     *    - Check element state
     *    - Element location and size
     * 
     * Key Interview Questions:
     * - Handle StaleElementReferenceException?
     * - Hidden vs disabled elements?
     * - Element state checks?
     * - Dynamic element identification?
     */

    /*
     * ============================================================================
     * FILE HANDLING PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.filehandling
     * 
     * Files:
     * 1. FileUploadDownloadDemo.java
     *    - File upload using sendKeys()
     *    - Upload multiple files
     *    - Configure Chrome for downloads
     *    - Click and download verification
     *    - Wait for file download
     *    - Verify downloaded file
     *    - Check file content
     * 
     * Key Interview Questions:
     * - How to upload files?
     * - How to download files?
     * - Browser configuration?
     * - Download verification?
     */

    /*
     * ============================================================================
     * WAIT STRATEGIES PACKAGE (1 File)
     * ============================================================================
     * Package: com.example.waits
     * 
     * Files:
     * 1. WaitStrategiesDemo.java
     *    - Implicit wait
     *    - Explicit wait (WebDriverWait)
     *    - Fluent wait with custom polling
     *    - Common ExpectedConditions
     *    - Custom wait conditions
     *    - Wait for page load
     *    - Wait for AJAX completion
     * 
     * Key Interview Questions:
     * - Implicit vs explicit wait?
     * - Which is better and why?
     * - Common ExpectedConditions?
     * - Custom conditions?
     * - Wait timeout best practices?
     */

    /*
     * ============================================================================
     * REFERENCE PACKAGE (2 Files)
     * ============================================================================
     * Package: com.example.reference
     * 
     * Files:
     * 1. SeleniumCheatSheet.java
     *    - Quick reference for all syntax
     *    - All topics covered
     *    - Interview points
     *    - Best practices
     * 
     * 2. FrameworkStructure.java (This File)
     *    - Framework overview
     *    - File structure and navigation
     *    - Quick reference guide
     * 
     * ============================================================================
     * DOCUMENTATION FILES
     * ============================================================================
     * 
     * 1. README_SELENIUM_CONCEPTS.md
     *    - Comprehensive documentation
     *    - All 13 topics with details
     *    - 50+ interview questions with answers
     *    - Best practices guide
     *    - Real-world scenarios
     *    - Career path guidance
     *    - Quick checklist for all topics
     * 
     * ============================================================================
     */

    /**
     * QUICK START GUIDE
     * 
     * Step 1: Understand the Curriculum
     * - Start with README_SELENIUM_CONCEPTS.md
     * - Read through all 13 major topics
     * - Check the interview checklist
     * 
     * Step 2: Study Core Topics (Week 1-2)
     * - Actions class
     * - Window handles
     * - Web tables
     * - Basic waits
     * 
     * Step 3: Study Intermediate Topics (Week 3-4)
     * - Dropdowns (native and custom)
     * - Alerts
     * - iFrames
     * - File handling
     * 
     * Step 4: Study Advanced Topics (Week 5-6)
     * - Shadow DOM
     * - JavaScript Executor
     * - Excel data
     * - Properties configuration
     * 
     * Step 5: Integration & Practice (Week 7-8)
     * - Advanced web elements
     * - Complete wait strategies
     * - Real-world scenarios
     * - Mock interviews
     * 
     * Step 6: Expert Level (Ongoing)
     * - Framework design
     * - Page Object Model
     * - Data-driven testing
     * - Performance optimization
     */

    /**
     * RECOMMENDED LEARNING ORDER
     * 
     * 1. Read this file (FrameworkStructure.java)
     * 2. Read README_SELENIUM_CONCEPTS.md
     * 3. Study SeleniumCheatSheet.java for syntax
     * 4. For each topic:
     *    a. Read the topic description in README
     *    b. Review the interview questions
     *    c. Study the Java code examples
     *    d. Try modifying examples
     *    e. Practice similar scenarios
     */

    /**
     * HOW TO USE THIS FRAMEWORK
     * 
     * Option 1: Learning
     * - Read Java files to understand concepts
     * - Study examples and modify them
     * - Use as reference during learning
     * 
     * Option 2: Reference
     * - Search for topic you need
     * - Find relevant Java file
     * - Copy-paste and customize
     * - Use SeleniumCheatSheet for quick syntax
     * 
     * Option 3: Interview Preparation
     * - Go through interview checklist
     * - Study each topic's interview questions
     * - Review real-world scenarios
     * - Practice with Java examples
     */

    /**
     * KEY CONCEPTS AT A GLANCE
     */
    public static void conceptSummary() {
        System.out.println("=== SELENIUM JAVA FRAMEWORK SUMMARY ===\n");

        System.out.println("Actions Class:");
        System.out.println("  - Use for complex mouse/keyboard interactions");
        System.out.println("  - Methods: moveToElement, click, rightClick, doubleClick, drag");
        System.out.println("  - Keyboard: keyDown, keyUp, sendKeys for combinations\n");

        System.out.println("Window Handles:");
        System.out.println("  - Store parent handle before child opens");
        System.out.println("  - Use for loops to find child by title/URL");
        System.out.println("  - Always switch back to parent after close\n");

        System.out.println("Web Tables:");
        System.out.println("  - Scope searches: row.findElements not driver.findElements");
        System.out.println("  - Identify rows by data, not position");
        System.out.println("  - Use dynamic XPath for simple operations\n");

        System.out.println("Dropdowns:");
        System.out.println("  - Native: Use Select class (selectByText/Value/Index)");
        System.out.println("  - Custom: Click, find, click pattern\n");

        System.out.println("Alerts:");
        System.out.println("  - driver.switchTo().alert()");
        System.out.println("  - accept(), dismiss(), getText(), sendKeys()\n");

        System.out.println("iFrames:");
        System.out.println("  - driver.switchTo().frame(id/name/index/element)");
        System.out.println("  - Always switch to defaultContent() when done\n");

        System.out.println("Shadow DOM:");
        System.out.println("  - Use JavaScript: shadowRoot.querySelector()");
        System.out.println("  - Normal Selenium locators don't work\n");

        System.out.println("JavaScript Executor:");
        System.out.println("  - Last resort for clicking/typing");
        System.out.println("  - Use for accessing properties and DOM\n");

        System.out.println("Excel Data:");
        System.out.println("  - Use Apache POI library");
        System.out.println("  - Workbook, Sheet, Row, Cell classes\n");

        System.out.println("Properties Files:");
        System.out.println("  - Key=value configuration format");
        System.out.println("  - Externalize configuration from code\n");

        System.out.println("Advanced WebElements:");
        System.out.println("  - Handle hidden/disabled/stale elements");
        System.out.println("  - Use retry logic for stale elements\n");

        System.out.println("File Handling:");
        System.out.println("  - Upload: sendKeys(file_path) on file input");
        System.out.println("  - Download: Configure Chrome options, wait for file\n");

        System.out.println("Wait Strategies:");
        System.out.println("  - Use Explicit (WebDriverWait) not Implicit");
        System.out.println("  - Common: presenceOf, visibilityOf, elementToBeClickable\n");
    }

    public static void main(String[] args) {
        conceptSummary();
    }
}
