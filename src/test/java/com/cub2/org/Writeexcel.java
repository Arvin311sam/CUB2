package com.cub2.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writeexcel {

	public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {
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
			wb = new HSSFWorkbook(inputStream); // If it is xls file then create object of XSSFWorkbook class
		}
		Sheet sheet = wb.getSheet(sheetName); // Read excel sheet by sheet name
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum(); // Get the current count of rows in excel file
		Row row = sheet.getRow(0); // Get the first row from the sheet
		Row newRow = sheet.createRow(rowCount + 1); // Create a new row and append it at last of sheet
		for (int j = 0; j < row.getLastCellNum(); j++) { // Create a loop over the cell of newly created Row
			Cell cell = newRow.createCell(j); // Fill data in row
			cell.setCellValue(dataToWrite[j]);
		}
		inputStream.close(); // Close input stream
		FileOutputStream outputStream = new FileOutputStream(file); // Create an object of FileOutputStream class to
																	// create write data in excel file
		wb.write(outputStream); // write data in the excel file
		outputStream.close(); // close output stream
	}

	public static void main(String... strings) throws IOException {
		String[] valueToWrite = { "Mr. E", "Noida" }; // Create an array with the data in the same order in which you
														// expect to be
		// filled in excel file
		Writeexcel objExcelFile = new Writeexcel(); // Create an object of current class
		// Write the file using file name, sheet name and the data to be filled
		objExcelFile.writeExcel(System.getProperty("user.dir") + "\\src\\excelExportAndFileIO", "ExportExcel.xlsx",
				"ExcelGuru99Demo", valueToWrite);
	}
}
