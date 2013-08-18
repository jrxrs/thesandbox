Feature: Pascal's Triangle Calculation

  Scenario: Obtain the value of Pascal' Triangle given a columns and a row
    Given I want to calculate the value of column x and row y in Pascal's Triangle
    When I pass a data table with a list of columns, rows and their expected results
    Then The results should all be correct
      | c | r | result |
      | 0 | 0 | 1      |
      | 1 | 1 | 1      |
      | 1 | 2 | 2      |
      | 2 | 3 | 3      |
      | 2 | 4 | 6      |
      | 4 | 6 | 15     |
      | 5 | 9 | 126    |
      | 9 | 5 | 0      |
