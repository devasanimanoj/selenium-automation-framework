package com.example.reference;

/**
 * SELENIUM JAVA CHEAT SHEET - ACTIONS, WINDOWS & TABLES
 * 
 * Quick reference for the most important Selenium concepts
 */

public class SeleniumCheatSheet {

    /*
     * ============================================================
     * SET 3 & 9: ACTIONS CLASS
     * ============================================================
     * 
     * Import:
     * import org.openqa.selenium.interactions.Actions;
     * import org.openqa.selenium.Keys;
     * 
     * Create Actions:
     * Actions actions = new Actions(driver);
     * 
     * MOUSE OPERATIONS:
     * 
     * 1. Mouse Hover
     *    actions.moveToElement(element).perform();
     * 
     * 2. Right Click (Context Click)
     *    actions.contextClick(element).perform();
     * 
     * 3. Double Click
     *    actions.doubleClick(element).perform();
     * 
     * 4. Click using Actions
     *    actions.click(element).perform();
     * 
     * 5. Click and Hold
     *    actions.clickAndHold(element).perform();
     * 
     * 6. Release Mouse Button
     *    actions.release().perform();
     * 
     * 7. Drag and Drop
     *    actions.dragAndDrop(source, target).perform();
     * 
     * 8. Manual Drag and Drop (if dragAndDrop fails)
     *    actions.clickAndHold(source)
     *           .moveToElement(target)
     *           .release()
     *           .perform();
     * 
     * 9. Drag by Offset
     *    actions.dragAndDropBy(element, 100, 0).perform();
     * 
     * KEYBOARD OPERATIONS:
     * 
     * 1. Send Keys
     *    actions.sendKeys("Hello").perform();
     * 
     * 2. Special Keys
     *    actions.sendKeys(Keys.ENTER).perform();
     *    Keys: TAB, ESCAPE, BACKSPACE, DELETE, ARROW_UP, ARROW_DOWN, 
     *          ARROW_LEFT, ARROW_RIGHT, HOME, END, PAGE_UP, PAGE_DOWN
     * 
     * 3. CTRL + A (Select All)
     *    actions.keyDown(Keys.CONTROL)
     *           .sendKeys("a")
     *           .keyUp(Keys.CONTROL)
     *           .perform();
     * 
     * 4. CTRL + C (Copy)
     *    actions.keyDown(Keys.CONTROL)
     *           .sendKeys("c")
     *           .keyUp(Keys.CONTROL)
     *           .perform();
     * 
     * 5. CTRL + V (Paste)
     *    actions.keyDown(Keys.CONTROL)
     *           .sendKeys("v")
     *           .keyUp(Keys.CONTROL)
     *           .perform();
     * 
     * 6. SHIFT + Key
     *    actions.keyDown(Keys.SHIFT)
     *           .sendKeys("a")
     *           .keyUp(Keys.SHIFT)
     *           .perform();
     * 
     * CHAINING:
     * 
     * 1. Chain Multiple Actions
     *    actions.moveToElement(menu)
     *           .click()
     *           .moveToElement(subMenu)
     *           .click()
     *           .perform();
     * 
     * BUILD VS PERFORM:
     * 
     * 1. build() - Creates action sequence
     *    Action action = actions.moveToElement(element)
     *                           .click()
     *                           .build();
     * 
     * 2. perform() - Executes the action sequence
     *    action.perform();
     *    OR
     *    actions.moveToElement(element).click().perform();
     * 
     * INTERVIEW POINTS:
     * - perform() executes the action sequence
     * - build() creates the sequence (rarely used separately)
     * - Use Actions for complex interactions (hover, right-click, drag)
     * - Use WebElement.click() for simple clicks
     */

    /*
     * ============================================================
     * SET 8: WINDOW HANDLES & TABS
     * ============================================================
     * 
     * Import:
     * import java.util.Set;
     * import org.openqa.selenium.WindowType;
     * 
     * BASIC OPERATIONS:
     * 
     * 1. Get current window handle
     *    String currentWindow = driver.getWindowHandle();
     *    Return type: String
     * 
     * 2. Get all window handles
     *    Set<String> allWindows = driver.getWindowHandles();
     *    Return type: Set<String>
     * 
     * 3. Switch to a window
     *    driver.switchTo().window(windowHandle);
     * 
     * PARENT-CHILD WINDOW PATTERN:
     * 
     * String parentWindow = driver.getWindowHandle();
     * button.click();  // Opens new window
     * 
     * Set<String> allWindows = driver.getWindowHandles();
     * for (String window : allWindows) {
     *     if (!window.equals(parentWindow)) {
     *         driver.switchTo().window(window);
     *         break;
     *     }
     * }
     * 
     * IDENTIFY WINDOW BY:
     * 
     * 1. By Title
     *    for (String window : driver.getWindowHandles()) {
     *        driver.switchTo().window(window);
     *        if (driver.getTitle().equals("Payment Page")) break;
     *    }
     * 
     * 2. By URL
     *    for (String window : driver.getWindowHandles()) {
     *        driver.switchTo().window(window);
     *        if (driver.getCurrentUrl().contains("payment")) break;
     *    }
     * 
     * SELENIUM 4 - OPEN NEW WINDOWS:
     * 
     * 1. Open new tab
     *    driver.switchTo().newWindow(WindowType.TAB);
     * 
     * 2. Open new browser window
     *    driver.switchTo().newWindow(WindowType.WINDOW);
     * 
     * CLOSE WINDOWS:
     * 
     * 1. Close current window/tab
     *    driver.close();
     * 
     * 2. Close entire session
     *    driver.quit();
     * 
     * IMPORTANT PATTERN:
     * 
     * driver.close();  // Close child
     * driver.switchTo().window(parentWindow);  // Switch back to parent
     * 
     * INTERVIEW POINTS:
     * - close() closes current window, quit() closes all
     * - getWindowHandle() returns String
     * - getWindowHandles() returns Set<String>
     * - Identify windows by title/URL, not order
     * - Always switch back to parent after closing child
     */

    /*
     * ============================================================
     * SET 10: WEB TABLES
     * ============================================================
     * 
     * BASIC OPERATIONS:
     * 
     * 1. Locate table
     *    WebElement table = driver.findElement(By.id("employeeTable"));
     * 
     * 2. Get all rows
     *    List<WebElement> rows = table.findElements(By.tagName("tr"));
     * 
     * 3. Get cells in a row
     *    List<WebElement> cells = row.findElements(By.tagName("td"));
     * 
     * 4. Get cell text
     *    String value = cells.get(1).getText();  // Index 1 = 2nd column
     * 
     * IMPORTANT: Why scope to row?
     * ✓ row.findElements(By.tagName("td"))  - Good
     * ✗ driver.findElements(By.tagName("td")) - Bad
     * 
     * FIND ROW AND PERFORM ACTION:
     * 
     * for (WebElement row : rows) {
     *     if (row.getText().contains("Manoj")) {
     *         WebElement button = row.findElement(By.tagName("button"));
     *         button.click();
     *         break;
     *     }
     * }
     * 
     * DYNAMIC XPATH APPROACH:
     * 
     * 1. Find row by text and click button
     *    driver.findElement(By.xpath("//tr[td[text()='Manoj']]//button"))
     *           .click();
     * 
     * 2. Find specific button in row
     *    driver.findElement(By.xpath("//tr[td[text()='Manoj']]//button[text()='Edit']"))
     *           .click();
     * 
     * 3. Multiple conditions
     *    //tr[td[text()='Manoj'] and td[text()='Testing']]//button
     * 
     * 4. Find link
     *    //tr[td[text()='Manoj']]//a
     * 
     * 5. Find checkbox
     *    //tr[td[text()='Manoj']]//input[@type='checkbox']
     * 
     * PAGINATION PATTERN:
     * 
     * while (!found) {
     *     // Search current page
     *     if (findElement exists) {
     *         found = true;
     *         performAction();
     *     } else {
     *         // Click Next
     *         WebElement next = driver.findElement(By.id("next"));
     *         if (!next.isEnabled()) break;  // No more pages
     *         next.click();
     *     }
     * }
     * 
     * GET COLUMN BY INDEX:
     * 
     * List<WebElement> cells = row.findElements(By.tagName("td"));
     * String id = cells.get(0).getText();          // Column 0
     * String name = cells.get(1).getText();        // Column 1
     * String department = cells.get(2).getText();  // Column 2
     * 
     * INTERVIEW POINTS:
     * - Use row-scoped searches, not page-wide
     * - Avoid hard-coded row indexes (they change)
     * - Identify rows by data (name, ID), not position
     * - For pagination, loop until found or no next button
     * - XPath is faster for simple operations
     * - Looping is more flexible for complex logic
     */
}
