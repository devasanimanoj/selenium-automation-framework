package com.example.utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ASSERTION UTILITY CLASS
 * 
 * Reusable assertion methods for tests
 * Provides both hard and soft assertions
 * Can be used across all test files
 * 
 * Usage:
 * AssertionUtil.assertEquals(actual, expected, "Error message");
 * AssertionUtil.softAssertEquals(softAssert, actual, expected, "Error message");
 */

public class AssertionUtil {
    
    // ===== HARD ASSERTIONS =====
    
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
    
    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }
    
    public static void assertNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }
    
    public static void assertNotEquals(Object actual, Object expected) {
        Assert.assertNotEquals(actual, expected);
    }
    
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
    
    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }
    
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    
    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }
    
    public static void assertNull(Object object, String message) {
        Assert.assertNull(object, message);
    }
    
    public static void assertNull(Object object) {
        Assert.assertNull(object);
    }
    
    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }
    
    public static void assertNotNull(Object object) {
        Assert.assertNotNull(object);
    }
    
    public static void assertSame(Object actual, Object expected, String message) {
        Assert.assertSame(actual, expected, message);
    }
    
    public static void assertNotSame(Object actual, Object expected, String message) {
        Assert.assertNotSame(actual, expected, message);
    }
    
    public static void fail(String message) {
        Assert.fail(message);
    }
    
    // ===== SOFT ASSERTIONS =====
    
    public static void softAssertEquals(SoftAssert softAssert, Object actual, Object expected, String message) {
        softAssert.assertEquals(actual, expected, message);
    }
    
    public static void softAssertNotEquals(SoftAssert softAssert, Object actual, Object expected, String message) {
        softAssert.assertNotEquals(actual, expected, message);
    }
    
    public static void softAssertTrue(SoftAssert softAssert, boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }
    
    public static void softAssertFalse(SoftAssert softAssert, boolean condition, String message) {
        softAssert.assertFalse(condition, message);
    }
    
    public static void softAssertNull(SoftAssert softAssert, Object object, String message) {
        softAssert.assertNull(object, message);
    }
    
    public static void softAssertNotNull(SoftAssert softAssert, Object object, String message) {
        softAssert.assertNotNull(object, message);
    }
    
    // ===== CUSTOM ASSERTIONS =====
    
    // String assertions
    public static void assertStringContains(String text, String substring, String message) {
        if (text == null || !text.contains(substring)) {
            Assert.fail(message + " | Expected to contain: '" + substring + "' but was: '" + text + "'");
        }
    }
    
    public static void assertStringNotContains(String text, String substring, String message) {
        if (text != null && text.contains(substring)) {
            Assert.fail(message + " | Should not contain: '" + substring + "' but was: '" + text + "'");
        }
    }
    
    public static void assertStringNotEmpty(String text, String message) {
        if (text == null || text.isEmpty()) {
            Assert.fail(message + " | String should not be empty");
        }
    }
    
    public static void assertStringEmpty(String text, String message) {
        if (text != null && !text.isEmpty()) {
            Assert.fail(message + " | String should be empty but was: '" + text + "'");
        }
    }
    
    public static void assertStringEquals(String actual, String expected, boolean ignoreCase, String message) {
        boolean equals = ignoreCase ? 
            actual.equalsIgnoreCase(expected) : 
            actual.equals(expected);
        
        if (!equals) {
            Assert.fail(message + " | Expected: '" + expected + "' but was: '" + actual + "'");
        }
    }
    
    public static void assertStringLength(String text, int expectedLength, String message) {
        int actualLength = text != null ? text.length() : 0;
        Assert.assertEquals(actualLength, expectedLength, 
            message + " | Expected length: " + expectedLength + " but was: " + actualLength);
    }
    
    public static void assertStringLengthInRange(String text, int minLength, int maxLength, String message) {
        int length = text != null ? text.length() : 0;
        if (length < minLength || length > maxLength) {
            Assert.fail(message + " | Length should be between " + minLength + " and " + maxLength + 
                " but was: " + length);
        }
    }
    
    // Numeric assertions
    public static void assertGreaterThan(int actual, int expected, String message) {
        if (actual <= expected) {
            Assert.fail(message + " | Expected > " + expected + " but was: " + actual);
        }
    }
    
    public static void assertLessThan(int actual, int expected, String message) {
        if (actual >= expected) {
            Assert.fail(message + " | Expected < " + expected + " but was: " + actual);
        }
    }
    
    public static void assertGreaterThanOrEqual(int actual, int expected, String message) {
        if (actual < expected) {
            Assert.fail(message + " | Expected >= " + expected + " but was: " + actual);
        }
    }
    
    public static void assertLessThanOrEqual(int actual, int expected, String message) {
        if (actual > expected) {
            Assert.fail(message + " | Expected <= " + expected + " but was: " + actual);
        }
    }
    
    public static void assertInRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            Assert.fail(message + " | Value should be between " + min + " and " + max + 
                " but was: " + value);
        }
    }
    
    public static void assertGreaterThan(double actual, double expected, String message) {
        if (actual <= expected) {
            Assert.fail(message + " | Expected > " + expected + " but was: " + actual);
        }
    }
    
    public static void assertLessThan(double actual, double expected, String message) {
        if (actual >= expected) {
            Assert.fail(message + " | Expected < " + expected + " but was: " + actual);
        }
    }
    
    // List assertions
    public static void assertListContains(List<?> list, Object item, String message) {
        if (list == null || !list.contains(item)) {
            Assert.fail(message + " | List should contain: " + item);
        }
    }
    
    public static void assertListNotContains(List<?> list, Object item, String message) {
        if (list != null && list.contains(item)) {
            Assert.fail(message + " | List should not contain: " + item);
        }
    }
    
    public static void assertListEmpty(List<?> list, String message) {
        if (list != null && !list.isEmpty()) {
            Assert.fail(message + " | List should be empty but has " + list.size() + " items");
        }
    }
    
    public static void assertListNotEmpty(List<?> list, String message) {
        if (list == null || list.isEmpty()) {
            Assert.fail(message + " | List should not be empty");
        }
    }
    
    public static void assertListSize(List<?> list, int expectedSize, String message) {
        Assert.assertEquals(list.size(), expectedSize, 
            message + " | Expected size: " + expectedSize);
    }
    
    public static void assertListSize(List<?> list, int minSize, int maxSize, String message) {
        int actualSize = list != null ? list.size() : 0;
        if (actualSize < minSize || actualSize > maxSize) {
            Assert.fail(message + " | List size should be between " + minSize + " and " + maxSize + 
                " but was: " + actualSize);
        }
    }
    
    // Map assertions
    public static void assertMapContainsKey(Map<?, ?> map, Object key, String message) {
        if (map == null || !map.containsKey(key)) {
            Assert.fail(message + " | Map should contain key: " + key);
        }
    }
    
    public static void assertMapContainsValue(Map<?, ?> map, Object value, String message) {
        if (map == null || !map.containsValue(value)) {
            Assert.fail(message + " | Map should contain value: " + value);
        }
    }
    
    public static void assertMapEmpty(Map<?, ?> map, String message) {
        if (map != null && !map.isEmpty()) {
            Assert.fail(message + " | Map should be empty but has " + map.size() + " items");
        }
    }
    
    public static void assertMapNotEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            Assert.fail(message + " | Map should not be empty");
        }
    }
    
    public static void assertMapSize(Map<?, ?> map, int expectedSize, String message) {
        Assert.assertEquals(map.size(), expectedSize, 
            message + " | Expected size: " + expectedSize);
    }
    
    // Boolean assertions
    public static void assertIsTrue(boolean actual, String message) {
        assertTrue(actual, message);
    }
    
    public static void assertIsFalse(boolean actual, String message) {
        assertFalse(actual, message);
    }
    
    // Object assertions
    public static void assertObjectsEqual(Object obj1, Object obj2, String message) {
        if (!Objects.equals(obj1, obj2)) {
            Assert.fail(message + " | Expected: " + obj2 + " but was: " + obj1);
        }
    }
    
    public static void assertObjectsNotEqual(Object obj1, Object obj2, String message) {
        if (Objects.equals(obj1, obj2)) {
            Assert.fail(message + " | Objects should not be equal: " + obj1);
        }
    }
    
    public static void assertInstanceOf(Object object, Class<?> expectedClass, String message) {
        if (!expectedClass.isInstance(object)) {
            Assert.fail(message + " | Expected instance of " + expectedClass.getName() + 
                " but was: " + object.getClass().getName());
        }
    }
    
    // Array assertions
    public static void assertArrayContains(Object[] array, Object item, String message) {
        boolean found = false;
        for (Object element : array) {
            if (Objects.equals(element, item)) {
                found = true;
                break;
            }
        }
        if (!found) {
            Assert.fail(message + " | Array should contain: " + item);
        }
    }
    
    public static void assertArrayLength(Object[] array, int expectedLength, String message) {
        Assert.assertEquals(array.length, expectedLength, 
            message + " | Expected length: " + expectedLength);
    }
    
    // Email assertion
    public static void assertValidEmail(String email, String message) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !email.matches(emailRegex)) {
            Assert.fail(message + " | Invalid email format: " + email);
        }
    }
    
    // URL assertion
    public static void assertValidURL(String url, String message) {
        try {
            new java.net.URL(url).toURI();
        } catch (Exception e) {
            Assert.fail(message + " | Invalid URL format: " + url);
        }
    }
    
    // Soft assert helper
    public static void softAssertAll(SoftAssert softAssert) {
        softAssert.assertAll();
    }
}
