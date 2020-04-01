package com.onboardingproject.test.metadata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.onboardingapp.metadata.CalculationRequest;
@RunWith(MockitoJUnitRunner.class)
public class CalculationRequestTest {
	
	@InjectMocks
	CalculationRequest calculationMockRequest;
	
	
	
	@Test
	public void testRequest() throws IOException {
		CalculationRequest calcReqTest=new CalculationRequest();
		
		List<String> list=calcReqTest.propertiesCheck();
		int expectedListsize=5;
		assertEquals(expectedListsize, list.size());
	}
	
	@Test
	public void givenRequestFailure() throws NoSuchFieldException, SecurityException, Exception {
		setFinalStatic(CalculationRequest.class.getDeclaredField("FILE_NAME"),"");
		List<String> strList=calculationMockRequest.propertiesCheck();
		assertEquals(0, strList.size());
		
	}
	private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);       
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }
	

}
