Feature: Playlist Creation

  @skip
  Scenario: User creates a new playlist successfully
    Given I am logged in
    And The user is on the Playlist creation page
    When The user selects the playlist type as New Playlist
    And The user enters and submits a valid playlist name "MyPlaylist"
    Then The new playlist should be created successfully
    And The playlist "My New Playlist" should be displayed in the app

  @skip
  Scenario: User tries to create a playlist with the same name
    Given I am logged in
    And The user is on the Playlist creation page
    When The user selects the playlist type as New Playlist
    And The user enters and submits an existing playlist name "My New Playlist"
    Then An error message should be displayed "A playlist with this name already exists"

  @skip
  Scenario: User tries to create a playlist with an invalid name
    Given I am logged in
    And The user is on the Playlist creation page
    When The user selects the playlist type as New Playlist
    # Test for names shorter than 3 characters
    And The user enters and submits an invalid name
    Then The playlist should not be created
    # Test for names longer than 10 characters
    And The user enters and submits an invalid name
    #And The user enters and submits an invalid name more than 10 chars
    Then The playlist should not be created

    # And The playlist name should be displayed correctly in the app or database
#** pendingThen A red border should be displayed around the name input field