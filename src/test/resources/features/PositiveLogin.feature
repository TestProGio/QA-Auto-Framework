Feature: PositiveLogin feature

  #This is a comment
  @skip
  Scenario Outline: Positive Login Scenario
    Given I open Koel Login Page
    When I enter email <email>
    And I enter password <password>
    And I click submit
    Then I should be logged in
    Examples:
      | email             | password      |
      | "giovanna.silva@testpro.io" | "2024Sprint3!" |