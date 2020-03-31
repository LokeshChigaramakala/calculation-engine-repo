package com.onboardingapp.Demo;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onboardingapp.Metadata.CalculationRequest;
import com.onboardingapp.config.AppModule;

public class SparkOnboarding_Demo {
	static final String status = "Yes";
    static final Logger logger = Logger.getLogger(SparkOnboarding_Demo.class);
    // Implementing Loggers to SparkOnboarding_Demo
      private void runMe(String parameter) {
        logger.setLevel(Level.ERROR);
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

    public static void sendRequest(String status) throws IOException {

        if (status.equals("Yes")) {

            // creating the injector with te AppModule
            Injector inject=Guice.createInjector(new AppModule());
            // Instantiating method with Guice
            CalculationRequest request = inject.getInstance(CalculationRequest.class);
            request.makeRequest();

        }
    }
        public static void main(String[] args) throws IOException,ArithmeticException {
            try {
                logger.info("Application starts !!!");
                System.setProperty("hadoop.home.dir", "C://winutils"); 
                SparkOnboarding_Demo log=new SparkOnboarding_Demo();
                log.runMe("OnBoardingSparkApplication");
                String log4jConfigFile = System.getProperty("user.dir")
                        + File.separator + "log4j.properties";
                PropertyConfigurator.configure(log4jConfigFile);
                sendRequest(status);
                System.out.println("File Created !!!");

            } catch (NullPointerException e) {
                System.out.print("NullPointerException Caught");
            }
            catch (ArithmeticException e) {
               System.out.print("ArithmeticException Caught");
            }
        }
}

