package com.onboardingapp.Metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import com.google.inject.Inject;
import com.onboardingapp.calculationEngine.CalculationEngine;

public class CalculationRequest {
	 CalculationEngine c;
      public  CalculationRequest()
	 {

	 }
	    @Inject
	    public CalculationRequest(CalculationEngine c)
	    {

	        this.c=c;
	    }
	    // Method which checks the Property file if exist only then calls the OnBoarding Calculation Method
	    public List<String> propertiesCheck() throws IOException {
	        List<String> list = new ArrayList<String>();
	        String JsonPath;
	        String CSVPath;
	        String TargetPath;
	        String Query;
	        String d;
	        String FILE_NAME = "src/main/resources/onboarding.properties";
	        File f = new File(FILE_NAME);
	        long propFileSize = f.length();
	        if (!f.exists() || !f.isFile() || propFileSize == 0) {
	            System.out.println("Property File does not exist");}
	        else {
	            Properties p = new Properties();
	            p.load(new FileInputStream(f));
	            TargetPath=p.getProperty("OutputPath");
	            list.add(TargetPath);
	            Query=p.getProperty("Query");
	            list.add(Query);
	            JsonPath = p.getProperty("JsonFileName");
	            list.add(JsonPath);
	            File jsonFile = new File(JsonPath);
	            long jsonFileSize = jsonFile.length();
	            String json_ext = FilenameUtils.getExtension(JsonPath);
	            CSVPath = p.getProperty("CSVFileName");
	            File csvFile = new File(CSVPath);
	            long csvFileSize = csvFile.length();
	            String csv_ext = FilenameUtils.getExtension(CSVPath);
	            list.add(CSVPath);
	            d=p.getProperty("CsvDelimiter");
	            list.add(d);

	            if (!jsonFile.exists() || !jsonFile.isFile() || jsonFileSize == 0 || !json_ext.equals("json")) {
	                System.out.println("Transaction Core File (Dependency File) is Missing");
	            } else {
	                if (!csvFile.exists() || !csvFile.isFile() || csvFileSize == 0 || !csv_ext.equals("csv")) {
	                    System.out.println("Transaction Core Lot File Missing");
	                } else {
	                    System.out.println("Files are Ready to Go!!!");
	                    return list;
	                       }
	            }
	        }
	        return null;
	    }

	    public void makeRequest() throws IOException {
	        c.OnBoardingCalculation();
	    }

}
