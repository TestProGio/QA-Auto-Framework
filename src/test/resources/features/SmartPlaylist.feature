Feature: Create Smart Playlist

  As a user, I want to create a Smart playlist in the app,
  so that I can enjoy the music and modify my settings and preferences.

  # Creating a single rule playlist
  # @smoke @regression
@skip
  Scenario Outline: Create Smart Playlist with One Rule
    Given the user is logged into the app
    When the user clicks playlist creation
    And the user selects New Smart Playlist from menu
    And the user sets the playlist name as "<SingleRule Playlist>"
    And the user selects "<Rule Types>"
    And the user inputs "<SearchThis>"
    And the user saves the Smart playlist
    Then the Smart playlist "<SingleRule Playlist>" should be created successfully
    Then the results should be verified

    Examples:
      | SingleRule Playlist  | Rule Types  | SearchThis          |
      | 1- SPL Valid Title   | Title       | Pluto           |
      | 2- SPL Valid Album   | Album       | Airbit          |
      | 3- SPL Valid Artist  | Artist      | Makaih Beats    |
      | 4- SPL Valid Plays   | Plays       | 11              |
      | 5- SPL Valid Played  | Last Played | 10/16/2024      |
      | 6-SPL Valid Length   | Length      | 374             |

  # Creating a playlist with multiple rules
@skip
  Scenario Outline: Create Smart Playlist with Multiple Rules
    Given the user is logged into the app
    When the user clicks playlist creation
    And the user selects New Smart Playlist from menu
    And the user sets the playlist name as "<MultiRule Playlist>"
    And the user adds multiple different rules with options and inputs

      | Rule Options | Input                               |
      | Title        | M33 Project - Emotional Soundtrack  |
      | Album        | Midnight in Mississippi             |
      | Artist       | Lobo Loco                           |
      | Plays        | 5                                   |

    And the user saves the Smart playlist
    Then the Smart playlist "<MultiRule Playlist>" should be created successfully
    Then the results should be verified

    Examples:
      | MultiRule Playlist  |
      | Mixed Playlist 2     |

  # Creating a playlist with grouped rules
  @skip
  Scenario Outline: Create Smart Playlist with Group
    Given the user is logged into the app
    When the user clicks playlist creation
    And the user selects New Smart Playlist from menu
    And the user sets the playlist name as "<GroupRule Playlist>"
    And the user adds Group option with multiple rules options and inputs
      | Rule Options  | Input           |
      | Title         | Epic Song       |
      | Album         | Till Paradiso   |
      | Artist        | Dee Yan-Key     |
      | Plays         | 52              |
    And the user saves the Smart playlist
    Then the Smart playlist "<GroupRule Playlist>" should be created successfully
    Then the results should be verified

    Examples:
      | GroupRule Playlist   |
      | GroupPlaylist        |


  # Creating a playlist with no matching songs
  Scenario Outline: No Matching Songs for Smart Playlist Rule
    Given the user is logged into the app
    When the user clicks playlist creation
    And the user selects New Smart Playlist from menu
    And the user sets the playlist name as "<NoMatches Playlist>"
    And the user selects "<Rule Types>"
    And the user inputs "<SearchThis>"
    And the user saves the Smart playlist
    Then the Smart playlist "<NoMatches Playlist>" should be created successfully
    Then "No songs match the playlist's criteria." should appear
    Then the results should be verified

    Examples:
      | NoMatches Playlist | Rule Types  | SearchThis        |
      | 1-Invalid Song     | Title       | Personal Jesus     |



  @skip
  Scenario Outline: Check Playlist For Invalid Name Rules
    Given the user is logged into the app
    When the user clicks playlist creation
    And the user selects New Smart Playlist from menu
    And the user sets the playlist name as "<Playlist Name>"
    And the user adds a rule to include song title "Epic Song"
    And the user saves the Smart playlist
    Then the results should be verified

    Examples:
      | Playlist Name                                                                                   | Expected Outcome                                                                 |
      | (@Special_Characters!#$%^&*)                                                                    | The Smart playlist "(@Special_Characters!#$%^&*)" should be created successfully |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | The app should display an error "Playlist name must be between 1 and 256 characters" |
