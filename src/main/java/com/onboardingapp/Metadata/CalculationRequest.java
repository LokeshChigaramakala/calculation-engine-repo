package com.onboardingapp.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.inject.Inject;
import com.onboardingapp.calculationEngine.CalculationEngine;

public class CalculationRequest {
	CalculationEngine calcEngine;

	private static String FILE_NAME = "src/main/resources/onboarding.properties";
	
	public CalculationRequest() {

	}

	@Inject
	public CalculationRequest(CalculationEngine calcEngine) {

		this.calcEngine = calcEngine;
	}

	// Method which checks the Property file if exist only then calls the OnBoarding
	// Calculation Method
	public List<String> propertiesCheck() throws IOException {
		List<String> list = new ArrayList<String>();
		String jsonPath;
		String CSVPath;
		String targetPath;
		String query;
		String delimiter;

		File f = new File(FILE_NAME);
		//long propFileSize = f.length();
		if (f.exists()/* && f.isFile() && propFileSize != 0 */) {
			Properties props = new Properties();
			props.load(new FileInputStream(f));
			targetPath = props.getProperty("OutputPath");
			list.add(targetPath);
			query = props.getProperty("Query");
			list.add(query);
			jsonPath = props.getProperty("JsonFileName");
			list.add(jsonPath);
			//File jsonFile = new File(JsonPath);
			//long jsonFileSize = jsonFile.length();
			//String json_ext = FilenameUtils.getExtension(JsonPath);
			CSVPath = props.getProperty("CSVFileName");
			//File csvFile = new File(CSVPath);
			//long csvFileSize = csvFile.length();
			//String csv_ext = FilenameUtils.getExtension(CSVPath);
			list.add(CSVPath);
			delimiter = props.getProperty("CsvDelimiter");
			list.add(delimiter);
			System.out.println("Files are Ready to Go!!!");

			/*
			 * if (jsonFile.exists() && jsonFile.isFile() && jsonFileSize != 0 &&
			 * json_ext.equals("json")) { if (csvFile.exists() && csvFile.isFile() &&
			 * csvFileSize != 0 && csv_ext.equals("csv")) {
			 * System.out.println("Files are Ready to Go!!!"); //return list; } }
			 */
		}
		return list;
	}

	public void makeRequest() throws IOException {
		calcEngine.OnBoardingCalculation();
	}

}
