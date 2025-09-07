Feature: Running a simple test

  Scenario: A basic test to verify the setup
    Given I have a working setup
    When I run the test
    Then I should see "Setup is OK!"