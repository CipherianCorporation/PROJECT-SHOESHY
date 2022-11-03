package com.edu.graduationproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName, int colMax) throws Exception {
		Object[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startRow = 1;
			int startCol = 1;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			// you can write a function as well to get Column count
			int totalCols = colMax;
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
					System.out.println(tabArray[ci][cj]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			CellType dataType = Cell.getCellType();
			if (dataType == CellType.NUMERIC) {
				Double CellData = Cell.getNumericCellValue();
				return String.format("%.0f", CellData);
			} else if (dataType == CellType.FORMULA) {
				String CellData = Cell.getCellFormula();
				return CellData;
			} else {
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static void exportTestResultExcel(File file, Map<String, Object[]> map) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetIndex("Test Result") == -1 ? wb.createSheet("Test Result")
					: wb.getSheet("Test Result");
			int rowIndex = 0, cellIndex;
			// create row and cell and set value to cell
			for (Map.Entry<String, Object[]> entry : map.entrySet()) {
				XSSFRow row = sheet.createRow(rowIndex++);
				cellIndex = 0;
				for (Object obj : entry.getValue()) {
					XSSFCell cell = row.createCell(cellIndex++);
					if (obj instanceof Date) {
						cell.setCellValue((Date) obj);
					} else if (obj instanceof Boolean) {
						cell.setCellValue((Boolean) obj);
					} else if (obj instanceof String) {
						cell.setCellValue((String) obj);
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					} else if (obj instanceof Integer) {
						cell.setCellValue((Integer) obj);
					}
				}
			}
			// save to file
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
			wb.close();
			System.out.println("Export test result to excel success!!");
		} catch (Exception e) {
			throw e;
		}
	}
	
}
