package com.example.assertions;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.util.Objects;

/**
 * TESTNG ASSERTIONS - Comprehensive Guide
 * 
 * What are Assertions?
 * - Verify expected vs actual results
 * - Fail test if assertion fails
 * - Core of test validation
 * 
 * Types of Assertions:
 * 1. Hard Assertions - Stop execution on first failure
 * 2. Soft Assertions - Continue execution, report all failures
 * 3. Custom Assertions - Create domain-specific assertions
 */

public class AssertionsDemo {
    
    // ===== HARD ASSERTIONS (Stop on first failure) =====
    static void hardAssertions() {
        System.out.println("\n===== HARD ASSERTIONS =====");
        
        // 1. Assert.assertTrue()
        boolean isValid = true;
        Assert.assertTrue(isValid, "Value should be true");
        System.out.println("✓ assertTrue passed");
        
        // 2. Assert.assertFalse()
        boolean isInvalid = false;
        Assert.assertFalse(isInvalid, "Value should be false");
        System.out.println("✓ assertFalse passed");
        
        // 3. Assert.assertEquals()
        String actualValue = "Selenium";
        String expectedValue = "Selenium";
        Assert.assertEquals(actualValue, expectedValue, "Values should match");
        System.out.println("✓ assertEquals passed");
        
        // 4. Assert.assertNotEquals()
        String value1 = "Test1";
        String value2 = "Test2";
        Assert.assertNotEquals(value1, value2, "Values should not match");
        System.out.println("✓ assertNotEquals passed");
        
        // 5. Assert.assertNull()
        String nullValue = null;
        Assert.assertNull(nullValue, "Value should be null");
        System.out.println("✓ assertNull passed");
        
        // 6. Assert.assertNotNull()
        String notNullValue = "Value";
        Assert.assertNotNull(notNullValue, "Value should not be null");
        System.out.println("✓ assertNotNull passed");
        
        // 7. Assert.assertSame()
        String str1 = new String("Test");
        String str2 = str1;
        Assert.assertSame(str1, str2, "Objects should be same instance");
        System.out.println("✓ assertSame passed");
        
        // 8. Assert.assertNotSame()
        String str3 = new String("Test");
        String str4 = new String("Test");
        Assert.assertNotSame(str3, str4, "Objects should not be same instance");
        System.out.println("✓ assertNotSame passed");
        
        // 9. Assert.fail()
        // Assert.fail("This test failed intentionally");
        
        // 10. Numeric comparisons
        int actualNumber = 10;
        int expectedNumber = 10;
        Assert.assertEquals(actualNumber, expectedNumber, "Numbers should match");
        System.out.println("✓ assertEquals (numeric) passed");
    }
    
    // ===== SOFT ASSERTIONS (Continue on failure) =====
    static void softAssertions() {
        System.out.println("\n===== SOFT ASSERTIONS =====");
        
        SoftAssert softAssert = new SoftAssert();
        
        // Multiple assertions without stopping
        softAssert.assertEquals("Test", "Test", "First assertion");
        System.out.println("  - First assertion executed");
        
        softAssert.assertTrue(5 > 3, "Second assertion");
        System.out.println("  - Second assertion executed");
        
        softAssert.assertFalse(5 < 3, "Third assertion");
        System.out.println("  - Third assertion executed");
        
        softAssert.assertNull(null, "Fourth assertion");
        System.out.println("  - Fourth assertion executed");
        
        // This will fail - demonstrate soft assertion continues
        softAssert.assertEquals("Expected", "Actual", "Fifth assertion - This fails");
        System.out.println("  - Fifth assertion executed (even though it failed)");
        
        softAssert.assertNotNull("Value", "Sixth assertion");
        System.out.println("  - Sixth assertion executed");
        
        // Report all failures at once
        try {
            softAssert.assertAll();
            System.out.println("✓ All soft assertions passed");
        } catch (AssertionError e) {
            System.out.println("⚠ Soft assertions failed (but continued execution)");
            System.out.println("  Error: " + e.getMessage());
        }
    }
    
    // ===== SOFT ASSERTIONS - MULTIPLE FAILURES =====
    static void softAssertionsMultipleFailures() {
        System.out.println("\n===== SOFT ASSERTIONS - Multiple Failures =====");
        
        SoftAssert softAssert = new SoftAssert();
        
        // Simulate form validation failures
        softAssert.assertEquals("John", "Jane", "First name mismatch");
        softAssert.assertEquals(25, 30, "Age mismatch");
        softAssert.assertEquals("john@example.com", "jane@example.com", "Email mismatch");
        softAssert.assertEquals("USA", "UK", "Country mismatch");
        softAssert.assertEquals("Active", "Inactive", "Status mismatch");
        
        System.out.println("  - All assertions executed despite failures");
        
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("⚠ Multiple assertion failures collected:");
            String[] errors = e.getMessage().split("\n");
            for (int i = 0; i < errors.length && i < 5; i++) {
                System.out.println("    " + errors[i]);
            }
        }
    }
    
    // ===== CUSTOM ASSERTIONS =====
    static void customAssertions() {
        System.out.println("\n===== CUSTOM ASSERTIONS =====");
        
        // Custom assertion for email validation
        String email = "test@example.com";
        assertTrue(isValidEmail(email), "Email format is invalid");
        System.out.println("✓ Email assertion passed");
        
        // Custom assertion for phone number
        String phone = "123-456-7890";
        assertTrue(isValidPhoneNumber(phone), "Phone format is invalid");
        System.out.println("✓ Phone assertion passed");
        
        // Custom assertion for URL
        String url = "https://www.example.com";
        assertTrue(isValidURL(url), "URL format is invalid");
        System.out.println("✓ URL assertion passed");
        
        // Custom assertion for strong password
        String password = "SecurePass123@";
        assertTrue(isStrongPassword(password), "Password is not strong enough");
        System.out.println("✓ Password assertion passed");
        
        // Custom assertion for range
        int value = 50;
        assertInRange(value, 0, 100, "Value should be between 0 and 100");
        System.out.println("✓ Range assertion passed");
        
        // Custom assertion for list contains
        java.util.List<String> items = java.util.Arrays.asList("Apple", "Banana", "Orange");
        assertListContains(items, "Banana", "List should contain Banana");
        System.out.println("✓ List contains assertion passed");
    }
    
    // ===== CUSTOM ASSERTION METHODS =====
    
    static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
    
    static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    
    static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
    
    static void assertInRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            Assert.fail(message + " (Value: " + value + ", Expected: " + min + "-" + max + ")");
        }
    }
    
    static void assertListContains(java.util.List<?> list, Object item, String message) {
        if (!list.contains(item)) {
            Assert.fail(message + " (Actual list: " + list + ")");
        }
    }
    
    static void assertStringContains(String text, String substring, String message) {
        if (!text.contains(substring)) {
            Assert.fail(message + " (Text: " + text + ", Expected to contain: " + substring + ")");
        }
    }
    
    static void assertStringNotEmpty(String text, String message) {
        if (text == null || text.isEmpty()) {
            Assert.fail(message + " (String is empty or null)");
        }
    }
    
    static void assertLengthInRange(String text, int minLength, int maxLength, String message) {
        int length = text != null ? text.length() : 0;
        if (length < minLength || length > maxLength) {
            Assert.fail(message + " (Length: " + length + ", Expected: " + minLength + "-" + maxLength + ")");
        }
    }
    
    // ===== VALIDATION HELPER METHODS =====
    
    static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^[0-9\\-+()\\s]+$") && phone.length() >= 10;
    }
    
    static boolean isValidURL(String url) {
        try {
            new java.net.URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    static boolean isStrongPassword(String password) {
        return password != null && password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&  // Has uppercase
               password.matches(".*[a-z].*") &&  // Has lowercase
               password.matches(".*[0-9].*") &&  // Has digit
               password.matches(".*[!@#$%^&*()].*");  // Has special char
    }
    
    // ===== SOFT ASSERT WRAPPER CLASS =====
    static class SoftAssertWrapper {
        private SoftAssert softAssert;
        
        public SoftAssertWrapper() {
            this.softAssert = new SoftAssert();
        }
        
        public void assertEquals(Object actual, Object expected, String message) {
            softAssert.assertEquals(actual, expected, message);
        }
        
        public void assertTrue(boolean condition, String message) {
            softAssert.assertTrue(condition, message);
        }
        
        public void assertFalse(boolean condition, String message) {
            softAssert.assertFalse(condition, message);
        }
        
        public void assertNotNull(Object object, String message) {
            softAssert.assertNotNull(object, message);
        }
        
        public void assertNull(Object object, String message) {
            softAssert.assertNull(object, message);
        }
        
        public void assertInRange(int value, int min, int max, String message) {
            softAssert.assertTrue(value >= min && value <= max, 
                message + " (Value: " + value + ", Expected: " + min + "-" + max + ")");
        }
        
        public void assertAll() {
            softAssert.assertAll();
        }
    }
    
    // ===== COMPARISON HELPERS =====
    
    static void assertObjectsEqual(Object obj1, Object obj2, String message) {
        if (!Objects.equals(obj1, obj2)) {
            Assert.fail(message + " (Expected: " + obj2 + ", Actual: " + obj1 + ")");
        }
    }
    
    static void assertArraysEqual(Object[] arr1, Object[] arr2, String message) {
        Assert.assertEquals(arr1, arr2, message);
    }
    
    static void assertMapContains(java.util.Map<?, ?> map, Object key, String message) {
        if (!map.containsKey(key)) {
            Assert.fail(message + " (Map keys: " + map.keySet() + ")");
        }
    }
    
    // ===== NUMERIC ASSERTIONS =====
    
    static void assertGreaterThan(int actual, int expected, String message) {
        if (actual <= expected) {
            Assert.fail(message + " (Expected > " + expected + ", but got " + actual + ")");
        }
    }
    
    static void assertLessThan(int actual, int expected, String message) {
        if (actual >= expected) {
            Assert.fail(message + " (Expected < " + expected + ", but got " + actual + ")");
        }
    }
    
    static void assertGreaterThanOrEqual(int actual, int expected, String message) {
        if (actual < expected) {
            Assert.fail(message + " (Expected >= " + expected + ", but got " + actual + ")");
        }
    }
    
    static void assertLessThanOrEqual(int actual, int expected, String message) {
        if (actual > expected) {
            Assert.fail(message + " (Expected <= " + expected + ", but got " + actual + ")");
        }
    }
    
    // ===== BEST PRACTICES =====
    
    static void bestPractices() {
        System.out.println("\n===== ASSERTIONS BEST PRACTICES =====");
        System.out.println("1. USE HARD ASSERTIONS:");
        System.out.println("   - For critical validations where test must stop");
        System.out.println("   - When subsequent steps depend on this assertion");
        System.out.println("");
        System.out.println("2. USE SOFT ASSERTIONS:");
        System.out.println("   - For form field validations");
        System.out.println("   - For multiple UI checks (buttons, labels, etc.)");
        System.out.println("   - When you want to report all failures at once");
        System.out.println("   - Always call assertAll() at the end");
        System.out.println("");
        System.out.println("3. ASSERTION MESSAGES:");
        System.out.println("   - Always provide meaningful messages");
        System.out.println("   - Include expected and actual values");
        System.out.println("   - Message should help debugging");
        System.out.println("");
        System.out.println("4. CUSTOM ASSERTIONS:");
        System.out.println("   - Create for domain-specific validations");
        System.out.println("   - Make assertions reusable");
        System.out.println("   - Follow naming convention: assert[What]");
        System.out.println("");
        System.out.println("5. ASSERTION ORDER:");
        System.out.println("   - Validate prerequisites first");
        System.out.println("   - Validate main functionality");
        System.out.println("   - Validate post-conditions");
        System.out.println("");
        System.out.println("6. AVOID:");
        System.out.println("   - Don't assert on implementation details");
        System.out.println("   - Don't use assertions for performance checks");
        System.out.println("   - Don't have tests with no assertions");
        System.out.println("   - Don't ignore assertion failures");
    }
    
    public static void main(String[] args) {
        try {
            hardAssertions();
            softAssertions();
            softAssertionsMultipleFailures();
            customAssertions();
            bestPractices();
        } catch (AssertionError e) {
            System.out.println("\n❌ Test failed: " + e.getMessage());
        }
    }
}
