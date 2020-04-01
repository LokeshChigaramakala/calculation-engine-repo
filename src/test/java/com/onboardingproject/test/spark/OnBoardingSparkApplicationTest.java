package com.onboardingproject.test.spark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.onboardingproject.spark.OnBoardingSparkApplication;

public class OnBoardingSparkApplicationTest {
	OnBoardingSparkApplication onBoarding=new OnBoardingSparkApplication();
	
	 @Rule
	 public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testRunme() {
		onBoarding.runMe("logger info");
		
	}
	@Test
	public void testInputfileProcess() throws FileNotFoundException, IOException {
		onBoarding.InputfileProcess(onBoarding);
		
	}
	
	@Test
    public void someMethod() throws IOException {
        // given
        final File file1 = folder.newFile("myfile1.txt");
        final File file2 = folder.newFile("myfile2.txt");

       
    }
	
}
