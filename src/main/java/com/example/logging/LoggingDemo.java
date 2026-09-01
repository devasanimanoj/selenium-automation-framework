package com.example.logging;

import java.io.IOException;
import java.util.logging.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LOGGING IN SELENIUM - Comprehensive Guide
 * 
 * What is Logging?
 * - Record test execution details
 * - Track test progress and results
 * - Debug issues and failures
 * - Generate reports
 * 
 * Logging Frameworks:
 * 1. Java Logging (java.util.logging)
 * 2. Log4j
 * 3. SLF4J
 * 4. Logback
 */

public class LoggingDemo {
    
    // Using java.util.logging (Built-in)
    private static final Logger javaLogger = Logger.getLogger(LoggingDemo.class.getName());
    
    // Using Log4j
    private static final Logger log4jLogger = Logger.getLogger(LoggingDemo.class);
    
    static {
        setupJavaLogging();
    }
    
    // ===== JAVA UTIL LOGGING SETUP =====
    static void setupJavaLogging() {
        try {
            // Create file handler
            FileHandler fileHandler = new FileHandler(
                "logs/test-execution.log", true);
            
            // Create console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            
            // Set formatter
            SimpleFormatter formatter = new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    return String.format("[%s] %s - %s%n",
                        record.getLevel(),
                        record.getSourceMethodName(),
                        record.getMessage());
                }
            };
            
            fileHandler.setFormatter(formatter);
            consoleHandler.setFormatter(formatter);
            
            // Add handlers
            javaLogger.addHandler(fileHandler);
            javaLogger.addHandler(consoleHandler);
            
            // Set level
            javaLogger.setLevel(Level.ALL);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ===== LOG4J SETUP =====
    static void setupLog4j() {
        try {
            PropertyConfigurator.configure("src/main/resources/log4j.properties");
            System.out.println("✓ Log4j configured");
        } catch (Exception e) {
            System.out.println("✗ Log4j configuration failed: " + e.getMessage());
        }
    }
    
    // ===== LOGGING WITH JAVA UTIL LOGGING =====
    static void javaUtilLogging() {
        System.out.println("\n===== JAVA UTIL LOGGING =====");
        
        // Different log levels
        javaLogger.log(Level.SEVERE, "Error message - Critical issue");
        javaLogger.log(Level.WARNING, "Warning message - Potential issue");
        javaLogger.log(Level.INFO, "Info message - General information");
        javaLogger.log(Level.CONFIG, "Config message - Configuration info");
        javaLogger.log(Level.FINE, "Fine message - Detailed debugging");
        javaLogger.log(Level.FINER, "Finer message - Very detailed");
        javaLogger.log(Level.FINEST, "Finest message - Most detailed");
        
        // Using convenience methods
        javaLogger.severe("Critical error occurred");
        javaLogger.warning("Warning: something unexpected");
        javaLogger.info("Test started successfully");
        
        System.out.println("✓ Java Util Logging demonstration completed");
    }
    
    // ===== LOGGING WITH LOG4J =====
    static void log4jLogging() {
        System.out.println("\n===== LOG4J LOGGING =====");
        
        // Different log levels
        log4jLogger.debug("Debug message - detailed info");
        log4jLogger.info("Info message - general info");
        log4jLogger.warn("Warning message - potential issue");
        log4jLogger.error("Error message - something failed");
        log4jLogger.fatal("Fatal message - critical failure");
        
        System.out.println("✓ Log4j logging demonstration completed");
    }
    
    // ===== CUSTOM LOGGER CLASS =====
    static class TestLogger {
        private Logger logger = Logger.getLogger(TestLogger.class.getName());
        private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        public void startTest(String testName) {
            String message = String.format("🔧 TEST STARTED: %s [%s]", 
                testName, LocalDateTime.now().format(dateFormatter));
            logger.info(message);
        }
        
        public void endTest(String testName, boolean passed) {
            String status = passed ? "✅ PASSED" : "❌ FAILED";
            String message = String.format("%s TEST: %s [%s]", 
                status, testName, LocalDateTime.now().format(dateFormatter));
            logger.info(message);
        }
        
        public void step(String stepDescription) {
            String message = String.format("↳ STEP: %s", stepDescription);
            logger.info(message);
        }
        
        public void logAction(String action, String details) {
            String message = String.format("→ ACTION: %s | Details: %s", action, details);
            logger.info(message);
        }
        
        public void logVerification(String verification, boolean result) {
            String status = result ? "✓ PASS" : "✗ FAIL";
            String message = String.format("⊘ VERIFY: %s | %s", verification, status);
            logger.info(message);
        }
        
        public void logError(String error, Exception e) {
            logger.severe("ERROR: " + error);
            if (e != null) {
                logger.severe("Exception: " + e.getMessage());
            }
        }
        
        public void logWarning(String warning) {
            logger.warning("WARNING: " + warning);
        }
        
        public void logDebug(String debug) {
            logger.fine("DEBUG: " + debug);
        }
    }
    
    // ===== LOGGING UTILITY CLASS =====
    static class LoggerUtil {
        private static Logger logger = Logger.getLogger(LoggerUtil.class.getName());
        
        static {
            try {
                FileHandler fileHandler = new FileHandler("logs/automation.log", true);
                ConsoleHandler consoleHandler = new ConsoleHandler();
                
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                consoleHandler.setFormatter(formatter);
                
                logger.addHandler(fileHandler);
                logger.addHandler(consoleHandler);
                logger.setLevel(Level.ALL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Test lifecycle logs
        public static void testStart(String testName) {
            logger.info("══════════════════════════════════════════");
            logger.info("TEST STARTED: " + testName);
            logger.info("══════════════════════════════════════════");
        }
        
        public static void testEnd(String testName, boolean passed) {
            String status = passed ? "PASSED" : "FAILED";
            logger.info("TEST RESULT: " + testName + " - " + status);
            logger.info("══════════════════════════════════════════");
        }
        
        public static void testSkipped(String testName, String reason) {
            logger.info("TEST SKIPPED: " + testName);
            logger.info("Reason: " + reason);
        }
        
        // Step and action logs
        public static void step(String stepNumber, String stepDescription) {
            logger.info("STEP " + stepNumber + ": " + stepDescription);
        }
        
        public static void action(String action) {
            logger.info("→ " + action);
        }
        
        public static void assertion(String assertion) {
            logger.info("✓ ASSERT: " + assertion);
        }
        
        // Level-based logs
        public static void info(String message) {
            logger.info(message);
        }
        
        public static void debug(String message) {
            logger.fine(message);
        }
        
        public static void warning(String message) {
            logger.warning(message);
        }
        
        public static void error(String message) {
            logger.severe(message);
        }
        
        public static void exception(Exception e) {
            logger.severe("Exception: " + e.getClass().getName());
            logger.severe("Message: " + e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                logger.severe("  at " + element);
            }
        }
        
        // Data logging
        public static void logData(String label, Object value) {
            logger.info(label + " = " + value);
        }
        
        public static void logMap(java.util.Map<?, ?> map) {
            logger.info("MAP DATA:");
            for (java.util.Map.Entry<?, ?> entry : map.entrySet()) {
                logger.info("  " + entry.getKey() + " : " + entry.getValue());
            }
        }
        
        public static void logList(java.util.List<?> list, String label) {
            logger.info("LIST: " + label);
            for (int i = 0; i < list.size(); i++) {
                logger.info("  [" + i + "] " + list.get(i));
            }
        }
    }
    
    // ===== LOG4J.PROPERTIES CONFIGURATION =====
    static void log4jConfiguration() {
        System.out.println("\n===== LOG4J CONFIGURATION =====");
        System.out.println("Create log4j.properties file:");
        System.out.println("""
            # Root logger option
            log4j.rootLogger=INFO, file, console
            
            # File appender
            log4j.appender.file=org.apache.log4j.FileAppender
            log4j.appender.file.File=logs/test.log
            log4j.appender.file.layout=org.apache.log4j.PatternLayout
            log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} - %c{1} - %p - %m%n
            log4j.appender.file.Append=true
            
            # Console appender
            log4j.appender.console=org.apache.log4j.ConsoleAppender
            log4j.appender.console.layout=org.apache.log4j.PatternLayout
            log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %c{1} - %m%n
            
            # Specific loggers
            log4j.logger.com.example=DEBUG
            log4j.logger.org.openqa.selenium=WARN
            """);
    }
    
    // ===== DEMONSTRATION OF CUSTOM LOGGER =====
    static void demonstrateCustomLogger() {
        System.out.println("\n===== CUSTOM LOGGER DEMONSTRATION =====");
        
        TestLogger testLogger = new TestLogger();
        
        // Simulate test execution
        testLogger.startTest("testLoginFunctionality");
        
        testLogger.step("Navigate to login page");
        testLogger.logAction("Navigate", "https://example.com/login");
        
        testLogger.step("Enter username");
        testLogger.logAction("sendKeys", "username field = 'testuser'");
        
        testLogger.step("Enter password");
        testLogger.logAction("sendKeys", "password field = '****'");
        
        testLogger.step("Click login button");
        testLogger.logAction("click", "Login button");
        
        testLogger.step("Verify redirect");
        testLogger.logVerification("Dashboard URL contains 'dashboard'", true);
        
        testLogger.endTest("testLoginFunctionality", true);
    }
    
    // ===== LOGGING BEST PRACTICES =====
    static void bestPractices() {
        System.out.println("\n===== LOGGING BEST PRACTICES =====");
        System.out.println("1. LOG LEVELS:");
        System.out.println("   - DEBUG: Detailed debugging information");
        System.out.println("   - INFO: General information about test flow");
        System.out.println("   - WARNING: Warnings about potential issues");
        System.out.println("   - ERROR/SEVERE: Errors and failures");
        System.out.println("");
        System.out.println("2. WHAT TO LOG:");
        System.out.println("   ✓ Test start/end with timestamp");
        System.out.println("   ✓ Each step in test execution");
        System.out.println("   ✓ Actions taken (click, sendKeys, etc.)");
        System.out.println("   ✓ Verifications and assertions");
        System.out.println("   ✓ Expected vs actual values");
        System.out.println("   ✓ Exceptions and error messages");
        System.out.println("   ✓ Screenshots locations");
        System.out.println("   ✗ Sensitive data (passwords, tokens)");
        System.out.println("");
        System.out.println("3. STRUCTURED LOGGING:");
        System.out.println("   - Use consistent message format");
        System.out.println("   - Include timestamps");
        System.out.println("   - Use clear prefixes (→, ✓, ✗, etc.)");
        System.out.println("   - Include context information");
        System.out.println("");
        System.out.println("4. FILE MANAGEMENT:");
        System.out.println("   - Store logs in 'logs' directory");
        System.out.println("   - Create separate log for each run");
        System.out.println("   - Include timestamp in log file name");
        System.out.println("   - Archive old logs");
        System.out.println("");
        System.out.println("5. PERFORMANCE:");
        System.out.println("   - Don't log sensitive information");
        System.out.println("   - Use appropriate log levels");
        System.out.println("   - Don't log inside loops");
        System.out.println("   - Use lazy evaluation");
    }
    
    public static void main(String[] args) {
        try {
            javaUtilLogging();
            demonstrateCustomLogger();
            LoggerUtil.testStart("DemoTest");
            LoggerUtil.step("1", "Initialize browser");
            LoggerUtil.action("Create ChromeDriver");
            LoggerUtil.step("2", "Navigate to application");
            LoggerUtil.action("Navigate to https://example.com");
            LoggerUtil.assertion("Page title contains expected text");
            LoggerUtil.testEnd("DemoTest", true);
            bestPractices();
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
