@cucumberTest
Feature:  validate my feature
Background:
  Given Calculator app is open

  Scenario:validate addition of 2 digits
    When I add 2 and 3
    Then sum should be "5"
#
#  Scenario:fail test to capture screen shot
#    When I add 2 and 3
#    Then sum should be 6