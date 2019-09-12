package com.automation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	@DataProvider(name = "csvDataProvider")
	public Iterator<Object[]> csvDataProvider(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();

		String pathName = "src/main/resources/negativelogintest.csv";

		String[] fileContent;

		logger.info("File path is" + " " + pathName);

		CSVReader reader;

		try {
			reader = new CSVReader(new FileReader((pathName)));
			fileContent = reader.readNext();

			if (fileContent != null) {
				String[] eachRow; // create string here so that for each row we have a new string
				while ((eachRow = reader.readNext()) != null) {
					Map<String, String> testData = new LinkedHashMap<String, String>();
					// create Map here so that for each row in csv file a new map is created with
					// column headers as keys
					for (int i = 1; i < fileContent.length; i++) {// i=1 sno column is skipped
						logger.info("Extracted values from csv file" + " " + eachRow[i]);
						testData.put(fileContent[i], eachRow[i]);
					}
					list.add(new Object[] { testData });// add object to list here so that for every row it is returning
				}

			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

	/// Read data from Excel

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

//TestNG DataProvider
	@DataProvider(name = "dataProvider1")
	public Object[][] dataProvider1() {
		return new Object[][] { { "value1", 1 }, { "value2", 2 }, { "value3", 3 }, { "value4", 4 } };

	}
}
