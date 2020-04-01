package com.trp.onboardingapp;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onboardingapp.config.AppModule;
import com.onboardingapp.metadata.CalculationRequest;

public class SparkOnBoardingMainApp {
	static final String status = "Yes";
	static final Logger logger = Logger.getLogger(SparkOnBoardingMainApp.class);

	// Implementing Loggers to SparkOnboarding_Demo
	private void runMe(String parameter) {
		logger.setLevel(Level.ERROR);
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}

	public static void sendRequest(String status) throws IOException {

		if (status.equals("Yes")) {
			// creating the injector with te AppModule
			Injector inject = Guice.createInjector(new AppModule());
			// Instantiating method with Guice
			CalculationRequest request = inject.getInstance(CalculationRequest.class);
			request.makeRequest();
		}
	}

	public static void main(String[] args) {
		startApplication();
	}

	public static void startApplication() {
		logger.info("Application starts !!!");
		System.setProperty("hadoop.home.dir", "C://winutils");
		SparkOnBoardingMainApp log = new SparkOnBoardingMainApp();
		log.runMe("OnBoardingSparkApplication");
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		try {
			sendRequest(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("File Created !!!");
	}
}
