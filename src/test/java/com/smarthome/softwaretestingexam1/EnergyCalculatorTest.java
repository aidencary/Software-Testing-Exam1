package com.smarthome.softwaretestingexam1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnergyCalculatorTest {

    private EnergyCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new EnergyCalculator();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testCasesEnergyCalc.csv", numLinesToSkip = 1)
    public void testCalculateRebate(String testCase, double kwh, boolean hasSmartDevice,
                                    boolean peakOptOut, String expectedResult, boolean isError) {

        if (isError) {
            assertThrows(IllegalArgumentException.class, () ->
                    calculator.calculateRebate(kwh, hasSmartDevice, peakOptOut), String.format("Test Case %s Failed -- Expected an IllegalArgumentException but none was thrown.", testCase));
        } else {
            double expected = Double.parseDouble(expectedResult);
            double actualResult = calculator.calculateRebate(kwh, hasSmartDevice, peakOptOut);

            assertEquals(expected, actualResult,
                    String.format(
                            "Test Case %s Failed -- kWh: %.1f, Device: %b, Opt-Out: %b | Expected: %s, Actual: %.2f",
                            testCase, kwh, hasSmartDevice, peakOptOut, expectedResult, actualResult
                    ));
        }
    }


}