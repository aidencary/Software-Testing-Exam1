## Smart-Home Energy Rebate Calculator

- Link to Google Sheet of tables and test cases: https://docs.google.com/spreadsheets/d/1lS1qhHFSMMezxhcvpbgql74oY7NjwHY--qm1OX0HjjY/edit?usp=sharing

## The Scenario: The "Smart-Home Energy Rebate"

- A utility company offers a rebate on electricity bills based on three factors:
1. Usage Tier: Measured in kWh (Kilowatt-hours).
2. Smart Device Integration: Whether the user has a "Smart Thermostat" connected.
3. Peak Hours Opt-Out: Whether the user agreed to reduce power during high-demand events.

## The Business Rules:

- Tier 1 (0 < kWh $<= 500): No rebate regardless of other factors (base rate).
- Tier 2 (500 < kWh <= 1500): * 10% rebate if they have a Smart Thermostat OR Opt-Out.
  - 15% rebate if they have BOTH.
- Tier 3 (kWh > 1500):
  - 20% rebate if they have a Smart Thermostat AND Opt-Out.
  - Otherwise, 5% "consolidation" rebate just for being a high-volume user.
- Invalid Input: Any kWh <= 0 should throw an IllegalArgumentException

## Test Files

- EnergyCalculator.java: The core application logic.
- EnergyCalculatorTest.java: The JUnit 5 implementation utilizing ParameterizedTest.
- testCasesEnergyCalc.csv: Externalized data source containing all BVA and Decision Table test cases.

## Requirements

- Java 25+
- Maven (or included wrapper)

## Setup

- Clone the project and build it
- Run tests using "./mvnw test" in the terminal or right-click on EnergyCalculatorTest class and click "Run"
