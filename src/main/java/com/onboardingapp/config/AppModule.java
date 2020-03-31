package com.onboardingapp.config;

import com.google.inject.AbstractModule;
import com.onboardingapp.calculationEngine.CalculationEngine;
import com.onboardingapp.calculationEngine.OnBoardingCalculation;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        //super.configure();
        bind(CalculationEngine.class).to(OnBoardingCalculation.class);
    }
}
