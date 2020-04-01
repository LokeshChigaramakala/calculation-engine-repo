package com.onboarding.test.demo;

import java.io.IOException;

import org.junit.Test;

import com.trp.onboardingapp.SparkOnBoardingMainApp;

public class SparkOnBoardingMainAppTest {

	/*
	 * @Before public void before() throws NoSuchFieldException, SecurityException,
	 * Exception { Logger logger = Mockito.mock(Logger.class);
	 * setFinalStatic(SparkOnBoardingMainApp.class.getDeclaredField("logger"),
	 * logger); }
	 */

	@Test
	public void testStartApplication() throws IOException {
		String[] args = null;
		SparkOnBoardingMainApp.main(args);
	}

	@Test
	public void testSendRequest() throws IOException {
		SparkOnBoardingMainApp.sendRequest("Yes");

	}
	@Test
	public void testSendRequestNo() throws IOException {
		SparkOnBoardingMainApp.sendRequest("No");

	}

	/*
	 * @Test(expected = NullPointerException.class) public void
	 * givenNullFilePathWhenExecutingStartApplicatioThenThrowNPE() throws
	 * IOException { System.setProperty("user.dir", "");
	 * SparkOnBoardingMainApp.startApplication();
	 * 
	 * }
	 * 
	 * private static void setFinalStatic(Field field, Object newValue) throws
	 * Exception { field.setAccessible(true); Field modifiersField =
	 * Field.class.getDeclaredField("modifiers");
	 * modifiersField.setAccessible(true); modifiersField.setInt(field,
	 * field.getModifiers() & ~Modifier.FINAL); field.set(null, newValue); }
	 */

}
