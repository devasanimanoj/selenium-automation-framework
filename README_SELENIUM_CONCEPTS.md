# Selenium Java Practice & Interview Preparation - Comprehensive Framework

**Beginner to Expert Level Selenium Automation Training**

This directory contains comprehensive Selenium Java code examples organized by topics covering everything from basic to advanced web element handling. All examples are based on interview-level best practices and cover common real-world scenarios.

## 📚 Complete Curriculum

### Core Topics
- [x] Actions & Mouse Interactions
- [x] Window Handles & Tabs
- [x] Web Tables & Data Extraction
- [x] Dropdowns (Native & Custom)
- [x] Alerts & Dialog Handling
- [x] iFrames & Frame Switching
- [x] Shadow DOM
- [x] JavaScript Executor
- [x] File Handling (Upload/Download)
- [x] Excel Data Management
- [x] Properties File Configuration
- [x] Advanced Web Elements
- [x] Wait Strategies

---

## Directory Structure

### 1. **Actions Package** (`src/main/java/com/example/actions/`)
Advanced mouse and keyboard interactions.

| File | Description |
|------|-------------|
| `ActionsBasicDemo.java` | Basic setup and structure for Actions class |
| `MouseHoverDemo.java` | Mouse hover using `moveToElement()` |
| `RightClickDemo.java` | Right-click (context click) using `contextClick()` |
| `DoubleClickDemo.java` | Double-click using `doubleClick()` |
| `DragAndDropDemo.java` | Drag and drop using `dragAndDrop()` |
| `ManualDragDropDemo.java` | Manual drag-drop with `clickAndHold()`, `moveToElement()`, `release()` |
| `KeyboardCtrlADemo.java` | Keyboard combinations - CTRL + A |
| `KeyboardShortcutsReference.java` | Utility class for common keyboard shortcuts |
| `ActionsCompleteDemo.java` | Combined example with multiple actions |

**Key Concepts:**
- `perform()` executes the action sequence
- `build()` creates the sequence (rarely used separately)
- Chain multiple actions together
- Use Actions for complex interactions, not simple clicks

**Interview Topics:**
- Difference between Actions and WebElement.click()
- When to use build() vs perform()
- How to handle drag-and-drop failures
- Keyboard combinations (CTRL+A, CTRL+C, CTRL+V)

---

### 2. **Window Handles Package** (`src/main/java/com/example/windowhandles/`)
Handle browser windows and tabs.

| File | Description |
|------|-------------|
| `WindowHandleBasicDemo.java` | Basic window handle operations |
| `ParentChildWindowDemo.java` | Handle parent and child windows |
| `SwitchByTitleDemo.java` | Identify windows by title |
| `SwitchByUrlDemo.java` | Identify windows by URL |
| `NewTabDemo.java` | Selenium 4 - Open new tab |
| `NewWindowDemo.java` | Selenium 4 - Open new browser window |
| `CompleteWindowHandlingDemo.java` | Complete real-world pattern |

**Key Concepts:**
- `getWindowHandle()` → Returns current window (String)
- `getWindowHandles()` → Returns all windows (Set<String>)
- `switchTo().window(handle)` → Switch to specific window
- `close()` → Close current window
- `quit()` → Close entire session

**Interview Topics:**
- Difference between close() and quit()
- How to switch from child to parent window
- Identifying windows by title/URL vs handle
- Selenium 4 WindowType.TAB vs WindowType.WINDOW
- Why you should always switch back to parent after closing child

---

### 3. **Web Tables Package** (`src/main/java/com/example/webtables/`)
Handle static and dynamic web tables.

| File | Description |
|------|-------------|
| `WebTableBasicDemo.java` | Basic table operations and row counting |
| `PrintTableDemo.java` | Print complete table data |
| `FindEmployeeDemo.java` | Search for specific value in table |
| `TableRowActionDemo.java` | **IMPORTANT**: Find value and click button in same row |
| `TableColumnDemo.java` | Access specific columns by index |
| `DynamicTableXPathDemo.java` | Use dynamic XPath to find elements |
| `PaginationDemo.java` | Handle tables with pagination |
| `InterviewScenariosDemo.java` | Common interview scenarios and solutions |

**Key Concepts:**
- Scope searches to rows: `row.findElements()` not `driver.findElements()`
- Use dynamic XPath: `//tr[td[text()='Manoj']]//button`
- Never hard-code row indexes
- Identify rows by data (name, ID), not position
- Handle pagination by looping through pages

**Interview Topics:**
- How to find a row and click its button
- Getting values from specific columns
- Handling dynamic tables with changing data
- Pagination handling
- Why to avoid row indexes
- Table vs custom JavaScript grids

---

### 4. **Reference Package** (`src/main/java/com/example/reference/`)
Quick reference materials.

| File | Description |
|------|-------------|
| `SeleniumCheatSheet.java` | Complete cheat sheet with all syntax |

This file contains:
- All Actions syntax and examples
- All Window Handles syntax and examples
- All Web Tables syntax and examples
- Interview points and best practices

---

### 4. **Dropdowns Package** (`src/main/java/com/example/dropdowns/`)
Handle all types of dropdown selections.

| File | Description |
|------|-------------|
| `NativeSelectDropdownDemo.java` | Standard HTML `<select>` using Select class |
| `MultiSelectDropdownDemo.java` | Multi-select dropdowns with deselect options |
| `CustomDropdownDemo.java` | Bootstrap, Material UI, React/Angular custom dropdowns |

**Key Concepts:**
- `Select` class for native dropdowns
- Select by visible text, value, or index
- `selectByVisibleText()`, `selectByValue()`, `selectByIndex()`
- `getFirstSelectedOption()`, `getOptions()`
- `deselectByVisibleText()`, `deselectAll()`
- Custom dropdowns require click + find + click pattern

**Interview Topics:**
- Difference between Select and custom dropdowns
- How to handle multi-select
- Finding options in custom dropdowns
- Handling Angular Material/React dropdowns

---

### 5. **Alerts Package** (`src/main/java/com/example/alerts/`)
Handle JavaScript alerts, confirmations, and prompts.

| File | Description |
|------|-------------|
| `AlertHandlingDemo.java` | Simple, confirmation, and prompt alerts |

**Key Concepts:**
- Three alert types: Simple, Confirmation, Prompt
- `driver.switchTo().alert()` - Switch to alert
- `accept()` - Click OK
- `dismiss()` - Click Cancel
- `getText()` - Get alert message
- `sendKeys()` - Type in prompt

**Interview Topics:**
- How to handle each alert type
- Alert vs browser alerts
- Checking if alert exists
- Exception handling for alerts

---

### 6. **iFrames Package** (`src/main/java/com/example/iframes/`)
Handle inline frames and nested iFrames.

| File | Description |
|------|-------------|
| `IFrameHandlingDemo.java` | Switch by ID, name, index, WebElement; nested iFrames |

**Key Concepts:**
- `driver.switchTo().frame()` - Switch to iFrame
- Switch by ID, name, index, or WebElement
- `driver.switchTo().defaultContent()` - Return to main page
- `driver.switchTo().parentFrame()` - Return to parent
- Finding elements inside iFrames
- Nested iFrame navigation

**Interview Topics:**
- How to interact with elements in iFrames
- Nested iFrame handling strategy
- Finding elements across multiple iFrames
- Common mistakes when working with iFrames

---

### 7. **Shadow DOM Package** (`src/main/java/com/example/shadowdom/`)
Access and interact with Shadow DOM elements.

| File | Description |
|------|-------------|
| `ShadowDOMHandlingDemo.java` | Access, click, type in shadow DOM elements |

**Key Concepts:**
- Shadow DOM is encapsulated, not in light DOM
- Cannot use normal Selenium locators
- Use `JavascriptExecutor` with `shadowRoot.querySelector()`
- Nested shadow DOM navigation
- Finding elements by text in shadow DOM

**Interview Topics:**
- Why Selenium can't find shadow DOM elements
- How to penetrate shadow boundaries
- JavaScript method for shadow DOM
- Common frameworks using shadow DOM

---

### 8. **JavaScript Executor Package** (`src/main/java/com/example/javascript/`)
Execute JavaScript in the browser for advanced interactions.

| File | Description |
|------|-------------|
| `JavaScriptExecutorDemo.java` | Click, type, scroll, get properties using JS |

**Key Concepts:**
- `JavascriptExecutor` interface
- `executeScript()` and `executeAsyncScript()`
- Passing WebElements as arguments
- Click, type, scroll, highlight elements
- Get element properties via JavaScript
- Access DOM directly

**Common Uses:**
- Click when normal click fails
- Type when normal sendKeys fails
- Scroll to element or page
- Get/Set element attributes
- Check element visibility
- Remove elements from DOM
- Trigger change events

**Interview Topics:**
- When to use JavaScript Executor
- Avoid overusing JavaScript
- Real user vs automated interactions
- JavaScript error handling

---

### 9. **Excel Data Package** (`src/main/java/com/example/exceldata/`)
Read and write Excel files using Apache POI.

| File | Description |
|------|-------------|
| `ExcelDataHandlingDemo.java` | Read/write Excel, handle cells, data-driven testing |

**Dependencies:**
- `poi-X.X.X.jar`
- `poi-ooxml-X.X.X.jar`

**Key Concepts:**
- `Workbook`, `Sheet`, `Row`, `Cell` objects
- `FileInputStream` for reading
- `FileOutputStream` for writing
- Select by row/column index
- `getCellValue()` with type checking
- Find row by search value
- Get data as 2D array for data-driven testing
- Update cell values
- Auto-size columns

**Use Cases:**
- Test data management
- Data-driven testing
- Result reporting
- Performance comparison

**Interview Topics:**
- How to read Excel data
- How to write Excel data
- Handling different cell types
- Data-driven testing approach

---

### 10. **Properties File Package** (`src/main/java/com/example/propertyfiles/`)
Read and manage configuration files.

| File | Description |
|------|-------------|
| `PropertiesFileDemo.java` | Read/write properties, configuration class |

**Key Concepts:**
- `Properties` class
- `FileInputStream` for reading
- `FileOutputStream` for writing
- Key-value pair format
- `getProperty()` with default values
- Load all properties
- Update properties
- Configuration wrapper class

**Common Configuration Properties:**
```properties
browser=chrome
url=https://example.com
username=admin
password=12345
timeout=10
implicitWait=5
```

**Benefits:**
- Externalize configuration
- Easy to maintain
- No code changes for different environments
- Support for multiple configurations

**Interview Topics:**
- Externalized configuration benefits
- How to use Properties file
- Configuration vs hardcoding
- Environment-specific configurations

---

### 11. **Advanced WebElements Package** (`src/main/java/com/example/advancedwebelements/`)
Handle complex web element scenarios.

| File | Description |
|------|-------------|
| `AdvancedWebElementsDemo.java` | Hidden, disabled, stale, dynamic elements |

**Topics Covered:**
- Hidden Elements (display:none, visibility:hidden)
- Disabled Elements (disabled attribute)
- Stale Elements (element removed from DOM)
- Dynamic Elements (appear/disappear dynamically)
- Element Properties (text, attributes, location, size)
- Element State (displayed, enabled, selected)

**Solutions:**
- Make hidden elements visible using JavaScript
- Enable disabled elements
- Retry mechanism for stale elements
- Explicit wait for dynamic elements
- Get element properties and CSS values

**Interview Topics:**
- Handling StaleElementReferenceException
- Hidden vs disabled elements
- Dynamic element identification
- Element wait conditions

---

### 12. **File Handling Package** (`src/main/java/com/example/filehandling/`)
Upload and download files.

| File | Description |
|------|-------------|
| `FileUploadDownloadDemo.java` | File upload and download handling |

**File Upload:**
- Locate `input[type="file"]` element
- Use `sendKeys()` to upload
- Verify file upload

**File Download:**
- Configure Chrome options for download directory
- Set download preferences
- Click download link
- Wait for file to appear
- Verify downloaded file

**Configuration:**
```java
ChromeOptions options = new ChromeOptions();
Map<String, Object> prefs = new HashMap<>();
prefs.put("download.default_directory", downloadDir);
prefs.put("download.prompt_for_download", false);
options.setExperimentalOption("prefs", prefs);
```

**Interview Topics:**
- File upload mechanisms
- How to verify file downloads
- Browser configuration for downloads
- Download verification strategies

---

### 13. **Wait Strategies Package** (`src/main/java/com/example/waits/`)
Master different wait mechanisms.

| File | Description |
|------|-------------|
| `WaitStrategiesDemo.java` | Implicit, explicit, fluent waits with examples |

**Three Types of Waits:**

1. **Implicit Wait**
   - Applied globally
   - Default wait for all elements
   - `driver.manage().timeouts().implicitlyWait()`

2. **Explicit Wait (WebDriverWait)** ✅ **RECOMMENDED**
   - Applied to specific elements
   - More control and flexibility
   - `new WebDriverWait(driver, Duration.ofSeconds(10))`

3. **Fluent Wait**
   - Customizable polling frequency
   - Ignore specific exceptions
   - `new FluentWait<>(driver)`

**Common ExpectedConditions:**
- `presenceOfElementLocated()` - Element in DOM
- `visibilityOfElementLocated()` - Element visible
- `elementToBeClickable()` - Element clickable
- `elementSelectionStateToBe()` - Element selected
- `titleContains()` - Page title
- `urlContains()` - Page URL
- `alertIsPresent()` - Alert dialog
- `frameToBeAvailableAndSwitchToIt()` - iFrame ready

**Best Practices:**
1. Use Explicit Wait instead of Implicit
2. Don't mix Implicit and Explicit waits
3. Set reasonable timeouts (10-15 seconds)
4. Handle TimeoutException
5. Use appropriate conditions
6. Wait is last resort - improve reliability

**Interview Topics:**
- Difference between wait types
- Why explicit is better than implicit
- Common ExpectedConditions
- Custom wait conditions
- Wait timeout best practices

---

## � Selenium Locators: Beginner to Expert

### 1) Types of Locators in Selenium

Selenium supports multiple locator strategies. Always prefer the most stable and specific one.

1. **ID**
   - Most stable and recommended
   - `By.id("username")`
   - Example: `driver.findElement(By.id("loginBtn"));`

2. **Name**
   - Good when name attribute is unique
   - `By.name("email")`

3. **Class Name**
   - Useful for repeated elements
   - `By.className("btn-primary")`
   - Risk: classes can be dynamic and non-unique

4. **Tag Name**
   - Used for table rows, list items, labels
   - `By.tagName("tr")`

5. **Link Text**
   - Exact visible text of anchor tag
   - `By.linkText("Login")`

6. **Partial Link Text**
   - Match part of anchor text
   - `By.partialLinkText("Log")`

7. **CSS Selector**
   - Powerful and fast
   - `By.cssSelector("input[type='text']")`
   - Can handle classes, attributes, child selectors, nth-child, etc.

8. **XPath**
   - Very powerful for dynamic and complex DOMs
   - `By.xpath("//input[@id='username']")`
   - Best for complex page structures or dynamic UI

### 2) Absolute vs Relative XPath

#### Absolute XPath
- Starts from the root node
- Example: `/html/body/div[1]/form/input[1]`
- Not recommended because it breaks easily if DOM structure changes

#### Relative XPath
- Starts from anywhere in the DOM using `//`
- Example: `//input[@name='username']`
- Recommended because it is more flexible and stable

**Best Practice:** Prefer relative XPath over absolute XPath.

### 3) XPath Syntaxes You Must Know

#### Basic XPath
```java
//input
//button
//div[@id='login']
```

#### With Attribute
```java
//input[@type='text']
//button[@class='submit-btn']
//a[@href='https://example.com']
```

#### Multiple Conditions (AND / OR)
```java
//input[@type='text' and @name='username']
//input[@type='text' or @type='email']
//button[@id='submit' and @class='primary']
```

#### Contains
```java
//*[contains(@id, 'user')]
//*[contains(@class, 'btn')]
//*[contains(text(), 'Submit')]
//*[contains(@href, 'login')]
```

#### Starts-With
```java
//*[starts-with(@id, 'user')]
//*[starts-with(@class, 'btn')]
//*[starts-with(text(), 'Welcome')]
```

#### Text-Based XPath
```java
//button[text()='Submit']
//span[text()='Login']
//a[contains(text(), 'Forgot Password')]
```

#### Normalize Space
```java
//div[normalize-space(text())='Welcome User']
//*[normalize-space(@class)='nav-item active']
```

#### Wildcard / Any Element
```java
//*[@id='username']
//*[text()='Login']
```

#### XPath with Parent / Child / Descendant
```java
//div[@id='form']//input
//form//button
//div[@class='container']/input
```

#### XPath with Indexes
```java
(//input)[1]
(//button)[3]
//table//tr[2]
```

> Avoid index-based XPath unless necessary because the DOM can change.

### 4) XPath Axes

XPath axes help navigate nodes relative to the current node.

#### A. Self Axis
- Selects the current node itself
```xpath
self::node()
```
Example:
```xpath
//input[@id='username']/self::input
```

#### B. Forward Axes
These move in the direction of document order.

- `child::` - direct children
```xpath
//div/child::input
```

- `descendant::` - all descendants
```xpath
//div/descendant::input
```

- `following::` - all nodes after current node in document order
```xpath
//input[@id='username']/following::button
```

- `following-sibling::` - siblings after current node
```xpath
//label[@for='email']/following-sibling::input
```

- `attribute::` - attributes of the current node
```xpath
//input/@type
```

#### C. Backward Axes
These move backward in document order.

- `parent::`
```xpath
//input[@id='username']/parent::form
```

- `ancestor::`
```xpath
//button[@type='submit']/ancestor::form
```

- `preceding::` - all nodes before current node
```xpath
//button[@id='submit']/preceding::input
```

- `preceding-sibling::` - same parent, before current node
```xpath
//input[@id='email']/preceding-sibling::label
```

### 5) Very Important XPath Patterns for Interviews

#### Find element by text
```java
//button[text()='Submit']
//span[contains(text(),'Login')]
```

#### Match partial attribute value
```java
//*[contains(@id, 'user')]
//*[contains(@class, 'btn')]
```

#### Match starts-with on attribute or text
```java
//*[starts-with(@id, 'user')]
//*[starts-with(text(), 'Welcome')]
```

#### Use AND / OR for multiple checks
```java
//input[@type='text' and @name='email']
//button[@id='save' or @name='submit']
```

#### Navigate using parent-child relation
```java
//div[@class='user-row']//button
//table//tr[td[text()='Manoj']]/td[3]
```

#### Use relative path with descendant search
```java
//div[@class='container']//input[@type='text']
```

### 6) Dynamic XPath Handling

Dynamic elements often have changing IDs, classes, or values. Use stable attributes, text, or patterns instead of static full values.

#### Example 1: ID changes partially
```java
//input[contains(@id, 'userName')] 
```

#### Example 2: Class value contains changing suffix
```java
//button[contains(@class, 'primary-btn')]
```

#### Example 3: Dynamic text with exact pattern
```java
//td[contains(text(), 'Employee')]
```

#### Example 4: Variable values using string concat
```java
//div[@id='user-' + '123']
```

#### Example 5: Using ancestor/descendant to make robust searches
```java
//div[@class='table-row']//button[contains(text(),'Edit')]
```

#### Example 6: Replace fragile index usage
```java
//table//tr[td[text()='Manoj']]//button
```

**Dynamic XPath best practices:**
- Prefer `contains()`, `starts-with()`, `text()`, `normalize-space()`
- Avoid brittle absolute paths
- Use nearest stable parent and then locate child
- Avoid full index-based locators unless absolutely required
- Use `//*[contains(@id,'...')]` when IDs are dynamic

### 7) XPath Best Practices

- Prefer relative XPath (`//...`) over absolute XPath (`/html/...`)
- Use stable attributes such as `id`, `name`, `data-testid`, or text
- Use meaningful relationships: parent-child, ancestor-descendant, sibling
- Use `contains()` and `starts-with()` for dynamic element IDs/classes
- Use `normalize-space()` when text has extra spaces or line breaks
- Avoid too much complexity; keep XPath readable and maintainable

### 8) XPath vs CSS Selector

| Use Case | Prefer |
|---------|--------|
| Simple and stable attributes | CSS selector |
| Dynamic text or DOM traversal | XPath |
| Parent/child/sibling relationships | XPath |
| Quick attribute matching | CSS |
| Very dynamic UI | XPath with contains() |

### 9) Locator Strategy Summary

#### Best Priority Order
1. `By.id()`
2. `By.name()`
3. `By.cssSelector()`
4. `By.xpath()`
5. `By.linkText()` / `By.partialLinkText()`
6. `By.className()` / `By.tagName()`

> Use CSS for speed and simplicity; use XPath when DOM relationships or dynamic content require it.

---

## 🚀 Advanced Selenium Interview Topics for 6+ Years Experience

These are the concepts most interviewers expect from a senior Selenium automation engineer.

### 1) Page Object Model (POM)

- Separate page logic from test logic
- Create a `BasePage` for shared reusable methods
- Create page classes for each screen or component
- Keep locators in page objects, not tests
- Reuse actions such as click, type, wait, select, upload
- Use `PageFactory` or explicit element retrieval for cleaner code

**Best Practice:**
- Keep business logic in tests
- Keep UI operations in page classes
- Avoid using raw locators across multiple test classes

### 2) Framework Design and Architecture

A strong Selenium framework usually includes:
- Base driver setup and browser launch logic
- Test configuration properties
- Utilities for wait, screenshot, reporting, Excel/JSON parsing
- Logging and custom exception handling
- Retry strategy for flaky tests
- Reporting with Extent Reports / Allure / TestNG reports

**Senior-level expectation:**
- Understand reusable architecture, not just test scripts
- Build maintainable automation frameworks for large-scale projects

### 3) Browser and Execution Strategy

- ChromeOptions / FirefoxOptions / EdgeOptions
- Headless browser execution for CI
- Browser profile and preferences configuration
- Disable notifications and popups
- Run on Windows/Linux/macOS agents
- Use `RemoteWebDriver` for Selenium Grid

### 4) Selenium Grid and Parallel Execution

- Run tests on multiple browsers and machines
- Use `grid` for cross-browser validation
- Use `@DataProvider` / JUnit parameterization / Maven Surefire parallelization
- Keep parallel execution safe and isolated
- Avoid shared state between tests

**Important interview question:**
- Why do you need `driver.quit()` in `@AfterMethod` or `@AfterEach`? Stop browser sessions to avoid resource leaks.

### 5) Dynamic Web Elements and Flaky Tests

- Prefer explicit waits over sleep
- Use `ExpectedConditions` and custom wait wrappers
- Handle stale element exceptions
- Use JS fallback when normal interaction fails
- Re-fetch element references after page refresh or AJAX updates
- Prefer stable locators such as `data-testid`, IDs, names, or roles

### 6) Shadow DOM, Frames, and Nested UI

- Use `switchTo().frame()` for iframes
- Use JavaScript access for Shadow DOM
- Handle nested frames and parent-to-child navigation carefully
- Always return to default content before switching elsewhere

### 7) Test Data Management

- Use Excel, CSV, JSON, properties files, or database-backed test data
- Keep test data separate from test logic
- Use parameterized tests for multiple data combinations
- Manage environment-specific values using config files

### 8) Reporting, Debugging, and Observability

- Capture screenshots on failure
- Log step-by-step activity
- Record test pass/fail status
- Add page title, URL, and browser metadata to reports
- Keep failures actionable and reproducible

### 9) CI/CD and Automation Pipeline

- Run Selenium tests in Jenkins, GitHub Actions, GitLab CI, Azure DevOps
- Use Maven or Gradle in pipeline
- Publish test results and screenshots as artifacts
- Handle browser driver updates automatically
- Run smoke tests in PR validation and full suite nightly

### 10) API + UI Testing Combination

- UI automation validates user experience
- API tests validate backend contracts
- Use API responses to seed test data and verify state
- Good automation testers know both UI and backend flows

### 11) Performance, Security, and Accessibility Basics

- Avoid brittle waits and slow XPath chains
- Use stable selectors and minimal DOM traversal
- Understand basic accessibility patterns like ARIA labels
- Avoid hardcoded secrets and environment credentials
- Validate not just functionality but accessibility expectations

### 12) Real Interview Questions for Senior Selenium Roles

1. How do you handle flaky tests?
   - Use explicit waits, stable locators, retry strategy, clean session management, and avoid thread-unsafe global state.

2. What is a good automation framework design?
   - Base class, utility layer, page objects, data providers, reports, and configuration handling.

3. What is the difference between implicit, explicit, and fluent waits?
   - Implicit waits are global; explicit waits are targeted; fluent waits are configurable and polling-based.

4. How do you handle dynamic UI elements?
   - Use waits, stable locators, polling, re-find strategy, and JavaScript fallback when needed.

5. How do you run tests in parallel?
   - Use Selenium Grid, Maven Surefire fork count, or framework-level parallel configuration.

6. How do you keep tests maintainable?
   - Use POM, page object reuse, modular utilities, readable names, and centralized configuration.

---

## 🧠 Git: From Setup to Merge (Beginner to Expert)

### 1) Git Basics

Git is a version control system used to track changes in code across time.

#### Common Git Terms
- **Repository**: project folder managed by Git
- **Commit**: saved snapshot of your work
- **Branch**: independent line of work
- **Merge**: combine changes from one branch into another
- **Pull**: fetch and integrate changes from remote
- **Push**: upload local changes to remote repository
- **Clone**: copy a remote repository to local machine
- **Conflict**: when two branches modify same lines differently

### 2) Initial Git Setup

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
git config --global --list
```

#### Project Setup
```bash
git init
git status
git add .
git commit -m "Initial commit"
```

### 3) Git Workflow for Daily Use

```bash
git clone <repo-url>
git status
git add <file>
git commit -m "Add feature"
git push origin main
git pull origin main
```

### 4) Branching

```bash
git branch
git branch feature-login
git checkout feature-login
# or
git switch feature-login
```

Create and switch in one command:
```bash
git checkout -b feature-login
```

### 5) Merge Concepts

#### Merge a branch into current branch
```bash
git checkout main
git merge feature-login
```

#### Merge conflict example
If both branches changed the same file and same lines, Git raises a conflict.

Resolve manually in the file, then:
```bash
git add <resolved-file>
git commit -m "Resolve merge conflict"
```

### 6) Pull Request / Merge Request Review

- Create branch from main
- Make changes and commit
- Push branch to remote
- Open a pull request
- Review code and approve
- Merge to main

### 7) Rebase vs Merge

#### Merge
- Keeps branch history intact
- Best for shared/public branches

```bash
git merge feature-login
```

#### Rebase
- Replays commits on top of another branch
- Produces cleaner linear history

```bash
git rebase main
```

> Use rebase carefully on shared branches. Merging is safer for team workflows.

### 8) Common Git Commands

```bash
git status
git log
git log --oneline --decorate --graph --all
git diff
git diff --staged
git stash
git stash pop
git reset --soft HEAD~1
git reset --hard HEAD~1
git restore --staged <file>
git restore <file>
```

### 9) Remote Repository Commands

```bash
git remote -v
git remote add origin <repo-url>
git remote set-url origin <new-url>
git fetch origin
git pull origin main
git push origin main
```

### 10) .gitignore

Create `.gitignore` to avoid committing unwanted files:

```gitignore
node_modules/
*.class
*.log
.idea/
.vscode/
target/
```

### 11) Git Best Practices

- Commit often with meaningful messages
- Use small logical commits
- Pull before push to avoid conflicts
- Keep main branch stable
- Use branches for features/bugs
- Resolve conflicts carefully
- Never commit secrets or credentials

### 12) Git Interview Questions You Should Know

1. What is Git?
   - A distributed version control system

2. Difference between git pull and git fetch?
   - `fetch` downloads updates without merging; `pull` fetches and merges

3. What is merge conflict?
   - Happens when changes overlap in the same file lines

4. Difference between merge and rebase?
   - Merge preserves history; rebase rewrites/linearizes it

5. What is `.gitignore`?
   - File telling Git which files not to track

6. Why use branches?
   - To work on features safely without affecting main

### 13) Advanced Git Commands for Real Projects

```bash
git status
git add .
git commit -m "Add feature"
git push origin main
git pull --rebase origin main
git fetch origin
git checkout -b feature/login
git switch main
git merge feature/login
git cherry-pick <commit-sha>
git stash
git stash list
git stash pop
git revert <commit-sha>
git reset --soft HEAD~1
git reset --mixed HEAD~1
git reset --hard HEAD~1
git reflog
git tag v1.0.0
git push origin v1.0.0
```

### 14) Advanced Git Best Practices

- Always pull latest changes before starting work
- Use feature branches for every change
- Keep commit messages clear and specific
- Do not rewrite shared branch history unless you are sure everyone agrees
- Use `git stash` for temporary work
- Use `git revert` for undoing a public commit safely
- Use `.gitignore` aggressively to keep repos clean
- Prefer PR-based review for team code quality

### 15) Day-to-Day Git Flow for a Team

```bash
git checkout main
git pull origin main
git checkout -b feature/selenium-login
git add .
git commit -m "Add Selenium login automation"
git push -u origin feature/selenium-login
git checkout main
git pull origin main
git merge feature/selenium-login
git push origin main
```

### 16) Git Interview Answers for Senior Roles

- `git fetch` = download remote refs, no merge
- `git pull` = fetch + merge/rebase
- `git merge` = combine branch history into current one
- `git rebase` = move branch commits onto another base
- `git revert` = create a new commit undoing older work safely
- `git stash` = save local changes temporarily
- `git reset --hard` = discard local changes completely
- `.gitignore` = tells Git which files/folders not to track

---

## ✅ Final Interview Preparation Checklist

For a 6-year Selenium profile, prepare these topics thoroughly:
- Selenium WebDriver basics and architecture
- XPath and CSS selectors
- Wait strategies and stale element handling
- Alerts, frames, windows, tabs, and shadow DOM
- Dropdowns, web tables, dynamic elements
- POM, framework design, and maintainability
- TestNG/JUnit, Maven, and parallel execution
- CI/CD and browser automation in pipelines
- Data-driven testing and reporting
- Git workflow, PRs, branch management, and merge conflict resolution

This is the level expected from experienced automation engineers.

---

## �📋 Quick Interview Checklist

### Actions Class
- [ ] Understand perform() vs build()
- [ ] Know mouse operations (hover, click, right-click, double-click, drag)
- [ ] Know keyboard operations (CTRL+A, CTRL+C, CTRL+V)
- [ ] Know when to use Actions vs WebElement methods
- [ ] Know how to chain multiple actions

### Window Handles
- [ ] Know getWindowHandle() vs getWindowHandles() return types
- [ ] Know how to switch between parent and child windows
- [ ] Know how to identify windows by title/URL
- [ ] Know difference between close() and quit()
- [ ] Know Selenium 4 newWindow() method
- [ ] Know the pattern: store parent → switch to child → close child → switch back

### Web Tables
- [ ] Know how to count rows and columns
- [ ] Know how to get cell values
- [ ] Know how to find a row and click its button (VERY IMPORTANT)
- [ ] Know dynamic XPath approach
- [ ] Know how to handle pagination
- [ ] Know to scope searches to rows (row.findElements, not driver.findElements)
- [ ] Know to avoid hard-coded row indexes
- [ ] Know how to extract complete table data

### Dropdowns
- [ ] Know native `<select>` using Select class
- [ ] Know selectByVisibleText, selectByValue, selectByIndex
- [ ] Know how to handle multi-select
- [ ] Know difference between native and custom dropdowns
- [ ] Know how to find and click options in custom dropdowns
- [ ] Know Angular Material, React, Bootstrap dropdown patterns

### Alerts & Dialogs
- [ ] Know three types of alerts (simple, confirm, prompt)
- [ ] Know how to accept/dismiss alerts
- [ ] Know how to send text to prompt
- [ ] Know how to check if alert exists
- [ ] Know alert vs browser alerts
- [ ] Know exception handling for alerts

### iFrames
- [ ] Know how to switch to iFrame by ID, name, index, WebElement
- [ ] Know how to switch to default content
- [ ] Know how to handle nested iFrames
- [ ] Know parentFrame() vs defaultContent()
- [ ] Know how to find elements inside iFrames
- [ ] Know common mistakes with iFrames

### Shadow DOM
- [ ] Know why Selenium can't find shadow DOM elements
- [ ] Know how to use shadowRoot.querySelector()
- [ ] Know JavaScript approach to penetrate shadow
- [ ] Know nested shadow DOM navigation
- [ ] Know when to suspect shadow DOM

### JavaScript Executor
- [ ] Know when to use JavaScript Executor
- [ ] Know how to click using JavaScript
- [ ] Know how to type using JavaScript
- [ ] Know how to scroll page and elements
- [ ] Know how to get element properties
- [ ] Know user-like vs automated behavior

### Excel Data
- [ ] Know how to read Excel files
- [ ] Know how to write Excel files
- [ ] Know Workbook, Sheet, Row, Cell objects
- [ ] Know how to find specific data
- [ ] Know how to use for data-driven testing
- [ ] Know how to handle different cell types

### Properties Files
- [ ] Know how to read properties files
- [ ] Know how to write properties files
- [ ] Know key-value pair format
- [ ] Know configuration wrapper pattern
- [ ] Know externalized configuration benefits
- [ ] Know getProperty() with defaults

### Advanced WebElements
- [ ] Know how to handle hidden elements
- [ ] Know how to handle disabled elements
- [ ] Know StaleElementReferenceException and retry
- [ ] Know dynamic element waiting
- [ ] Know element properties and CSS values
- [ ] Know element state checks

### File Handling
- [ ] Know how to upload files
- [ ] Know how to configure download directory
- [ ] Know how to verify downloads
- [ ] Know file verification strategies
- [ ] Know multi-file upload
- [ ] Know file content verification

### Wait Strategies
- [ ] Know three types of waits
- [ ] Know implicit vs explicit waits
- [ ] Know WebDriverWait and ExpectedConditions
- [ ] Know common conditions (presence, visibility, clickable)
- [ ] Know fluent wait with custom polling
- [ ] Know when to use waits
- [ ] Know timeout best practices

---

## Common Interview Questions & Best Answers

### Actions Class
1. **Why use Actions instead of WebElement.click()?**
   - Actions is for complex interactions like hover, right-click, drag-drop, and keyboard combinations. For simple clicks, use WebElement.click().

2. **What is the difference between build() and perform()?**
   - build() creates the action sequence, perform() executes it. Normally, perform() is sufficient.

3. **How do you perform drag-and-drop if dragAndDrop() doesn't work?**
   - Use: `clickAndHold(source).moveToElement(target).release().perform();`

### Window Handles
1. **How do you switch from child window back to parent?**
   - Store parent handle before opening child: `driver.switchTo().window(parentWindow);`

2. **What's the difference between close() and quit()?**
   - close() closes the current window, quit() closes the entire WebDriver session.

3. **How do you handle multiple browser windows?**
   - Store parent handle, get all windows, compare handles, identify the child, switch to it.

### Web Tables
1. **How do you find an employee and click the Edit button?**
   ```java
   for (WebElement row : rows) {
       if (row.getText().contains("Manoj")) {
           row.findElement(By.tagName("button")).click();
           break;
       }
   }
   ```

2. **What's the best way to identify table rows?**
   - Use business data (name, ID), not row positions: `//tr[td[text()='Manoj']]`

3. **How do you handle tables with pagination?**
   - Loop through pages: search → if found: perform action; if not: click Next; if Next disabled: stop

---

## Running the Examples

1. Ensure Selenium WebDriver and ChromeDriver are in your classpath
2. Update the URL in examples (currently using `https://example.com`)
3. Adjust By locators to match your test application
4. Run each Java file as a standalone program

## Best Practices Summary

✅ **DO:**
- Use row-scoped searches: `row.findElements()`
- Identify rows by data, not position
- Store parent window handle before opening child windows
- Use dynamic XPath for simple operations
- Loop for complex table operations
- Handle exceptions in real automation code

❌ **DON'T:**
- Use hard-coded row indexes
- Search entire page for table elements
- Assume window/tab order
- Forget to switch back to parent after closing child
- Use only dragAndDrop() without fallback logic
- Create multiple Windows without tracking handles

---

## Shadow DOM
- [ ] Know why Selenium can't find shadow DOM elements
- [ ] Know how to use shadowRoot.querySelector()
- [ ] Know JavaScript approach to penetrate shadow
- [ ] Know nested shadow DOM navigation
- [ ] Know when to suspect shadow DOM

### JavaScript Executor
- [ ] Know when to use JavaScript Executor
- [ ] Know how to click using JavaScript
- [ ] Know how to type using JavaScript
- [ ] Know how to scroll page and elements
- [ ] Know how to get element properties
- [ ] Know user-like vs automated behavior

### Excel Data
- [ ] Know how to read Excel files
- [ ] Know how to write Excel files
- [ ] Know Workbook, Sheet, Row, Cell objects
- [ ] Know how to find specific data
- [ ] Know how to use for data-driven testing
- [ ] Know how to handle different cell types

### Properties Files
- [ ] Know how to read properties files
- [ ] Know how to write properties files
- [ ] Know key-value pair format
- [ ] Know configuration wrapper pattern
- [ ] Know externalized configuration benefits
- [ ] Know getProperty() with defaults

### Advanced WebElements
- [ ] Know how to handle hidden elements
- [ ] Know how to handle disabled elements
- [ ] Know StaleElementReferenceException and retry
- [ ] Know dynamic element waiting
- [ ] Know element properties and CSS values
- [ ] Know element state checks

### File Handling
- [ ] Know how to upload files
- [ ] Know how to configure download directory
- [ ] Know how to verify downloads
- [ ] Know file verification strategies
- [ ] Know multi-file upload
- [ ] Know file content verification

### Wait Strategies
- [ ] Know three types of waits
- [ ] Know implicit vs explicit waits
- [ ] Know WebDriverWait and ExpectedConditions
- [ ] Know common conditions (presence, visibility, clickable)
- [ ] Know fluent wait with custom polling
- [ ] Know when to use waits
- [ ] Know timeout best practices

---

## 🎓 Complete Interview Q&A

### Dropdowns (Complete)
1. **How to select from native HTML dropdown?**
   - Use Select class: `new Select(element).selectByVisibleText("India");`

2. **How to handle custom dropdowns (Bootstrap, Material UI)?**
   - Click to open, find options, click selected option

3. **How to handle multi-select?**
   - Check `isMultiple()`, select multiple options, use `deselectAll()`

4. **Difference between Select and custom dropdowns?**
   - Select: HTML `<select>` element, use Select class
   - Custom: DIV-based, requires manual click and find

### Alerts (Complete)
1. **Types of JavaScript alerts?**
   - Simple alert (OK button only)
   - Confirmation (OK/Cancel)
   - Prompt (Input field + OK/Cancel)

2. **How to handle each alert type?**
   - All use `driver.switchTo().alert()` then accept/dismiss/sendKeys

3. **How to check if alert exists?**
   ```java
   try {
       driver.switchTo().alert();
       return true;
   } catch (NoAlertPresentException e) {
       return false;
   }
   ```

### iFrames (Complete)
1. **How to interact with iFrame elements?**
   - Switch to frame: `driver.switchTo().frame(id/name/index/element)`
   - Find element: `driver.findElement()`
   - Switch back: `driver.switchTo().defaultContent()`

2. **How to handle nested iFrames?**
   - Switch to outer frame → switch to inner from outer → use parentFrame() to navigate

3. **Common iFrame mistakes?**
   - Forgetting to switch to frame before finding element
   - Not switching back to default content
   - Assuming frame order

### Shadow DOM (Complete)
1. **Why can't Selenium find shadow DOM elements?**
   - Shadow DOM is encapsulated, separate from light DOM
   - Selenium queries light DOM only

2. **How to access shadow DOM?**
   ```java
   WebElement element = (WebElement) jsExecutor.executeScript(
       "return arguments[0].shadowRoot.querySelector('#id');",
       hostElement
   );
   ```

3. **When to suspect shadow DOM?**
   - Element not found using normal locators
   - Modern web components (Polymer, LitElement)
   - Chrome custom elements

### JavaScript Executor (Complete)
1. **When is JavaScript Executor necessary?**
   - Normal click fails (ElementClickIntercepted)
   - Hidden elements need interaction
   - Shadow DOM access
   - Complex JavaScript operations

2. **Disadvantages of using JS?**
   - Not user-like behavior
   - Can't interact with real browser events
   - Fragile to DOM changes

3. **Common JavaScript operations?**
   - Click: `arguments[0].click();`
   - Type: `arguments[0].value = 'text';`
   - Scroll: `window.scrollTo(0, y);`
   - Get properties: `return arguments[0].getAttribute('attr');`

### Excel Data (Complete)
1. **How to read Excel using POI?**
   ```java
   FileInputStream fis = new FileInputStream(file);
   Workbook workbook = new XSSFWorkbook(fis);
   Sheet sheet = workbook.getSheetAt(0);
   Row row = sheet.getRow(0);
   Cell cell = row.getCell(0);
   ```

2. **How to use Excel for data-driven testing?**
   - Read data as 2D array
   - Loop through rows
   - Execute test for each data set

3. **How to handle different cell types?**
   ```java
   CellType type = cell.getCellType();
   if (type == CellType.STRING) { ... }
   if (type == CellType.NUMERIC) { ... }
   ```

### Properties File (Complete)
1. **Why use properties files?**
   - Externalize configuration
   - Easy to change environments
   - No code changes needed
   - Version control friendly

2. **How to read properties?**
   ```java
   Properties props = new Properties();
   props.load(new FileInputStream("config.properties"));
   String value = props.getProperty("key", "default");
   ```

3. **Best practices for configuration?**
   - Separate prod/test configs
   - Don't store credentials
   - Use meaningful key names
   - Document all properties

### Advanced WebElements (Complete)
1. **How to handle hidden elements?**
   - Make visible: `jsExecutor.executeScript("arguments[0].style.display='block';", element);`
   - Or interact directly via JavaScript

2. **How to handle stale elements?**
   - Catch `StaleElementReferenceException`
   - Re-find element: `driver.findElement(locator)`
   - Or use retry logic

3. **How to check element state?**
   - `isDisplayed()` - Visible on page
   - `isEnabled()` - Can be interacted with
   - `isSelected()` - Selected (checkbox, radio)

### File Handling (Complete)
1. **How to upload files?**
   - Find `input[type="file"]`
   - Use `sendKeys(file_path)`
   - sendKeys() works uniquely for file inputs

2. **How to handle downloads?**
   - Configure Chrome options with download directory
   - Set prefs for no prompt
   - Click download link
   - Wait for file in directory

3. **How to verify downloads?**
   - Poll download directory
   - Check file exists
   - Verify file size/content

### Wait Strategies (Complete)
1. **Implicit vs Explicit wait?**
   - Implicit: Global, all elements wait specified time
   - Explicit: Specific elements, specific conditions

2. **Which is better and why?**
   - Explicit is better (more control, faster)
   - Avoid mixing both
   - Use explicit in modern frameworks

3. **Common ExpectedConditions?**
   - `presenceOfElementLocated()` - Element in DOM
   - `visibilityOfElementLocated()` - Visible
   - `elementToBeClickable()` - Can click
   - `titleContains()`, `urlContains()` - Page state

---

## 🚀 Real-World Testing Scenarios

### Scenario 1: E-commerce Checkout
```
1. Search product (handle AJAX loading)
2. Select from size dropdown
3. Upload product image
4. Add to cart (wait for confirmation)
5. Proceed to checkout (multi-step)
6. Fill payment form in iFrame
7. Handle confirmation alert
8. Verify order in new window
```

### Scenario 2: Data-Driven Employee Management
```
1. Load employee data from Excel
2. For each employee:
   a. Create record using form
   b. Select department from custom dropdown
   c. Assign role from native dropdown
   d. Upload employee photo
3. Verify created records in table
4. Write results to Excel
```

### Scenario 3: Complex Form with Dynamic Content
```
1. Load configuration from properties file
2. Navigate to form (handle page load)
3. Fill basic fields
4. Interact with hidden fields using JavaScript
5. Access custom controls in shadow DOM
6. Handle nested iFrames (payment form)
7. Process alerts
8. Download invoice
9. Verify using properties
```

### Scenario 4: Data Extraction & Reporting
```
1. Login to application
2. Navigate to employee table
3. Search and extract data by pagination
4. Handle dynamic column updates
5. Write to Excel report
6. Cross-reference with properties file
7. Generate automated summary
```

---

## 📊 Framework Architecture

### Recommended Structure
```
selenium-automation-framework/
├── src/
│   ├── main/java/com/example/
│   │   ├── config/              # Configuration classes
│   │   ├── pages/               # Page Object Model
│   │   ├── utilities/           # Reusable utilities
│   │   └── drivers/             # WebDriver factory
│   ├── test/java/com/example/
│   │   ├── tests/               # Test cases
│   │   └── testdata/            # Test data
│   └── resources/
│       ├── config.properties    # Configuration
│       ├── testdata/            # Excel files
│       └── log4j.properties     # Logging
├── pom.xml                      # Maven dependencies
└── README.md
```

### Dependencies to Include
```xml
<!-- Selenium -->
<selenium-java>4.15.0</selenium-java>

<!-- Apache POI -->
<poi-ooxml>5.0.0</poi-ooxml>

<!-- WebDriverManager -->
<webdrivermanager>5.6.0</webdrivermanager>

<!-- Logging -->
<log4j-core>2.20.0</log4j-core>

<!-- Testing Framework -->
<testng>7.8.1</testng>
<junit>4.13.2</junit>
```

---

## 🎯 Quick Reference

### Locator Strategies (Priority Order)
1. ID - Most stable
2. Name - Stable
3. CSS Selector - Good
4. XPath - Last resort
5. Link Text - For links only

### Wait Best Practices
1. Use WebDriverWait with explicit conditions
2. Timeout: 10-15 seconds typically
3. Handle TimeoutException
4. Avoid Thread.sleep()
5. Improve test stability first, then wait

### Element Interaction Priority
1. Try WebElement methods (click, sendKeys, etc.)
2. Use Actions for complex interactions
3. Use JavaScript only as fallback
4. Use JavaScript for shadow DOM

### Configuration Best Practices
1. Externalize via properties files
2. Use environment variables for secrets
3. One config per environment (dev/test/prod)
4. Version control configs (except secrets)
5. Document all configuration options

---

## 📞 Troubleshooting

| Problem | Solution |
|---------|----------|
| ElementNotInteractableException | Use JavaScript click or wait for visibility |
| StaleElementReferenceException | Re-find element or use retry logic |
| NoSuchElementException | Use explicit wait with presence condition |
| TimeoutException | Verify element locator, check app for issues |
| Shadow DOM element not found | Use JavaScript with shadowRoot.querySelector() |
| iFrame element not found | Verify you switched to correct frame |
| Download not working | Configure Chrome prefs, verify directory |
| Excel read fails | Check POI version, file format, encoding |

---

## 💡 Expert Tips

1. **Always use Explicit Waits**
   - More reliable than implicit
   - Better performance
   - More control

2. **Scope Element Searches**
   - Find parent first
   - Search within parent
   - Faster and more reliable

3. **Use Data-Driven Tests**
   - Read from Excel
   - Parameterize tests
   - Easy to add more tests

4. **Externalize Configuration**
   - Properties files
   - Environment specific
   - No code changes

5. **Handle Exceptions Properly**
   - Catch specific exceptions
   - Log properly
   - Fail gracefully

6. **Maintain Test Independence**
   - No test depends on another
   - Setup/teardown properly
   - Use fresh data

7. **Use Page Object Model**
   - Separate pages from tests
   - Reusable page objects
   - Easier maintenance

8. **Log Everything**
   - Use proper logging
   - Include context
   - Help with debugging

---

*Last Updated: 2026-09-01*
*Comprehensive Selenium Java Framework - Beginner to Expert Level*
*Complete Coverage: 13 Major Topics with 25+ Java Classes and 50+ Interview Questions*
