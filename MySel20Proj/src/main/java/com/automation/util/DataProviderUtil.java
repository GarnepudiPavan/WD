package com.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

public class DataProviderUtil {

	private static Logger logger = LogManager.getLogger(DataProviderUtil.class);

	// get file from classpath, resources folder
	private File getFileFromResources(String fileName) {

		// ClassLoader classLoader = getClass().getClassLoader();

		URL resource = DataProviderUtil.class.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

	@DataProvider(name = "csvDataProvider")
	public Iterator<Object[]> csvDataProvider(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		String pathName = "src/main/resources/negativeLogInTest.csv";// "src" + File.separator + "main" + File.separator
																		// + "resources" +
		// File.separator +
		// "dataProvider" + File.separator +
		// + method.getName() + ".csv";// + method.getDeclaringClass().getSimpleName() +
		// File.separator
		logger.info("File path is" + " " + pathName);
		File file = getFileFromResources("negativeLogInTest.csv");
		// logger.info("Absolute path of File" + " " + file.getAbsolutePath());
		CSVReader reader;
		List<Object[]> myEntries = null;
		try {
			reader = new CSVReader(new FileReader(file));

			myEntries = new ArrayList<Object[]>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				myEntries.add(nextLine);
			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myEntries.iterator();
	}
	
	
	///Read data from Excel
	
	@DataProvider(name = "searchWords")
	public Object[][] provider() throws Exception {
	    SpreadsheetData spreadsheetData = new SpreadsheetData();
	    return spreadsheetData.getCellData("./src/test/resources/data/data.xlsx");
	}
	
	public class SpreadsheetData {
		public String[][] getCellData(String path) throws InvalidFormatException, IOException {
		FileInputStream stream = new FileInputStream(path);
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet s = workbook.getSheetAt(0);
		       int rowcount = s.getLastRowNum();
		        int cellcount = s.getRow(0).getLastCellNum();
		String data[][] = new String[rowcount][cellcount];
		        for (int rowCnt = 1; rowCnt <= rowcount; rowCnt++) {
		            Row row = s.getRow(rowCnt);
		            for (int colCnt = 0; colCnt < cellcount; colCnt++) {
		                Cell cell = row.getCell(colCnt);
		                try {
		if (cell.getCellType() == cell.CELL_TYPE_STRING) {
		                        data[rowCnt - 1][colCnt] = cell.getStringCellValue();
		} else {
		                        data[rowCnt - 1][colCnt] = String.valueOf(cell.getNumericCellValue());
		}
		                } catch (Exception e) {
		                    e.printStackTrace();
		}
		            }
		        }
		return data;
		}
		}
	
	
}
