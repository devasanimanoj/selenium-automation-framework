# Selenium WebDriver - Quick Reference Cheat Sheet

## 🔍 Element Locators

```java
// ID
By.id("elementId")

// Name
By.name("elementName")

// Class Name
By.className("className")

// Tag Name
By.tagName("div")

// CSS Selector
By.cssSelector("div.class > p")

// XPath
By.xpath("//div[@id='test']")

// Link Text
By.linkText("Click Here")

// Partial Link Text
By.partialLinkText("Click")
```

---

## ⌨️ Finding Elements

```java
// Single element
WebElement element = driver.findElement(By.id("id"));

// Multiple elements
List<WebElement> elements = driver.findElements(By.tagName("div"));

// With explicit wait
WebElement element = wait.until(
    ExpectedConditions.presenceOfElementLocated(By.id("id"))
);

// Clickable element
WebElement element = wait.until(
    ExpectedConditions.elementToBeClickable(By.id("id"))
);

// Visible element
WebElement element = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.id("id"))
);
```

---

## 🖱️ Element Interactions

```java
// Click
element.click();

// Type text
element.sendKeys("text");

// Clear
element.clear();

// Submit form
element.submit();

// Get text
String text = element.getText();

// Get attribute
String value = element.getAttribute("value");

// Check if displayed
boolean isDisplayed = element.isDisplayed();

// Check if enabled
boolean isEnabled = element.isEnabled();

// Check if selected
boolean isSelected = element.isSelected();

// Get size
Dimension size = element.getSize();

// Get location
Point location = element.getLocation();

// Get tag name
String tagName = element.getTagName();
```

---

## ⏳ Waits

### Implicit Wait
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

### Explicit Wait - Common Conditions
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Presence
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));

// Visibility
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));

// Clickability
wait.until(ExpectedConditions.elementToBeClickable(By.id("id")));

// Text to be present
wait.until(ExpectedConditions.textToBePresentInElement(element, "text"));

// URL contains
wait.until(ExpectedConditions.urlContains("expected-url"));

// Title contains
wait.until(ExpectedConditions.titleContains("expected-title"));

// Alert present
wait.until(ExpectedConditions.alertIsPresent());

// Invisibility
wait.until(ExpectedConditions.invisibilityOfElement(element));

// Staleness (element removed from DOM)
wait.until(ExpectedConditions.stalenessOf(element));
```

### Fluent Wait
```java
Wait<WebDriver> fluentWait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(500))
    .ignoring(NoSuchElementException.class);

fluentWait.until(driver -> driver.findElement(By.id("id")).isDisplayed());
```

---

## 🎯 ChromeDriver Options

```java
ChromeOptions options = new ChromeOptions();

// Headless mode
options.addArguments("--headless");

// Maximize window
options.addArguments("--start-maximized");

// Window size
options.addArguments("--window-size=1920,1080");

// Disable notifications
options.addArguments("--disable-notifications");

// Disable images
options.addArguments("--blink-settings=imagesEnabled=false");

// Disable plugins
options.addArguments("--disable-plugins");

// Disable extensions
options.addArguments("--disable-extensions");

// Accept insecure certificates
options.setAcceptInsecureCerts(true);

// Custom user agent
options.addArguments("user-agent=Custom Agent");

// Disable GPU (faster headless)
options.addArguments("--disable-gpu");

// Set preferences
HashMap<String, Object> prefs = new HashMap<>();
prefs.put("profile.default_content_settings.popups", 0);
options.setExperimentalOption("prefs", prefs);

WebDriver driver = new ChromeDriver(options);
```

---

## 💻 JavaScript Executor

```java
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

// Execute script with return
Long result = (Long) jsExecutor.executeScript("return 1 + 1;");

// Execute script without return
jsExecutor.executeScript("console.log('test');");

// Set element value
jsExecutor.executeScript("arguments[0].value = 'test';", element);

// Click element
jsExecutor.executeScript("arguments[0].click();", element);

// Get element text
String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);

// Scroll to element
jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

// Scroll by pixels
jsExecutor.executeScript("window.scrollBy(0, 500);");

// Remove element
jsExecutor.executeScript("arguments[0].remove();", element);

// Get page title
String title = (String) jsExecutor.executeScript("return document.title;");

// Get page URL
String url = (String) jsExecutor.executeScript("return window.location.href;");

// Highlight element
jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);

// Check visibility
Boolean isVisible = (Boolean) jsExecutor.executeScript(
    "return arguments[0].offsetParent !== null;", element);

// Get computed style
String color = (String) jsExecutor.executeScript(
    "return window.getComputedStyle(arguments[0]).color;", element);
```

---

## 🖐️ Actions (Mouse & Keyboard)

```java
Actions actions = new Actions(driver);

// Move to element
actions.moveToElement(element).perform();

// Click
actions.click(element).perform();

// Double click
actions.doubleClick(element).perform();

// Right click
actions.contextClick(element).perform();

// Click and hold
actions.clickAndHold(element).perform();

// Release
actions.release().perform();

// Drag and drop
actions.dragAndDrop(source, target).perform();

// Drag by offset
actions.moveToElement(element)
       .clickAndHold()
       .moveByOffset(100, 50)
       .release()
       .perform();

// Send keys
actions.sendKeys("text").perform();

// Key down
actions.keyDown(Keys.CONTROL).perform();

// Key up
actions.keyUp(Keys.CONTROL).perform();

// Keyboard shortcut
actions.keyDown(Keys.CONTROL)
       .sendKeys("a")
       .keyUp(Keys.CONTROL)
       .perform();

// Tab
actions.sendKeys(Keys.TAB).perform();

// Enter
actions.sendKeys(Keys.ENTER).perform();

// Escape
actions.sendKeys(Keys.ESCAPE).perform();

// Scroll wheel
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
actions.scroll(scrollOrigin, 0, 500).perform();

// Common keys
Keys.RETURN
Keys.ENTER
Keys.TAB
Keys.ESCAPE
Keys.CONTROL
Keys.SHIFT
Keys.ALT
Keys.DELETE
Keys.BACKSPACE
Keys.ARROW_UP
Keys.ARROW_DOWN
```

---

## 📋 Common Patterns

### Dropdown Selection
```java
// Using Select class
WebElement dropdown = driver.findElement(By.id("dropdown"));
Select select = new Select(dropdown);

// Select by visible text
select.selectByVisibleText("Option 1");

// Select by value
select.selectByValue("option1");

// Select by index
select.selectByIndex(0);

// Get selected option
String selected = select.getFirstSelectedOption().getText();

// Get all options
List<WebElement> options = select.getOptions();
```

### Alert Handling
```java
// Switch to alert
Alert alert = driver.switchTo().alert();

// Get alert text
String text = alert.getText();

// Accept alert
alert.accept();

// Dismiss alert
alert.dismiss();

// Type in prompt
alert.sendKeys("text");
```

### Frame/iFrame Handling
```java
// Switch to frame by index
driver.switchTo().frame(0);

// Switch to frame by name
driver.switchTo().frame("frameName");

// Switch to frame by element
WebElement frameElement = driver.findElement(By.id("frame"));
driver.switchTo().frame(frameElement);

// Switch back to default content
driver.switchTo().defaultContent();
```

### Window/Tab Handling
```java
// Get current window handle
String currentHandle = driver.getWindowHandle();

// Get all window handles
Set<String> allHandles = driver.getWindowHandles();

// Switch to window
for (String handle : allHandles) {
    driver.switchTo().window(handle);
}

// Switch by title
for (String handle : driver.getWindowHandles()) {
    driver.switchTo().window(handle);
    if (driver.getTitle().contains("Expected Title")) {
        break;
    }
}

// Close current window
driver.close();

// Close all windows
driver.quit();
```

### Shadow DOM
```java
// Get shadow root
SearchContext shadowRoot = element.getShadowRoot();

// Find element in shadow
WebElement shadowElement = shadowRoot.findElement(By.tagName("span"));

// Using JavaScript for nested shadow
String script = "return document.querySelector('host').shadowRoot.querySelector('child').shadowRoot.querySelector('span');";
WebElement element = (WebElement) jsExecutor.executeScript(script);
```

---

## 🧪 Test Hooks (TestNG)

```java
@BeforeSuite
public void beforeSuite() {
    // Runs once before all tests
}

@BeforeTest
public void beforeTest() {
    // Runs before each <test> tag in XML
}

@BeforeClass
public void beforeClass() {
    // Runs once before each test class
}

@BeforeMethod
public void beforeMethod() {
    // Runs before each test method
    driver = new ChromeDriver();
}

@Test
public void testMethod() {
    // Test code here
}

@AfterMethod
public void afterMethod() {
    // Runs after each test method
    if (driver != null) {
        driver.quit();
    }
}

@AfterClass
public void afterClass() {
    // Runs once after each test class
}

@AfterTest
public void afterTest() {
    // Runs after each <test> tag in XML
}

@AfterSuite
public void afterSuite() {
    // Runs once after all tests
}
```

---

## 🎨 Page Object Model - Template

```java
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BTN = By.id("login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public LoginPage enterUsername(String username) {
        findElement(USERNAME).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        findElement(LOGIN_BTN).click();
    }

    public void login(String username, String password) {
        enterUsername(username)
            .enterPassword(password);
        clickLogin();
    }
}
```

---

## 🏭 Page Factory - Template

```java
public class LoginPageFactory {
    WebDriver driver;

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement loginButton;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

---

## 🔐 Authentication

```java
// HTTP Basic Auth (URL embedded)
String url = "http://username:password@example.com";
driver.navigate().to(url);

// Form-based login
WebElement username = driver.findElement(By.id("username"));
WebElement password = driver.findElement(By.id("password"));
WebElement loginBtn = driver.findElement(By.id("login"));

username.sendKeys("user");
password.sendKeys("pass");
loginBtn.click();

wait.until(ExpectedConditions.urlContains("dashboard"));
```

---

## 🛠️ Debugging & Troubleshooting

```java
// Take screenshot
File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

// Get page source
String pageSource = driver.getPageSource();

// Get current URL
String currentURL = driver.getCurrentUrl();

// Get page title
String title = driver.getTitle();

// Get element outer HTML
String outerHTML = (String) jsExecutor.executeScript("return arguments[0].outerHTML;", element);

// Highlight element
jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);

// Log element info
System.out.println("Tag: " + element.getTagName());
System.out.println("Text: " + element.getText());
System.out.println("Value: " + element.getAttribute("value"));

// Check element readiness
Boolean isReady = (Boolean) jsExecutor.executeScript(
    "return document.readyState === 'complete';"
);

// Get element count
Long count = (Long) jsExecutor.executeScript(
    "return document.querySelectorAll('div').length;"
);
```

---

## ⚠️ Exception Handling

```java
try {
    WebElement element = driver.findElement(By.id("element"));
    element.click();
} catch (NoSuchElementException e) {
    System.out.println("Element not found");
} catch (StaleElementReferenceException e) {
    System.out.println("Element is stale, refinding...");
    // Re-find the element
} catch (TimeoutException e) {
    System.out.println("Wait timeout");
} catch (ElementNotInteractableException e) {
    System.out.println("Element not interactable");
} catch (Exception e) {
    System.out.println("Unexpected error: " + e.getMessage());
}
```

---

## 📊 Quick Decision Table

| Need | Solution |
|------|----------|
| Find element | `findElement(By)` |
| Wait for element | `WebDriverWait` + `ExpectedConditions` |
| Type text | `sendKeys()` |
| Click | `click()` or `Actions` |
| Hover | `Actions.moveToElement()` |
| Drag & Drop | `Actions.dragAndDrop()` |
| Keyboard shortcut | `Actions.keyDown/keyUp()` |
| Execute JavaScript | `JavascriptExecutor` |
| Work with shadow DOM | `getShadowRoot()` |
| Switch frame | `switchTo().frame()` |
| Switch window | `switchTo().window()` |
| Handle alert | `switchTo().alert()` |
| Select dropdown | `new Select()` |
| Setup/cleanup | `@BeforeMethod/@AfterMethod` |
| Page objects | `POM` or `Page Factory` |

---

## 🚀 Performance Tips

1. Use CSS selectors instead of XPath
2. Disable images in headless mode
3. Avoid Thread.sleep() - use explicit waits
4. Re-use WebDriver instances
5. Close browser properly
6. Use implicit + explicit waits strategically
7. Run tests in parallel when possible
8. Cache frequently used elements (with caution)

---

**Quick Link to Full Documentation:** See `COMPREHENSIVE_GUIDE.md`

