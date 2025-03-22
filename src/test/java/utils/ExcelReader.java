package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	 /**
     * @param fileName - XL file name , different files can be used if needed. It supports multiple XL sheets
     * @param scenario - scenario in the XL file should match 
     * @param sheetName - XL sheet name should match and different modules or cases can use multiple XL tabs
     * @return multiple data values 
     */
	   public static List<Map<String, String>> getAllDataFromExcel(String fileName, String scenario, String sheetName) {
	        List<Map<String, String>> dataList = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream("C:\\Users\\msira\\Team2_AssuredNinjas\\src\\test\\resources\\testData\\" + fileName)) {
	            Workbook workbook = WorkbookFactory.create(fis);

	            // Get the specified sheet by name
	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                throw new RuntimeException("Sheet \"" + sheetName + "\" not found in " + fileName);
	            }

	            // Read header row to get column names
	            Row headerRow = sheet.getRow(0);
	            if (headerRow == null) {
	                throw new RuntimeException("No header row found in sheet: " + sheetName);
	            }

	            // Get total number of rows and columns
	            int totalRows = sheet.getPhysicalNumberOfRows();
	            int totalCols = headerRow.getPhysicalNumberOfCells();

	            // Use DataFormatter to handle different cell types gracefully
	            DataFormatter dataFormatter = new DataFormatter();

	            // Loop through all rows to find matching scenarios
	            for (int i = 1; i < totalRows; i++) {
	                Row row = sheet.getRow(i);
	                if (row != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
	                    Map<String, String> dataMap = new HashMap<>();
	                    // Loop through all columns to read data based on header
	                    for (int j = 0; j < totalCols; j++) {
	                        String headerName = headerRow.getCell(j).getStringCellValue();
	                        Cell cell = row.getCell(j);
	                        // Convert cell to string regardless of its type
	                        String cellValue = (cell != null) ? dataFormatter.formatCellValue(cell) : "";
	                        dataMap.put(headerName, cellValue);
	                    }
	                    // Add the row's data to the list
	                    dataList.add(dataMap);
	                }
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to read Excel file: " + fileName, e);
	        }

	        if (dataList.isEmpty()) {
	            throw new RuntimeException("No matching scenario found in sheet: " + sheetName);
	        }
	        return dataList;
	    }
    
}
