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
	public static List<Map<String, String>> getAllDataFromExcel(String fileName, String scenario, String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testData\\" + fileName)) {
			Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new RuntimeException("Sheet \"" + sheetName + "\" not found in " + fileName);
			}

			Row headerRow = sheet.getRow(0);
			if (headerRow == null) {
				throw new RuntimeException("No header row found in sheet: " + sheetName);
			}

			int totalRows = sheet.getPhysicalNumberOfRows();
			int totalCols = headerRow.getPhysicalNumberOfCells();

			DataFormatter dataFormatter = new DataFormatter();

			for (int i = 1; i < totalRows; i++) {
				Row row = sheet.getRow(i);
				if (row != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
					Map<String, String> dataMap = new HashMap<>();

					for (int j = 0; j < totalCols; j++) {
						String headerName = headerRow.getCell(j).getStringCellValue();
						Cell cell = row.getCell(j);

						String cellValue = (cell != null) ? dataFormatter.formatCellValue(cell) : "";
						dataMap.put(headerName, cellValue);
					}

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
