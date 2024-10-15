Feature: User Registration and Login
  As a user, I want to create an account, so that I can log in and use Koel app.

  Background:
    Given The user is on the Registration page

  @CreateAccount
  Scenario: Successfully create an account
    When The user enters a valid email "test@testpro.io"
    And The user enters a valid password "password123!"
    And The user submits the registration form
    Then A confirmation message should be displayed "Account created successfully"
    And The password should be sent to the user's email box
    And The user should be able to log in using the registered email and password
    And The user data should be correctly saved in the database with encrypted password

  @EmailValidation
  Scenario: Invalid email - missing @ symbol
    When The user enters an invalid email "testtestpro.io"
    And The user submits the registration form
    Then An error message should be displayed "Invalid email format"

  @EmailValidation
  Scenario: Invalid email - missing dot in domain
    When The user enters an invalid email "test@com"
    And The user submits the registration form
    Then An error message should be displayed "Invalid email format"

  @EmailValidation
  Scenario: Invalid email - missing @testpro.io domain
    When The user enters an email with a different domain "test@otherdomain.com"
    And The user submits the registration form
    Then An error message should be displayed "Invalid email format"

  @EmailPrevention
  Scenario: Prevent using multiple users generation with +1 before @ sign
    When The user enters an email with +1 before the @ sign "test+1@testpro.io"
    And The user submits the registration form
    Then An error message should be displayed "Invalid email format"

  @EmailExist
  Scenario: Email already exists in the database
    Given The email "test@testpro.io" is already registered
    When The user enters the same email "test@testpro.io"
    And The user submits the registration form
    Then An error message should be displayed "User already registered"

  @LoginAfterRegistration
  Scenario: Log in after successful registration
    Given The user has registered an account with email "test@testpro.io" and password "password123!"
    When The user logs in with valid credentials
    Then The user should be logged in successfully

  @DBVerification
  Scenario: Verify user data in the database
    Given The user has successfully registered with email "test@testpro.io" and password "password123!"
    Then The email and encrypted password should be stored in the database
