// Cucumber Feature File
Feature: Bitcoin Price Retrieval
  Scenario: Fetch Bitcoin prices for a valid date range
    Given Bitcoin price data exists between "2025-03-01" and "2025-03-03"
    When the client requests Bitcoin prices from "2025-03-01" to "2025-03-03" in "USD"
    Then the response should contain the highest price "43000.0" and lowest price "41000.0"

  Scenario: Fetch Bitcoin prices for a date range with no data
    Given no Bitcoin price data exists between "2025-03-10" and "2025-03-12"
    When the client requests Bitcoin prices from "2025-03-10" to "2025-03-12" in "USD"
    Then the response should contain an error message "No data available for the given dates"