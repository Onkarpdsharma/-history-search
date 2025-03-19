@history
Feature: Retrieve historical Bitcoin prices

  Scenario: Successfully retrieve Bitcoin prices within a date range in USD
    Given the Bitcoin API is available
    When I request prices from "2024-01-01" to "2024-01-05" in "USD"
    Then I should receive the following prices:
      | Date       | Price  |
      | 2024-01-01 | 42000  |
      | 2024-01-02 | 43000  |
      | 2024-01-03 | 41000  |
      | 2024-01-04 | 45000  |
      | 2024-01-05 | 44000  |
    And the highest price should be 45000
    And the lowest price should be 41000

  Scenario: Retrieve Bitcoin prices in a different currency (EUR)
    Given the Bitcoin API is available
    When I request prices from "2024-01-01" to "2024-01-05" in "EUR"
    Then I should receive converted prices with exchange rate 0.92
    And the highest price should be 41400
    And the lowest price should be 37720

  Scenario: No data available for the given date range
    Given the Bitcoin API is available
    When I request prices from "2023-12-25" to "2023-12-30" in "USD"
    Then I should receive an error message "No data available for the given dates"

  Scenario: Offline mode returns fallback data
    Given the Bitcoin API is in offline mode
    When I request prices from "2024-01-01" to "2024-01-02" in "USD"
    Then I should receive fallback prices:
      | Date       | Price  |
      | 2024-01-01 | 40000  |
      | 2024-01-02 | 41000  |
    And the highest price should be 41000
    And the lowest price should be 40000
