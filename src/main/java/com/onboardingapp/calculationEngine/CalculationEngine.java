package com.onboardingapp.calculationEngine;

import java.io.IOException;

import org.apache.spark.sql.SparkSession;

public interface CalculationEngine {
	 SparkSession sparksession = SparkSession
	            .builder()
	            .appName("OnBoardingSpark_Application").master("local[*]")
	            .getOrCreate();

	    void OnBoardingCalculation() throws IOException;
	}


