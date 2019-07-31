package com.automation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

public class CSVDataProvider {

	private static Logger logger = LogManager.getLogger(CSVDataProvider.class);

	@DataProvider(name = "CSVDataProvider")
	public static Iterator<Object[]> provideData(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		String pathName = "src" + File.separator + "test" + File.separator + "resources" + File.separator
				+ "dataproviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
				+ method.getName() + ".csv";
		File file = new File(pathName);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String keys[] = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				while ((dataParts = reader.readNext()) != null) {
					HashMap<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i <= dataParts.length - 1; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					list.add(new Object[] { testData });
				}
				reader.close();
			}
		} catch (FileNotFoundException e) {
			logger.info("Exception in csv data provider" + " " + e.getStackTrace().toString());
		} catch (IOException excep) {
			logger.info("Exception in csv data provider" + " " + excep.getStackTrace().toString());
		}
		return list.iterator();
	}

}
