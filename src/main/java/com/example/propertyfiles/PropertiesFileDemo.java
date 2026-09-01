package com.example.propertyfiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties File Data Reading and Writing
 * 
 * Properties files are used to store configuration data,
 * test environment URLs, credentials, etc.
 * 
 * File Format (key=value):
 * browser=chrome
 * url=https://example.com
 * username=admin
 * password=12345
 * 
 * Advantages:
 * - Easy to read and maintain
 * - Can externalize configuration
 * - Key-value format
 * - No dependencies required
 */
public class PropertiesFileDemo {

    /**
     * Read Properties File
     */
    public static void readPropertiesFile(String filePath) {

        try {
            // Create Properties object
            Properties properties = new Properties();

            // Create FileInputStream
            FileInputStream fis = new FileInputStream(filePath);

            // Load properties file
            properties.load(fis);

            // Read individual properties
            String browser = properties.getProperty("browser");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            System.out.println("Browser: " + browser);
            System.out.println("URL: " + url);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            // Read all properties
            System.out.println("\nAll Properties:");
            properties.forEach((key, value) -> 
                    System.out.println(key + " = " + value)
            );

            fis.close();

        } catch (IOException e) {
            System.out.println("Error reading properties: " + e.getMessage());
        }
    }

    /**
     * Get Specific Property Value
     */
    public static String getProperty(String filePath, String key) {

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);

            String value = properties.getProperty(key);
            fis.close();

            return value;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get Property with Default Value
     */
    public static String getPropertyWithDefault(String filePath, String key, String defaultValue) {

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);

            String value = properties.getProperty(key, defaultValue);
            fis.close();

            return value;

        } catch (IOException e) {
            return defaultValue;
        }
    }

    /**
     * Write Properties to File
     */
    public static void writePropertiesFile(String filePath) {

        try {
            // Create Properties object
            Properties properties = new Properties();

            // Set properties
            properties.setProperty("browser", "chrome");
            properties.setProperty("url", "https://example.com");
            properties.setProperty("username", "admin");
            properties.setProperty("password", "12345");
            properties.setProperty("timeout", "10");
            properties.setProperty("implicitWait", "5");

            // Create FileOutputStream
            FileOutputStream fos = new FileOutputStream(filePath);

            // Store properties with comments
            properties.store(fos, "Selenium Configuration Properties");

            System.out.println("Properties file created: " + filePath);

            fos.close();

        } catch (IOException e) {
            System.out.println("Error writing properties: " + e.getMessage());
        }
    }

    /**
     * Update Property in Existing File
     */
    public static void updateProperty(String filePath, String key, String newValue) {

        try {
            // Load existing properties
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
            fis.close();

            // Update property
            properties.setProperty(key, newValue);

            // Write back to file
            FileOutputStream fos = new FileOutputStream(filePath);
            properties.store(fos, "Updated Configuration");
            fos.close();

            System.out.println("Property '" + key + "' updated to: " + newValue);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Load All Properties into a Configuration Object
     */
    public static class Configuration {

        private String browser;
        private String url;
        private String username;
        private String password;
        private int timeout;
        private int implicitWait;

        public Configuration(String propertiesFilePath) {

            try {
                Properties properties = new Properties();
                FileInputStream fis = new FileInputStream(propertiesFilePath);
                properties.load(fis);

                this.browser = properties.getProperty("browser", "chrome");
                this.url = properties.getProperty("url");
                this.username = properties.getProperty("username");
                this.password = properties.getProperty("password");
                this.timeout = Integer.parseInt(properties.getProperty("timeout", "10"));
                this.implicitWait = Integer.parseInt(properties.getProperty("implicitWait", "5"));

                fis.close();

            } catch (IOException e) {
                System.out.println("Error loading configuration: " + e.getMessage());
            }
        }

        // Getters
        public String getBrowser() { return browser; }
        public String getUrl() { return url; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public int getTimeout() { return timeout; }
        public int getImplicitWait() { return implicitWait; }
    }

    /**
     * Usage Example
     */
    public static void main(String[] args) {

        String propertiesFile = "C:\\config\\config.properties";

        // Write properties
        writePropertiesFile(propertiesFile);

        // Read properties
        readPropertiesFile(propertiesFile);

        // Get specific property
        String browser = getProperty(propertiesFile, "browser");
        System.out.println("\nBrowser: " + browser);

        // Get with default
        String theme = getPropertyWithDefault(propertiesFile, "theme", "dark");
        System.out.println("Theme: " + theme);

        // Update property
        updateProperty(propertiesFile, "timeout", "15");

        // Load into Configuration object
        Configuration config = new Configuration(propertiesFile);
        System.out.println("\nConfiguration Object:");
        System.out.println("Browser: " + config.getBrowser());
        System.out.println("URL: " + config.getUrl());
        System.out.println("Timeout: " + config.getTimeout());
    }
}
