package com.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi
import org.testng.annotations.DataProvider;

public class TestData {

	// private static XSSFCell Cell;

	// TODO Auto-generated constructor stub
	@DataProvider(name = "excelRead")
	public static String[][] excelRead() throws Exception {
		// Set Path of the Excel File using File Class

		File excelFilePath = new File( // "\\src\\test\\resources\\TestData.xls");
				"C:\\Users\\Pavan\\Documents\\workspaces\\WebDriver_Auto\\MySel20Proj\\src\\main\\java\\src\\test\\resources\\TestData.xls");

		// Pass on file object having excel file path to below File Input Stream
		// object

		FileInputStream fis = new FileInputStream(excelFilePath);

		// Create a WorkBook instance by passing above file Input Stream

		HSSFWorkbook wb1 = new HSSFWorkbook(fis);

		// Create a new ExcelSheet instance and pass on the sheet name
		HSSFSheet sheet1 = wb1.getSheet("TestData");
		// Using Sheet instance fetch number rows and columns
		int rowNum = sheet1.getLastRowNum() + 1;// Plus one as we want total
												// number of rows if 0-n are
												// number of rows and n is last
												// row then n+1 will give number
												// of rows. As 0 is header row
		int colNum = sheet1.getRow(0).getLastCellNum();// Plus one is not needed
														// for columns as there
														// is no header row and
														// last column will give
														// total number of
														// columns.

		String[][] data = new String[(rowNum - 1)][colNum];// - 1
		int k = 0;// counter created to keep track of row index as we are
					// looping row from 1st index skipping headers

		for (int i = 1; i < rowNum; i++) { // Row is set to 1 to skip Column
											// headers whch is in 0th row and
											// fetch only data
			// Iterating through each of the fetched row in Sheet
			HSSFRow row = sheet1.getRow(i);

			// Iterating through columns that are fetch from Excel
			for (int j = 0; j < colNum; j++) {

				// Fetching each row column values i.e 0th row column 1 and
				// column 2.
				HSSFCell cell = row.getCell(j);

				// Converting cell value fetched to String
				String value = cellToString(cell);
				data[k][j] = value;// assigning k as row index for String array
									// that holds data from Excel
			}
			k++;// increment row counter to update row index
		}
		return data;
	}

	// Method that converts cell values to String
	public static String cellToString(HSSFCell cell) {
		int type;
		Object result;
		type = cell.getCellType();
		switch (type) {
		case 0:
			result = cell.getNumericCellValue();
			break;
		case 1:
			result = cell.getStringCellValue();
			break;
		default:
			throw new RuntimeException("There are no support for this type of cell");
		}
		return result.toString();
	}

	public static String getCellData(XSSFSheet ExcelWSheet, Cell cell, int RowNum, int ColNum) throws Exception {

		try {

			cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			@SuppressWarnings("deprecation")
			int dataType = cell.getCellType();

			if (dataType == 3) {

				return "";

			} else {

				String CellData = cell.getStringCellValue();

				return CellData;

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

	@DataProvider(name = "readExcelWithXSS")
	public static Object[][] readExcelWithXSS() {
		ArrayList<String> data = new ArrayList<String>();
		String userID[] = null, pwd[] = null;
		String tabArray[][] = null;
		XSSFWorkbook ExcelWBook = null;
		Row row;
		Cell cell = null;
		try {
			File excel = new File(
					// "src\\main\\java\\src\\test\\resources\\TestData.xls");
					"D:\\repos\\garnepudipavan\\WD\\MySel20Proj\\src\\main\\java\\src\\test\\resources\\TestData2.xlsx");
			FileInputStream fis = new FileInputStream(excel);
			ExcelWBook = new XSSFWorkbook(fis);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheetAt(0);// "TestData"

			/*
			 * Iterator<Row> iterate = ExcelWSheet.iterator(); while
			 * (iterate.hasNext()) { row = iterate.next();
			 * 
			 * Iterator<Cell> cellIterate = row.cellIterator();
			 * 
			 * while (cellIterate.hasNext()) { cell = cellIterate.next();
			 * data.add(cell.getStringCellValue()); } }
			 */

			int startRow = 1;

			int startCol = 1;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			// you can write a function as well to get Column count

			int totalCols = 2;

			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(ExcelWSheet, cell, i, j);

					System.out.println(tabArray[ci][cj]);

				}

			}

			/*userID = new String[data.size()];
			pwd = new String[data.size()];
			for (int i = 0; i < data.size(); i++) {
				userID[i] = data.get(i + 2);
				pwd[i] = data.get(i + 3);
				i = i + 1;
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ExcelWBook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Object[][] { { tabArray }, };

	}

	@DataProvider(name = "addData2Excel")
	public static Object[][] addData2Excel() throws Exception {

		Map<String, String> pumpData = new HashMap<String, String>();

		pumpData.put("Test.UserID", "Age");
		pumpData.put("Kumar", "23");
		pumpData.put("Kumar", "22");

		File filePath = new File(
				"C:\\Users\\Pavan\\Documents\\workspaces\\WebDriver_Auto\\MySel20Proj\\src\\main\\java\\src\\test\\resources\\TestData3.xlsx");

		FileInputStream fisObj = new FileInputStream(filePath);
		XSSFWorkbook workBookObj = new XSSFWorkbook(filePath);
		XSSFSheet sheetObj = workBookObj.getSheetAt(0);

		int rowCount = sheetObj.getLastRowNum() - sheetObj.getFirstRowNum();

		Row row = sheetObj.createRow(rowCount);

		int cellCount = row.getLastCellNum();
		Cell cell = null;
		for (int i = 0; i < cellCount; i++) {

			cell = row.createCell(i);

			for (Map.Entry<String, String> values : pumpData.entrySet()) {

				cell.setCellValue(values.getValue());
			}
		}

		fisObj.close();
		FileOutputStream fosObj = new FileOutputStream(filePath);
		workBookObj.write(fosObj);
		fosObj.close();
		workBookObj.close();
		return new Object[][] { { "Hello" }, };
	}

}
