package com.example.filehandling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

/**
 * File Upload and Download Handling
 * 
 * File Upload:
 * - Locate file input element
 * - Send file path using sendKeys()
 * 
 * File Download:
 * - Configure ChromeOptions to set download directory
 * - Click download link
 * - Verify file exists
 */
public class FileUploadDownloadDemo {

    public static void main(String[] args) {

        // ===== FILE UPLOAD =====
        fileUploadDemo();

        // ===== FILE DOWNLOAD =====
        fileDownloadDemo();
    }

    /**
     * File Upload Handling
     * 
     * HTML Example:
     * <input type="file" id="fileInput">
     * <button id="uploadBtn">Upload</button>
     */
    public static void fileUploadDemo() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://example.com");

        try {
            // Method 1: Simple File Upload
            uploadFile(driver, "C:\\Users\\varsh\\test.txt");

            // Method 2: Upload Multiple Files
            uploadMultipleFiles(driver, new String[]{
                    "C:\\Users\\varsh\\file1.txt",
                    "C:\\Users\\varsh\\file2.txt"
            });

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        driver.quit();
    }

    /**
     * Upload Single File
     */
    public static void uploadFile(WebDriver driver, String filePath) {

        try {
            // Locate file input element
            WebElement fileInput = driver.findElement(By.id("fileInput"));

            // Check if file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            // Send file path using sendKeys()
            // Note: sendKeys() works for input[type="file"] elements
            fileInput.sendKeys(file.getAbsolutePath());

            System.out.println("File uploaded: " + filePath);

            // Click upload button (if exists)
            try {
                WebElement uploadBtn = driver.findElement(By.id("uploadBtn"));
                uploadBtn.click();
                System.out.println("Upload button clicked");
            } catch (Exception e) {
                System.out.println("No upload button found");
            }

        } catch (Exception e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    /**
     * Upload Multiple Files
     * Note: Requires input[type="file" multiple]
     */
    public static void uploadMultipleFiles(WebDriver driver, String[] filePaths) {

        try {
            // Locate file input
            WebElement fileInput = driver.findElement(By.id("fileInput"));

            // Combine file paths
            String combinedPath = String.join("\n", filePaths);

            // Send all file paths
            fileInput.sendKeys(combinedPath);

            System.out.println("Uploaded " + filePaths.length + " files");

        } catch (Exception e) {
            System.out.println("Error uploading multiple files: " + e.getMessage());
        }
    }

    /**
     * File Download Handling
     * 
     * Steps:
     * 1. Configure ChromeOptions with download directory
     * 2. Click download link
     * 3. Verify file is downloaded
     */
    public static void fileDownloadDemo() {

        // Set download directory
        String downloadDir = "C:\\Users\\varsh\\Downloads";

        // Configure ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + downloadDir);

        // Set download preferences
        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        prefs.put("download.prompt_for_download", false);
        prefs.put("plugins.always_open_pdf_externally", true);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://example.com");

        try {
            // Click download link
            downloadFile(driver, "C:\\Users\\varsh\\Downloads", "document.pdf");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        driver.quit();
    }

    /**
     * Download File and Verify
     */
    public static void downloadFile(WebDriver driver, String downloadDir, String expectedFileName) {

        try {
            // Click download link/button
            WebElement downloadLink = driver.findElement(By.linkText("Download"));
            downloadLink.click();

            System.out.println("Download started");

            // Wait for file to download (wait up to 30 seconds)
            boolean fileDownloaded = waitForFileDownload(downloadDir, expectedFileName, 30);

            if (fileDownloaded) {
                System.out.println("File downloaded successfully: " + expectedFileName);

                // Verify file path
                File downloadedFile = new File(downloadDir + File.separator + expectedFileName);
                System.out.println("File path: " + downloadedFile.getAbsolutePath());
                System.out.println("File size: " + downloadedFile.length() + " bytes");

            } else {
                System.out.println("File download failed or timeout");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Wait for File Download
     * Polls the download directory until file appears
     */
    public static boolean waitForFileDownload(String downloadDir, String fileName, int maxWaitSeconds) {

        File directory = new File(downloadDir);
        long startTime = System.currentTimeMillis();
        long maxWaitMillis = maxWaitSeconds * 1000L;

        while (System.currentTimeMillis() - startTime < maxWaitMillis) {

            File file = new File(downloadDir + File.separator + fileName);

            if (file.exists()) {
                return true;
            }

            try {
                Thread.sleep(500); // Check every 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }

    /**
     * Download File without Chrome Options
     * Using normal browser behavior
     */
    public static void downloadWithoutOptions(WebDriver driver) {

        try {
            // Simply click the download link
            WebElement downloadBtn = driver.findElement(By.id("downloadBtn"));
            downloadBtn.click();

            System.out.println("Download started (browser default)");

            // Browser will download to its default download directory
            // Usually: C:\Users\<username>\Downloads

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Downloaded File
     */
    public static File getDownloadedFile(String downloadDir, String fileName) {

        File file = new File(downloadDir + File.separator + fileName);

        if (file.exists()) {
            return file;
        }

        return null;
    }

    /**
     * Verify File Content
     */
    public static boolean verifyFileContent(String filePath, String expectedContent) {

        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filePath);
            String fileContent = new String(java.nio.file.Files.readAllBytes(path));

            return fileContent.contains(expectedContent);

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Interview Notes:
     * Q: How to handle file upload in Selenium?
     * A: For input[type="file"], use sendKeys(file_path). 
     *    For custom upload buttons, use JavaScript to trigger or Robot class.
     *    
     * Q: How to download files?
     * A: Configure ChromeOptions with download directory,
     *    click download link, wait for file to appear in directory.
     *    
     * Q: Can Selenium download files directly?
     * A: No, Selenium triggers the download but browser handles it.
     *    Configure browser preferences and verify file in directory.
     */
}
