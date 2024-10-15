Feature: Koel Song Search Feature

  As a user, I want to perform a search to find an existing song, artist, or album
  so that I can quickly access my music collection.

  Acceptance Criteria:
  - After searching for existing songs, artists, or albums, matched results should be populated on the Search results page.
  - Results should be displayed in each section: Songs, Artist, Album.
  - Data should be displayed in each section, regardless of whether a song, artist, or album is searched for.
  - If no results are found, an empty list should be displayed with the message "None found".
  - Search should ignore leading and trailing white spaces.
  - Search should be case-sensitive.
  - I can clear the search query with the keyboard or the 'x' button. Clearing the search should also clear the results in all sections.


  @run
  Scenario: Searching for an existing song should display results
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box the existing song "Epic Song"
    Then the matched song should appear in the Songs section of the Search results page
    And the Song, Artist and Album sections should display relevant information
    When I click the x button
    Then the search results should be cleared

  @skip
  Scenario: Searching for an existing artist should display results
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box the existing artist "Boxcat Games"
    Then the matched artist "Boxcat Games" should appear in the Artist section of the Search results page
    And the Song, Artist and Album sections should display relevant information
    When I click the x button
    Then the search results should be cleared
  @skip
  Scenario: Searching for an existing album should display results
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box the existing album "Airbit"
    Then the album "Nameless: The Hackers RPG Soundtrack" should appear in the Album section of Search page
    And the Song, Artist and Album sections should display relevant information
    When I click the x button
    Then the search results should be cleared

  @skip
  Scenario: Searching for a non-existing song should display 'no results' message
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box the non-existing song "NamMyoHO"
    Then the search results page should show an empty list with None found message

  @skip
  Scenario: Search should ignore leading and trailing white spaces
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box "  chill song  "
    Then the search results should be the same as if "chill song" was typed
 @skip
  Scenario: Search should be case-sensitive
    Given I am logged
    And I am on the home page
    And I navigate to the search box
    When I type in the search box the song "bornking" in lowercase
    Then no results should be displayed
    When I type in the search box the song "BORNKING" in uppercase
    Then no results should be displayed
    When I type in the search box the song "BOrnkInG" in mixedcase
    Then no results should be displayed







