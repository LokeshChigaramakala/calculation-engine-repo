package com.onboardingproject.test.spark;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.spark.sql.Dataset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.onboardingapp.calculationEngine.OnBoardingCalculation;

public class OnBoardingCalculationTest {
	 	@InjectMocks
	    OnBoardingCalculation calculation;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void transcoreMethodTest() {
	        String mockJsondata = "C:\\trpworkspace\\calculation-engine\\src\\main\\resources\\Transaction_Core.json";
	        Dataset actualtranscore_val = calculation.transcoreMethod(mockJsondata);
	        assertNotNull(actualtranscore_val);

	    }

	    @Test
	    public void transcorelotMethod() {
	        String mockcsvData = "C:\\trpworkspace\\calculation-engine\\src\\main\\resources\\Transaction_Core_Lot.csv";
	        Dataset actualtranscorelot_val = calculation.transcorelotMethod(mockcsvData,",");
	        assertNotNull(actualtranscorelot_val);
	    }
	    
	    @Test
	    //@Ignore
	    public void testOnboardingCalculation() throws IOException {
	    	OnBoardingCalculation myClass = new OnBoardingCalculation();
	    	myClass.OnBoardingCalculation();
	       
	    	
	    	
	    	
	    }
	    

}
