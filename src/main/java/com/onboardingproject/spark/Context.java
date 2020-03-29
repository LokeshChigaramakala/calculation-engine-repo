package com.onboardingproject.spark;

import org.apache.spark.sql.SparkSession;

public interface Context {
	
	 SparkSession sparksession = SparkSession
	            .builder()
	            .appName("OnBoardingSpark_Application").master("local[*]")
	            .getOrCreate();

}
