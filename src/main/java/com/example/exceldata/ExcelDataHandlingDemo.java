package com.example.exceldata;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Excel Data Handling - Read and Write Excel Files
 * 
 * Used Apache POI library
 * Dependencies: poi-ooxml-X.X.X.jar, poi-X.X.X.jar
 * 
 * Key Classes:
 * - Workbook: Represents entire Excel file
 * - Sheet: Represents a worksheet
 * - Row: Represents a row
 * - Cell: Represents a cell
 * 
 * File Extensions:
 * - .xls: Uses HSSFWorkbook
 * - .xlsx: Uses XSSFWorkbook
 */
public class ExcelDataHandlingDemo {

    /**
     * Read Data from Excel File
     * 
     * File Structure:
     * Row 0: ID | Name | Department | Salary
     * Row 1: 101 | Manoj | Testing | 50000
     * Row 2: 102 | Rahul | Development | 60000
     */
    public static void readExcelData(String filePath) {

        try {
            // Create FileInputStream object
            FileInputStream fis = new FileInputStream(filePath);

            // Create Workbook object
            Workbook workbook = new XSSFWorkbook(fis);

            // Get Sheet (default: first sheet at index 0)
            Sheet sheet = workbook.getSheetAt(0);

            // Get total rows
            int totalRows = sheet.getPhysicalNumberOfRows();
            System.out.println("Total rows: " + totalRows);

            // Iterate through rows
            for (int i = 0; i < totalRows; i++) {

                // Get row
                Row row = sheet.getRow(i);

                if (row != null) {

                    // Get total cells in row
                    int totalCells = row.getPhysicalNumberOfCells();

                    // Iterate through cells
                    for (int j = 0; j < totalCells; j++) {

                        // Get cell
                        Cell cell = row.getCell(j);

                        // Print cell value
                        if (cell != null) {
                            System.out.print(cell.getStringCellValue() + " | ");
                        }
                    }

                    System.out.println();
                }
            }

            // Close resources
            workbook.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Error reading Excel: " + e.getMessage());
        }
    }

    /**
     * Read Specific Cell Value
     */
    public static String readCellValue(String filePath, int sheetIndex, 
            int rowNum, int cellNum) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum);

            String value = cell.getStringCellValue();

            workbook.close();
            fis.close();

            return value;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Write Data to Excel File
     */
    public static void writeExcelData(String filePath) {

        try {
            // Create Workbook
            Workbook workbook = new XSSFWorkbook();

            // Create Sheet
            Sheet sheet = workbook.createSheet("EmployeeData");

            // Row 0 - Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Department");
            headerRow.createCell(3).setCellValue("Salary");

            // Row 1 - Data
            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue(101);
            row1.createCell(1).setCellValue("Manoj");
            row1.createCell(2).setCellValue("Testing");
            row1.createCell(3).setCellValue(50000);

            // Row 2 - Data
            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue(102);
            row2.createCell(1).setCellValue("Rahul");
            row2.createCell(2).setCellValue("Development");
            row2.createCell(3).setCellValue(60000);

            // Auto-size columns
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);

            System.out.println("Excel file created: " + filePath);

            workbook.close();
            fos.close();

        } catch (IOException e) {
            System.out.println("Error writing Excel: " + e.getMessage());
        }
    }

    /**
     * Update Existing Cell Value
     */
    public static void updateCellValue(String filePath, int sheetIndex, 
            int rowNum, int cellNum, String newValue) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum);

            // Update cell value
            cell.setCellValue(newValue);

            // Write back to file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);

            System.out.println("Cell updated successfully");

            workbook.close();
            fis.close();
            fos.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get Cell Value by Different Data Types
     */
    public static Object getCellValueByType(Cell cell) {

        try {
            CellType cellType = cell.getCellType();

            switch (cellType) {

                case STRING:
                    return cell.getStringCellValue();

                case NUMERIC:
                    return cell.getNumericCellValue();

                case BOOLEAN:
                    return cell.getBooleanCellValue();

                default:
                    return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Row by Search Value
     */
    public static Row findRowByValue(String filePath, String searchValue) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);

            // Loop through rows
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

                Row row = sheet.getRow(i);

                // Loop through cells in row
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

                    Cell cell = row.getCell(j);

                    if (cell.getStringCellValue().equals(searchValue)) {
                        workbook.close();
                        fis.close();
                        return row;
                    }
                }
            }

            workbook.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Get Data as 2D Array
     * Useful for data-driven testing
     */
    public static String[][] getExcelDataAsArray(String filePath) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            String[][] data = new String[rows][cols];

            for (int i = 0; i < rows; i++) {

                Row row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {

                    Cell cell = row.getCell(j);
                    data[i][j] = cell.getStringCellValue();
                }
            }

            workbook.close();
            fis.close();

            return data;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {

        String filePath = "C:\\TestData\\employees.xlsx";

        // Write Excel
        writeExcelData(filePath);

        // Read Excel
        readExcelData(filePath);

        // Read specific cell
        String cellValue = readCellValue(filePath, 0, 1, 1);
        System.out.println("Cell value: " + cellValue);

        // Update cell
        updateCellValue(filePath, 0, 1, 3, "55000");

        // Get as array
        String[][] data = getExcelDataAsArray(filePath);
        System.out.println("\nData as array:");
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
        }
    }
}
