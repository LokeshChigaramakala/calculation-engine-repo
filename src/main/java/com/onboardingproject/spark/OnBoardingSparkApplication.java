package com.onboardingproject.spark;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import scala.collection.Seq;
import org.apache.spark.sql.types.*;
import org.apache.log4j.*;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import static org.apache.spark.sql.functions.*;

public class OnBoardingSparkApplication implements Context {
	
	 private static final Logger logger = Logger.getLogger(OnBoardingSparkApplication.class);
	    private void runMe(String parameter) {

	        logger.setLevel(Level.DEBUG);
	        if(logger.isDebugEnabled()){
	            logger.debug("This is debug : " + parameter);
	        }
	        if(logger.isInfoEnabled()){
	            logger.info("This is Info : " + parameter);
	        }
	        logger.warn("This is warn : " + parameter);
	        logger.error("This is error : " + parameter);
	        logger.fatal("This is fatal : " + parameter);
	    }
	    public static void main(String args[])  throws FileNotFoundException, IOException {

	        logger.info("Application starts !!!");
	        System.setProperty("hadoop.home.dir", "C://winutils"); 
	        OnBoardingSparkApplication log = new OnBoardingSparkApplication();
	         //logs a debug message
	        log.runMe("OnBoardingSparkApplication");
	        File f = new File("src/main/resources/onboarding.properties");
	        Properties p = new Properties();
	        p.load(new FileInputStream(f));
	        String JsonPath = null;
	        String CSVPath = null;
	        String OutputPath = null;
	        String d = null;
	        if (f.exists() == true) {
	            JsonPath = p.getProperty("JsonFileName");
	            CSVPath = p.getProperty("CSVFileName");
	            OutputPath = p.getProperty("OutputPath");
	            d = p.getProperty("CsvDelimiter");
	        } else {
	            System.out.println("File Not Found");
	        }
	        // Read Transaction Core file into a Data frame
	        Dataset transcore_df = sparksession.read().format("json")
	                .option("header", true)
	                .option("inferSchema", true)
	                .load(JsonPath);
	       
	        // Read Transaction Core LOT  file into a Data frame
	        Dataset transcorelot_df = sparksession.read().format("csv")
	                .option("header", true)
	                .option("delimiter",d)
	                .option("inferSchema", true)
	                .load(CSVPath);
	        Dataset filterdata_df = transcore_df.filter(transcore_df.col("Transaction_Code").equalTo("CGDLT").or(transcore_df.col("Transaction_Code").equalTo("CGDST")));
            filterdata_df.createOrReplaceTempView("json_vw");
            transcorelot_df.createOrReplaceTempView("csv_vw");
            Dataset resultdf = sparksession.sql("select csv_vw.Transaction_ID,json_vw.Transaction_Code,sum(csv_vw.transaction_amount) as trans_amt from json_vw right outer join csv_vw on json_vw.Transaction_ID=csv_vw.Transaction_ID group by csv_vw.Transaction_ID,json_vw.Transaction_Code");   
            resultdf.createOrReplaceTempView("resultdf_vw");
            Dataset resultdf2 = sparksession.sql("select Transaction_ID,case when transaction_code is null then 'OTHER' else transaction_code end as Transaction_code,case when Transaction_code is null then 'NULL' else trans_amt end as trans_amt from resultdf_vw order by Transaction_ID");
            resultdf2.show();
	        resultdf2.repartition(1).write().mode(SaveMode.Overwrite)
	                .option("header", true).json(OutputPath);

	        resultdf2.show(false);
	        System.out.println("Hello");
	    }

}
