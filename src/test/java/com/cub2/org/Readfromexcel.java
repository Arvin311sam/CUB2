package com.cub2.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readfromexcel {

	public void readExcel(String filePath, String fileName, String sheetName) throws IOException {
		File file = new File(filePath + "\\" + fileName); // Create an object of File class to open xlsx file
		FileInputStream inputStream = new FileInputStream(file); // Create an object of FileInputStream class to read
																	// excel file
		Workbook wb = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) { // Check condition if the file is xlsx file
			wb = new XSSFWorkbook(inputStream); // If it is xlsx file then create object of XSSFWorkbook class
		} else if (fileExtensionName.equals(".xls")) { // Check condition if the file is xls file
			wb = new HSSFWorkbook(inputStream); // If it is xls file then create object of HSSFWorkbook class
		}
		Sheet sheet = wb.getSheet(sheetName); // Read sheet inside the workbook by its name
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum(); // Find number of rows in excel file
		for (int i = 0; i < rowCount + 1; i++) { // Create a loop over all the rows of excel file to read it
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) { // Create a loop to print cell values in a row
				System.out.print(row.getCell(j).getStringCellValue() + "|| ");// Print Excel data in console
			}
			System.out.println();
		}
	}

	// Main function is calling readExcel function to read data from excel file
	public static void main(String string) throws IOException {
		Readfromexcel objExcelFile = new Readfromexcel(); // Create an object of ReadGuru99ExcelFile class
		String filePath = System.getProperty("user.dir") + "\\src\\excelExportAndFileIO"; // Prepare the path of excel
																							// file
		objExcelFile.readExcel(filePath, "ExportExcel.xlsx", "ExcelGuru99Demo"); // Call read file method of the class
																					// to read data

	}

}