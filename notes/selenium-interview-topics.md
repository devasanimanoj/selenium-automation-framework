# Selenium Interview Topics

This file explains the most important Selenium interview topics in a practical way. For every topic, focus on three things:

- what it is
- when you use it
- why it matters in real-world automation

## 1) Selenium fundamentals

### What it is
Selenium WebDriver is a browser automation library used to automate web applications.

### When to use it
Use it when you need to test real user interactions such as login, search, checkout, form validation, and navigation.

### Why it matters
It simulates user behavior on a real browser and helps validate end-to-end functionality.

### Important points
- `WebDriver` is the main interface used for browser automation.
- Each browser has its own driver, such as ChromeDriver, GeckoDriver, or EdgeDriver.
- Always close the browser session using `driver.quit()` at the end.

## 2) Locators

### What it is
Locators are the ways Selenium identifies elements on the page.

### Preferred order
1. id
2. name
3. cssSelector
4. xpath
5. linkText / partialLinkText
6. className / tagName

### Why this order matters
ID and CSS selectors are generally more stable and faster than XPath in many cases.

### Best practices
- Prefer stable locators like `id`, `data-testid`, and `name`
- Avoid brittle XPath chains when CSS is simpler
- Use `contains()` and `starts-with()` only when needed
- Use text-based locators only when the text is stable

## 3) Wait strategies

### What it is
Waits help the script handle slow applications and dynamic DOM changes.

### Implicit wait
- Applies globally to all element lookups
- Useful for basic waits but not for precise conditions
- Can make tests slower and less deterministic

### Explicit wait
- Best practice for most Selenium tests
- Waits until a specific condition is true
- Example: element visible, clickable, or present in the DOM

### Fluent wait
- Lets you define polling frequency and exceptions to ignore
- Good for dynamic and custom UI behavior

### Why use explicit waits instead of `Thread.sleep()`?
Because `Thread.sleep()` pauses the code blindly. Explicit wait waits for a real condition, which makes tests faster and more reliable.

## 4) Alerts, frames, and windows

### Alerts
Use `switchTo().alert()` to interact with browser popups.

Common methods:
- `accept()`
- `dismiss()`
- `getText()`
- `sendKeys()`

### Frames
Use `switchTo().frame()` when the page contains an iframe.

Always return to the main page with:
- `driver.switchTo().defaultContent()`

### Windows and tabs
Use window handles when opening multiple tabs/windows.

Common methods:
- `getWindowHandle()`
- `getWindowHandles()`
- `switchTo().window(handle)`
- `close()` vs `quit()`

### Why it matters
These are common in login flows, payment screens, document popups, and multi-tab apps.

## 5) Mouse and keyboard actions

### What it is
Selenium `Actions` class is used for hover, drag-and-drop, keyboard shortcuts, and advanced interaction.

### Common methods
- `moveToElement()`
- `clickAndHold()`
- `dragAndDrop()`
- `doubleClick()`
- `contextClick()`
- `sendKeys(Keys.CONTROL, "a")`

### When to use it
Use this when the UI needs user-like interaction beyond simple click or type.

## 6) Dropdowns

### Native dropdowns
Use the `Select` class for standard HTML dropdowns.

Examples:
- `selectByVisibleText()`
- `selectByValue()`
- `selectByIndex()`

### Custom dropdowns
Modern apps often use custom dropdowns built with HTML, CSS, or JavaScript.

When this happens:
- click the control
- find the option element
- click the desired option

### Why it matters
A lot of automation failures happen because developers treat custom dropdowns like native ones.

## 7) Web tables

### What it is
Web tables are HTML tables used for data grids and reports.

### Why it matters
A large part of enterprise testing involves searching or validating values in tables.

### Best practices
- Identify the row first, then work within that row
- Avoid fixed row numbers in production code
- Extract values by column text or row data
- Handle pagination if the table is long

## 8) Dynamic elements and AJAX pages

### What it is
Dynamic elements appear, disappear, or change after AJAX requests, timers, or page refresh.

### How to handle it
- use explicit waits
- re-find elements after page refresh
- handle stale element exceptions
- avoid unnecessary `Thread.sleep()`

### Why it matters
Most flaky tests happen because the app updates dynamically and scripts assume the element is already stable.

## 9) Shadow DOM

### What it is
Shadow DOM is a browser feature that encapsulates component internals.

### Why it matters
Selenium cannot directly interact with elements inside shadow DOM using normal locators unless you enter the shadow root.

### Common approach
- access the shadow root
- query inside it using JavaScript or a specialized strategy

## 10) JavaScript Executor

### What it is
`JavascriptExecutor` lets you run JavaScript in the browser.

### When to use it
- element click does not work normally
- you must scroll to an element
- hidden fields need value updates
- custom UI states need to be triggered

### Why it matters
It is a powerful tool for edge cases, but should not replace good Selenium locators and waits.

## 11) Page Object Model (POM)

### What it is
POM organizes pages as reusable objects instead of writing all code directly in tests.

### Why it matters
It reduces duplication and makes tests easier to maintain.

### Example benefits
- page objects hold locators
- test classes focus on behavior
- fixes can be made in one place

## 12) Framework design expectations

A senior-level framework usually includes:

- base driver setup and browser initialization
- config file handling
- page object classes
- utility classes for waits, screenshots, and reports
- test data management
- parallel execution support

### Why this matters
A real automation role is not only about writing a script—it is about building a sustainable automation framework.

## 13) CI/CD and execution model

### What it is
CI/CD automates testing in pipelines such as Jenkins, GitHub Actions, Azure DevOps, and GitLab CI.

### Why it matters
It ensures the tests run automatically for every code change and helps catch regressions early.

### Common practice
- run on headless browsers in CI
- save screenshots and reports as artifacts
- run smoke tests on pull requests
- run full regression suite nightly

## 14) Common interview questions

1. Difference between implicit wait and explicit wait
2. How do you handle stale element exceptions?
3. What is the difference between `close()` and `quit()`?
4. How do you handle alerts and popups?
5. How do you handle iframes and nested frames?
6. How do you handle dynamic tables?
7. Why is POM useful?
8. How do you make tests stable and not flaky?
9. How do you handle shadow DOM?
10. What are the best practices for Selenium interviews?

## 15) Senior-level mindset

Interviewers expect you to think beyond just writing code. They look for:

- test stability
- maintainable frameworks
- debugging ability
- efficient wait strategy
- good understanding of DOM and browser behavior
- knowledge of CI and production-quality automation

A strong answer is not just “this method works,” but “this is the right solution for the current UI behavior and project architecture.”

