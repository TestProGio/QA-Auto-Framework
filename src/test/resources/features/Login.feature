Feature: Login feature

  #This is a comment
  @skip
  Scenario: Positive Login Scenario
    Given I open Koel Login Page
    When I enter email "demo@testpro.io"
    And I enter password "te$t$tudent"
    And I click submit
    Then I should be logged in
  @skip
  Scenario Outline: Login Scenario
    Given I open Koel Login Page
    When I enter email "<Email>"
    And I enter password "<Password>"
    And I click submit
    Then I should be logged in

    Examples:
      | Email           | Password    |
      | de@testpro.io   | te$t$tudent |
      | demo@testpro.io | t$tudent    |
      |                 | te$t$tudent |
      | demo@testpro.io |             |
      |                 |             |
      | demo@testpro.i  | test$tudent |
