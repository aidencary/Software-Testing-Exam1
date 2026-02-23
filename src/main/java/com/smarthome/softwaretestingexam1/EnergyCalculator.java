package com.smarthome.softwaretestingexam1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnergyCalculator {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
        // Fault 1: Change <= 0 to < 0.
        // Caught by: Equivalence Partitions (TC1.1) which explicitly checks a negative partition.
        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        // Fault 2: Change > 500 to >= 500.
        // Caught by: BVA (TC2.5) because it checks the exact boundary of 500.
        // An EQ test like 250 (TC1.2) would still pass and miss this off-by-one error.
        if (kwh > 500 && kwh <= 1500) {

            // Fault 3: Change "&&" to "||".
            // Caught by: Decision Table (TC3.3 or TC3.4) which specifically tests the
            // combination of one TRUE and one FALSE boolean.
            // BVA usually uses TRUE/TRUE to test the kWh boundary and would miss this logic.
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.15;
            } else if (hasSmartDevice || peakOptOut) {
                rebatePercent = 0.10;
            }
        } else if (kwh > 1500) {

            // Fault 4: Entirely delete the "else { rebatePercent = 0.05; }" block.
            // Caught by: Code Coverage. JaCoCo would highlight
            // that the code path for "kwh > 1500" where both booleans are NOT true is
            // never executed if you don't have a test case like TC3.6 in my implementation.
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.20;
            } else {
                // Fault 5: Change 0.05 to 0.0.
                // Caught by: Branch Coverage. Even if Statement Coverage is 100%,
                // Branch Coverage ensures that both the 'True' and 'False' results of
                // every "if" condition are evaluated. This fault would only be caught if
                // you have a specific test for the "False" branch of this logic.
                rebatePercent = 0.05;
            }
        }

        return rebatePercent;
    }
}