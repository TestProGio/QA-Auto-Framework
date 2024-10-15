Feature: View All Songs Page

  As a user,
  I want to be able to open the All Songs page
  So that I can see all existing songs.

  Background:
    Given the user is logged into the Koel App

  Scenario: Display All Songs on All Songs page
    When the user navigates to the All Songs page
    Then the All Songs page should display a list of songs
    And the total count of songs should be displayed
    And the total duration of all songs should be displayed
    And each song should have an ID
    And each song should have a Title
   And each song should have an Artist
   And each song should have an Album
   And each song should have a Time Duration


   # And the displayed songs should match the records in the database

 # Scenario: Validate Songs Data Matches the Database
  #  When the user navigates to the "All Songs" page
  #  Then the total count of songs on the page should match the total count from the database
  #  And the total duration of songs on the page should match the total duration from the database
  #  And each song’s ID, Title, Artist, Album, and Time should match the database records
