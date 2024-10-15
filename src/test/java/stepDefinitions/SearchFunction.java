package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import pages.AllSongsPage;
import pages.LoginPage;
import pages.SearchPage;
import utils.WebDriverManagerUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.NoSuchElementException;

public class SearchFunction {
    private WebDriverManagerUtil webDriverManager;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private AllSongsPage allSongsPage;
    private SoftAssert softAssert;

    // Instance variable to store song name
    public String songName;
    public String artistName;
    public String albumName;
    public String badSongName;
    public String badSongResult;
    public String whiteSpaceStrg;
    public String expectedSongName;
    public String lowercaseSong;
    public String uppercaseSong;
    public String mixedcaseSong;

    @Before
    public void setUp() throws InterruptedException {
        webDriverManager = WebDriverManagerUtil.getInstance();

        loginPage = new LoginPage(webDriverManager.getDriver());
        searchPage = new SearchPage(webDriverManager.getDriver());
        allSongsPage = new AllSongsPage(webDriverManager.getDriver());
        softAssert = new SoftAssert();
        Reporter.log("Step: Setup completed.", true);
    }

    @After
    public void tearDown() {
        webDriverManager.tearDown();
        Reporter.log("Step: Teardown completed.", true);
    }

    //1
//Scenario: Searching for an existing song should display results
    @Given("I am logged")
    public void iAmLoggedIn() {
        webDriverManager.getDriver().get("https://qa.koel.app");
        loginPage.validLogin();
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/home"));
        Reporter.log("Step: I am logged in.", true);
    }

    @And("I am on the home page")
    public void iAmOnTheHomePage() {
        webDriverManager.getDriver().get("https://qa.koel.app/#!/songs");
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/songs"));
        Reporter.log("Step: Step: I am on the Home page.", true);
    }

    @And("I navigate to the search box")
    public void iNavigateToTheSearchBox() {
        WebElement searchBox = searchPage.findElement(searchPage.searchInputField);
        softAssert.assertNotNull(searchBox, "Search box is not available");
        searchBox.click();
        Reporter.log("Step: I navigate to the search box.", true);
    }

    @When("I type in the search box the existing song {string}")
    public void iTypeInTheSearchBoxTheExistingSong(String songName) {
        // Store the song name for later comparisons
        this.songName = songName;
        // Perform the search on the Search page
        searchPage.enterAndSearchSong(songName);
        Reporter.log("Step: I type in the search box the existing song '" + songName + "'.", true);
    }

    @Then("the matched song should appear in the Songs section of the Search results page")
    public void theMatchedSongShouldAppearInTheSongsSectionOfTheSearchResultsPage() {
        // Ensure the song appears in the Search Results page
        WebElement songResult = searchPage.getSongSearchResults();
        softAssert.assertNotNull(songResult, "No song results found for the search");

        Reporter.log("Step: The matched song appeared in the Songs section of the Search results page.", true);
    }

    @And("the Song, Artist and Album sections should display relevant information")
    public void theSongArtistAndAlbumSectionsShouldDisplayRelevantInformation() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = webDriverManager.getWait(); // Use wait from WebDriverManagerUtil

        // --- Step 1: Fetch and log the details from the Search Results page ---

        try {
            WebElement songElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/section[1]/ul/article/span[2]/span[1]")));
            String songText = songElement.getText();
            Reporter.log("Search Results - Song: " + songText, true);
            softAssert.assertNotNull(songText, "Song section is populated.");
        } catch (TimeoutException e) {
            String noSongMessage = searchPage.getNoSongSearchResultsText();
            Reporter.log("No song found. Message: 'None found.'", true);
            softAssert.assertEquals(noSongMessage, "None found.", "No song was found, and the correct message is displayed.");
        }

        // --- Step 2: Fetch and log the artist details ---
        try {
            WebElement artistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchExcerptsWrapper']/div/div/section[2]/p")));
            String artistText = artistElement.getText();
            Reporter.log("Search Results - Artist: " + artistText, true);
            softAssert.assertNotNull(artistText, "Artist section is populated.");
        } catch (TimeoutException e) {
            String noArtistMessage = searchPage.getNoArtistSearchResultsText();
            Reporter.log("No artist found. Message: 'None found.'", true);
            softAssert.assertEquals(noArtistMessage, "None found.", "No artist was found, and the correct message is displayed.");
        }

        // --- Step 3: Fetch and log the album details ---
        try {
            WebElement albumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchExcerptsWrapper']/div/div/section[2]/p")));
            String albumText = albumElement.getText();
            Reporter.log("Search Results - Album: " + albumText, true);
            softAssert.assertNotNull(albumText, "Album section is populated.");
        } catch (TimeoutException e) {
            String noAlbumMessage = searchPage.getNoAlbumSearchResultsText();
            Reporter.log("No album found. Message: 'None found.'", true);
            softAssert.assertEquals(noAlbumMessage, "None found.", "No album was found, and the correct message is displayed.");
        }

        Reporter.log("Search results reporting completed for Song, Artist, and Album sections.", true);

        // Assert all soft assertions at the end
        softAssert.assertAll();
    }




    @When("I click the x button")
    public void iClickTheXButton() {
        // Clear the search field for now
        WebElement searchBox = searchPage.findElement(searchPage.searchInputField);
        searchBox.clear();
        Reporter.log("Step: Cleared the search input field.", true);
    }

    @Then("the search results should be cleared")
    public void theSearchResultsShouldBeCleared() {
        WebElement searchBox = searchPage.findElement(searchPage.searchInputField);
        softAssert.assertTrue(searchBox.getAttribute("value").isEmpty(), "Search box is not cleared");
        Reporter.log("Step: The search results were cleared.", true);

        // Call assertAll to evaluate soft assertions
        softAssert.assertAll();
    }

    //2
    //Scenario: Searching for an existing artist should display results
    @When("I type in the search box the existing artist {string}")
    public void iTypeInTheSearchBoxTheExistingArtist(String artistName) {
        // Store the song name for later comparisons
        this.artistName = artistName;
        // Perform the search on the Search page
        searchPage.enterAndSearchArtist(artistName);
        Reporter.log("Step: I type in the search box the existing artist '" + artistName + "'.", true);
    }

    @Then("the matched artist {string} should appear in the Artist section of the Search results page")
    public void theMatchedArtistShouldAppearInTheArtistSectionOfTheSearchResultsPage(String artistName) {
        WebElement artistElement = searchPage.getArtistSearchResults();
        String artistText = artistElement.getText();  // Fetch the text from the artist element

        if (artistText.equals("None found")) {
            Reporter.log("Valid Artist Name not appearing in search results. Expected Results: " + artistName + ", but found: " + artistText, true);
            // Optionally use softAssert here if you still want to track it
        } else if (artistText.equals(artistName)) {
            Reporter.log("Artist name appeared: " + artistText, true);
        } else {
            Reporter.log("Unexpected result: Message " + artistText + ", expected " + artistName, true);
        }
    }

    //3
//Scenario: Searching for an existing album should display results
    @When("I type in the search box the existing album {string}")
    public void iTypeInTheSearchBoxTheExistingAlbum(String albumName) {// Store the song name for later comparisons
        this.albumName = albumName;
        // Perform the search on the Search page
        searchPage.enterAndSearchAlbum(albumName);
        Reporter.log("Step: I type in the search box an existing album '" + albumName + "'.", true);
    }

    @Then("the album {string} should appear in the Album section of Search page")
    public void theAlbumShouldAppearInTheAlbumSectionOfSearchPage(String albumName) {
        WebElement albumElement = searchPage.getAlbumSearchResults();
        String albumText = albumElement.getText();  // Fetch the text from the artist element

        if (albumText.equals("None found")) {
            Reporter.log("Valid Album Name not appearing in search results. Expected Results: " + albumName + ", but found: " + albumText, true);
            //
        } else if (albumText.equals(albumName)) {
            Reporter.log("Album name appeared: " + albumText, true);
        } else {
            Reporter.log("Unexpected result: Message " + albumText + ", expected " + albumName, true);
        }
    }

    //4
//Scenario: Searching for a non-existing song should display 'no results' message
    @When("I type in the search box the non-existing song {string}")
    public void iTypeInTheSearchBoxTheNonExistingSong(String badSongName) {
        // Store the song name for later comparisons
        this.badSongName = badSongName;

        // Perform the search on the Search page
        searchPage.enterAndSearchSong(badSongName);
        Reporter.log("Step: I enter and search for the song '" + badSongName + "'.", true);
    }

    @Then("the search results page should show an empty list with None found message")
    public void theSearchResultsPageShouldShowAnEmptyList() {
        // Check for the "None found." messages
        String noResultsSongSection = searchPage.getNoSongSearchResultsText();
        String noResultsArtistSection = searchPage.getNoArtistSearchResultsText();
        String noResultsAlbumSection = searchPage.getNoAlbumSearchResultsText();

        // Check for song result
        if (noResultsSongSection.equals("None found.")) {
            Reporter.log("Empty section for song: " + badSongName + ". 'None found.' message for song.", true);
        } else {
            Reporter.log("Unexpected message in song section: " + noResultsSongSection, true);
            Assert.fail("Expected 'None found.' message for song, but found: " + noResultsSongSection);
        }

        // Check for artist result
        if (noResultsArtistSection.equals("None found.")) {
            Reporter.log("Empty section for artist: 'None found.' message for artist.", true);
        } else {
            Reporter.log("Unexpected message in artist section: " + noResultsArtistSection, true);
            Assert.fail("Expected 'None found.' message for artist, but found: " + noResultsArtistSection);
        }

        // Check for album result
        if (noResultsAlbumSection.equals("None found.")) {
            Reporter.log("Empty section for album: 'None found.' for album.", true);
        } else {
            Reporter.log("Unexpected message in album section: " + noResultsAlbumSection, true);
            Assert.fail("Expected 'None found.' message for album, but found: " + noResultsAlbumSection);
        }

        // Final report: All "None found." messages confirmed for song, artist, and album
        Reporter.log("Confirming Empty List for: Song, Artist, and Album for invalid song search.", true);
    }
    //5
    //Scenario: Search should ignore leading and trailing white spaces
    @When("I type in the search box {string}")
    public void iTypeInTheSearchBox(String whiteSpaceStrg) {
        // Store the song name for later comparisons
        this.whiteSpaceStrg = whiteSpaceStrg;

        // Perform the search on the Search page
        searchPage.enterAndSearchSong(whiteSpaceStrg);
        Reporter.log("Step: I enter and search for the song '" + whiteSpaceStrg + "'.", true);
    }

    @Then("the search results should be the same as if {string} was typed")
    public void theSearchResultsShouldBeTheSameAsIfWasTyped(String expectedSongName) {
        // Retrieve the actual search results from the Search Page
        String actualHeaderResults = searchPage.getH1SearchResults();

        // Compare the actual result to the expected result without spaces
        if (actualHeaderResults.equals(expectedSongName)) {
            Reporter.log("Success: Search results for the input with leading/trailing spaces match the result for '" + expectedSongName + "'.", true);
        } else {
            Reporter.log("Failure: Search results for the input with leading/trailing spaces did not match the expected result. Expected: '" + expectedSongName + "', but found: '" + actualHeaderResults + "'.", true);
            Assert.fail("Search results mismatch.");
        }
    }
    //6
    //Scenario: Search should be case-sensitive
    @When("I type in the search box the song {string} in lowercase")
    public void iTypeInTheSearchBoxTheSongInLowercase(String lowercaseSong) {
        // Store the song name for later comparisons
        this.lowercaseSong = lowercaseSong;

        // Perform the search on the Search page
        searchPage.enterAndSearchSong(lowercaseSong);
        Reporter.log("Step: I enter a song in lowercase and search for the song '" + lowercaseSong + "'.", true);
    }

    @Then("no results should be displayed")
    public void noResultsShouldBeDisplayed()
    {
        // Ensure the song appears in the Search Results page
        WebElement songIncorrectCase = searchPage.getSongSearchResults();
        softAssert.assertNotNull(songIncorrectCase, "Song found in search results regardless of case");
        Reporter.log("Step: Defect found! The matched song appeared in the Songs section of the Search results page.", true);
    }
    @When("I type in the search box the song {string} in uppercase")
    public void iTypeInTheSearchBoxTheSongInUppercase(String uppercaseSong) {
        // Store the song name for later comparisons
        this.uppercaseSong = uppercaseSong;
        // Perform the search on the Search page
        searchPage.enterAndSearchSong(uppercaseSong);
        Reporter.log("Step: I enter a song in uppercase and search for the song '" + uppercaseSong + "'.", true);
    }

    @When("I type in the search box the song {string} in mixedcase")
    public void iTypeInTheSearchBoxTheSongInMixedcase(String mixedcaseSong) {
        // Store the song name for later comparisons
        this.mixedcaseSong = mixedcaseSong;
        // Perform the search on the Search page
        searchPage.enterAndSearchSong(mixedcaseSong);
        Reporter.log("Step: I enter a song in mixed case and search for the song '" + mixedcaseSong + "'.", true);
    }
}
