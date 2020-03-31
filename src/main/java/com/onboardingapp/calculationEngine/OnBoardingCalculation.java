package com.onboardingapp.calculationEngine;

import java.io.IOException;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;

import com.onboardingapp.Metadata.CalculationRequest;

public class OnBoardingCalculation implements CalculationEngine {

	@Override
	public void OnBoardingCalculation() throws IOException {
		// TODO Auto-generated method stub
		 CalculationRequest cr=new CalculationRequest();
		 System.setProperty("hadoop.home.dir", "C://winutils"); 
	        List<String> proplist = cr.propertiesCheck();
	        String TargetPath=proplist.get(0);
	        String Query=proplist.get(1);
	        String JsonPath = proplist.get(2);
	        String CSVPath = proplist.get(3);
	        String d=proplist.get(4);

	        Dataset transcore_df = this.transcoreMethod(JsonPath);
	        Dataset transcorelot_df=this.transcorelotMethod(CSVPath,d);
	        transcore_df.createOrReplaceTempView("json_vw");
	        transcorelot_df.createOrReplaceTempView("csv_vw");

	        Dataset resultdf = sparksession.sql(Query);
	        resultdf.repartition(1).write().mode(SaveMode.Overwrite)
	        .option("header", true).json(TargetPath);
		
	}
	public Dataset transcoreMethod(String JsonPath)
    {
        Dataset transcore_df = sparksession.read().format("json")
                .option("header", true)
                .option("inferSchema", true)
                .load(JsonPath);
        return transcore_df;
    }
	public Dataset transcorelotMethod(String CSVPath,String d)
    {
        Dataset transcorelot_df = sparksession.read().format("csv")
                .option("header", true)
                .option("delimiter",d)
                .option("inferSchema", true)
                .load(CSVPath);
        return transcorelot_df;
    }


	
}


