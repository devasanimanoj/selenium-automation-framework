package com.example.utils;

import java.io.IOException;
import java.util.logging.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LOGGING UTILITY CLASS
 * 
 * Centralized logging for all tests
 * Provides structured logging with timestamps
 * Easy to use and configure
 * 
 * Usage:
 * LoggerUtil.testStart("testName");
 * LoggerUtil.step("Step description");
 * LoggerUtil.action("Action performed");
 * LoggerUtil.assertion("Assertion verified");
 * LoggerUtil.testEnd("testName", true);
 */

public class LoggerUtil {
    
    private static Logger logger = Logger.getLogger(LoggerUtil.class.getName());
    private static final DateTimeFormatter dateFormatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    static {
        initializeLogger();
    }
    
    /**
     * Initialize logger with file and console handlers
     */
    static void initializeLogger() {
        try {
            // Create logs directory
            new java.io.File("logs").mkdirs();
            
            // File handler
            FileHandler fileHandler = new FileHandler("logs/test-execution.log", true);
            fileHandler.setLevel(Level.ALL);
            
            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            
            // Formatter
            Formatter formatter = new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    return String.format("[%s] [%s] %s%n",
                        LocalDateTime.now().format(dateFormatter),
                        record.getLevel(),
                        record.getMessage());
                }
            };
            
            fileHandler.setFormatter(formatter);
            consoleHandler.setFormatter(formatter);
            
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ===== TEST LIFECYCLE LOGGING =====
    
    /**
     * Log test start
     */
    public static void testStart(String testName) {
        logger.info("══════════════════════════════════════════════════════════════════");
        logger.info("TEST STARTED: " + testName);
        logger.info("══════════════════════════════════════════════════════════════════");
    }
    
    /**
     * Log test end with result
     */
    public static void testEnd(String testName, boolean passed) {
        String status = passed ? "✅ PASSED" : "❌ FAILED";
        logger.info("TEST RESULT: " + testName + " - " + status);
        logger.info("══════════════════════════════════════════════════════════════════");
    }
    
    /**
     * Log test skip
     */
    public static void testSkipped(String testName, String reason) {
        logger.info("⏭️  TEST SKIPPED: " + testName);
        logger.info("Reason: " + reason);
    }
    
    /**
     * Log test in progress
     */
    public static void testInProgress(String testName) {
        logger.info("🔄 TEST IN PROGRESS: " + testName);
    }
    
    // ===== STEP LOGGING =====
    
    /**
     * Log step with number
     */
    public static void step(String stepNumber, String stepDescription) {
        logger.info("STEP [" + stepNumber + "]: " + stepDescription);
    }
    
    /**
     * Log step without number
     */
    public static void step(String stepDescription) {
        logger.info("STEP: " + stepDescription);
    }
    
    // ===== ACTION LOGGING =====
    
    /**
     * Log action with details
     */
    public static void action(String action, String details) {
        logger.info("→ ACTION: " + action + " | Details: " + details);
    }
    
    /**
     * Log action without details
     */
    public static void action(String action) {
        logger.info("→ ACTION: " + action);
    }
    
    /**
     * Log click action
     */
    public static void clickAction(String element) {
        logger.info("→ CLICK: " + element);
    }
    
    /**
     * Log text entry action
     */
    public static void sendKeysAction(String field, String value) {
        logger.info("→ SENDKEYS: " + field + " = [value entered]");
    }
    
    /**
     * Log navigation action
     */
    public static void navigateAction(String url) {
        logger.info("→ NAVIGATE: " + url);
    }
    
    /**
     * Log wait action
     */
    public static void waitAction(String element, long seconds) {
        logger.info("→ WAIT: Waiting for " + element + " (" + seconds + "s)");
    }
    
    // ===== ASSERTION LOGGING =====
    
    /**
     * Log assertion
     */
    public static void assertion(String assertion) {
        logger.info("✓ ASSERT: " + assertion);
    }
    
    /**
     * Log assertion with expected and actual
     */
    public static void assertion(String name, Object expected, Object actual) {
        logger.info("✓ ASSERT: " + name + " | Expected: " + expected + " | Actual: " + actual);
    }
    
    /**
     * Log failed assertion
     */
    public static void assertionFailed(String assertion) {
        logger.severe("✗ ASSERT FAILED: " + assertion);
    }
    
    /**
     * Log verification
     */
    public static void verification(String verification, boolean result) {
        String status = result ? "✓ PASS" : "✗ FAIL";
        logger.info("⊘ VERIFY: " + verification + " | " + status);
    }
    
    // ===== DATA LOGGING =====
    
    /**
     * Log key-value pair
     */
    public static void data(String label, Object value) {
        logger.info("📊 DATA: " + label + " = " + value);
    }
    
    /**
     * Log list
     */
    public static void list(String label, java.util.List<?> items) {
        logger.info("📋 LIST: " + label);
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                logger.info("  [" + (i + 1) + "] " + items.get(i));
            }
        }
    }
    
    /**
     * Log map
     */
    public static void map(String label, java.util.Map<?, ?> data) {
        logger.info("🗺️  MAP: " + label);
        if (data != null) {
            for (java.util.Map.Entry<?, ?> entry : data.entrySet()) {
                logger.info("  " + entry.getKey() + " : " + entry.getValue());
            }
        }
    }
    
    // ===== ERROR LOGGING =====
    
    /**
     * Log error
     */
    public static void error(String message) {
        logger.severe("❌ ERROR: " + message);
    }
    
    /**
     * Log exception
     */
    public static void exception(Exception e) {
        logger.severe("❌ EXCEPTION: " + e.getClass().getSimpleName());
        logger.severe("Message: " + e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            logger.severe("  at " + element);
        }
    }
    
    /**
     * Log exception with custom message
     */
    public static void exception(String message, Exception e) {
        logger.severe("❌ EXCEPTION: " + message);
        logger.severe("Type: " + e.getClass().getSimpleName());
        logger.severe("Error: " + e.getMessage());
    }
    
    // ===== WARNING LOGGING =====
    
    /**
     * Log warning
     */
    public static void warning(String message) {
        logger.warning("⚠️  WARNING: " + message);
    }
    
    /**
     * Log warning with details
     */
    public static void warning(String message, String details) {
        logger.warning("⚠️  WARNING: " + message + " | " + details);
    }
    
    // ===== INFO LOGGING =====
    
    /**
     * Log info message
     */
    public static void info(String message) {
        logger.info("ℹ️  INFO: " + message);
    }
    
    /**
     * Log debug message
     */
    public static void debug(String message) {
        logger.fine("🔍 DEBUG: " + message);
    }
    
    /**
     * Log important message
     */
    public static void important(String message) {
        logger.info("⭐ IMPORTANT: " + message);
    }
    
    /**
     * Log success message
     */
    public static void success(String message) {
        logger.info("✅ SUCCESS: " + message);
    }
    
    // ===== SCREENSHOT LOGGING =====
    
    /**
     * Log screenshot capture
     */
    public static void screenshot(String screenshotName, String description) {
        logger.info("📸 SCREENSHOT: " + screenshotName);
        if (description != null && !description.isEmpty()) {
            logger.info("   Description: " + description);
        }
    }
    
    /**
     * Log screenshot on failure
     */
    public static void screenshotOnFailure(String screenshotName, String failureReason) {
        logger.severe("📸 SCREENSHOT (FAILURE): " + screenshotName);
        logger.severe("   Reason: " + failureReason);
    }
    
    // ===== SECTION LOGGING =====
    
    /**
     * Log section header
     */
    public static void section(String sectionName) {
        logger.info("");
        logger.info("═══════════════════════════════════════════");
        logger.info("  " + sectionName);
        logger.info("═══════════════════════════════════════════");
    }
    
    /**
     * Log subsection
     */
    public static void subsection(String subsectionName) {
        logger.info("");
        logger.info("───────────────────────────────────────────");
        logger.info("  " + subsectionName);
        logger.info("───────────────────────────────────────────");
    }
    
    // ===== PERFORMANCE LOGGING =====
    
    /**
     * Log performance measurement
     */
    public static void performance(String operation, long milliseconds) {
        logger.info("⏱️  PERFORMANCE: " + operation + " took " + milliseconds + "ms");
    }
    
    /**
     * Log slow operation
     */
    public static void slowOperation(String operation, long milliseconds, long threshold) {
        if (milliseconds > threshold) {
            logger.warning("🐢 SLOW OPERATION: " + operation + " took " + milliseconds + "ms (threshold: " + threshold + "ms)");
        }
    }
    
    // ===== SUMMARY LOGGING =====
    
    /**
     * Log test summary
     */
    public static void summary(int total, int passed, int failed, int skipped) {
        logger.info("");
        logger.info("═══════════════════════════════════════════");
        logger.info("  TEST SUMMARY");
        logger.info("═══════════════════════════════════════════");
        logger.info("Total Tests: " + total);
        logger.info("Passed: " + passed);
        logger.info("Failed: " + failed);
        logger.info("Skipped: " + skipped);
        logger.info("Success Rate: " + (total > 0 ? (passed * 100 / total) : 0) + "%");
        logger.info("═══════════════════════════════════════════");
    }
    
    /**
     * Get logger instance (for custom configurations)
     */
    public static Logger getLogger() {
        return logger;
    }
}
