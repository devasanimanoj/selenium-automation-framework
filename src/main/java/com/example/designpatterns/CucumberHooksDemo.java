package com.example.bdd;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * BDD SCENARIO LIFECYCLE HOOKS
 *
 * Place this class in the glue package used by your Cucumber runner.
 * Cucumber creates a new instance for each scenario, so instance fields
 * such as driver belong to one scenario and are not shared accidentally.
 *
 * Lifecycle order:
 * BeforeAll -> Before -> BeforeStep/AfterStep -> After -> AfterAll
 *
 * Important: @After hooks run even when a step fails. This makes them the
 * right place for screenshots and browser cleanup.
 */
public class CucumberHooksDemo {

    private WebDriver driver;

    // Expert-level suite hook: runs once before all scenarios in this JVM.
    @BeforeAll
    public static void beforeAllScenarios() {
        System.out.println("BDD suite started");
        // Load environment configuration or create shared test data here.
    }

    // Beginner-level scenario hook: creates an independent browser per scenario.
    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
    }

    // Intermediate-level tagged hook: runs only for scenarios with @ui.
    @Before(value = "@ui", order = 1)
    public void beforeUiScenario() {
        driver.manage().deleteAllCookies();
        System.out.println("UI scenario setup completed");
    }

    // Runs immediately before every Gherkin step.
    @BeforeStep
    public void beforeStep() {
        System.out.println("About to execute a step");
    }

    // Runs immediately after every Gherkin step.
    @AfterStep
    public void afterStep() {
        System.out.println("Step execution completed");
    }

    // @After runs in reverse order: higher order values run first.
    @After(order = 1)
    public void attachFailureScreenshot(Scenario scenario) {
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure screenshot");
            System.out.println("Failure screenshot attached");
        }
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        System.out.println("Finished scenario: " + scenario.getName()
                + " [" + scenario.getStatus() + "]");

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @AfterAll
    public static void afterAllScenarios() {
        System.out.println("BDD suite finished");
        // Remove shared test data or close suite-level resources here.
    }

    /**
     * Step definitions can access the scenario browser through this method
     * when the hook class is included in their object graph.
     */
    public WebDriver getDriver() {
        return driver;
    }
}
