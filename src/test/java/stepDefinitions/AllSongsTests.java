
package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import java.util.Optional;
import org.testng.asserts.SoftAssert;
import pages.AllSongsPage;
import pages.LoginPage;
import utils.WebDriverManagerUtil;

public class AllSongsTests {
    private WebDriverManagerUtil webDriverManager;
    private AllSongsPage allSongsPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;
/*
    @Before
    public void setUp() throws InterruptedException {
        webDriverManager = new WebDriverManagerUtil();
        webDriverManager.setup();
        allSongsPage = new AllSongsPage(webDriverManager.getDriver());
        loginPage = new LoginPage(webDriverManager.getDriver());
        softAssert = new SoftAssert();
        Reporter.log("Step: Setup completed.", true);
    }

 */
@Before
public void setUp() throws InterruptedException {
    webDriverManager = WebDriverManagerUtil.getInstance();
    allSongsPage = new AllSongsPage(webDriverManager.getDriver());
    loginPage = new LoginPage(webDriverManager.getDriver());
    softAssert = new SoftAssert();
    Reporter.log("Step: Setup completed.", true);
}


    @After
    public void tearDown() {
        webDriverManager.tearDown();
        Reporter.log("Step: Teardown completed.", true);
    }

    @Given("the user is logged into the Koel App")
    public void userIsLoggedIntoKoel() {
        webDriverManager.getDriver().get("https://qa.koel.app");
        // Assuming there is a validLogin method in LoginPage to handle the login process
        loginPage.validLogin();
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/home"));
        Reporter.log("Step: I am logged in.", true);
    }

    @When("the user navigates to the All Songs page")
    public void theUserNavigatesToThePage() throws InterruptedException {
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/home"));
        allSongsPage.clickLeftMenuAllSongs();
        Reporter.log("Step: Navigated to All Songs page.", true);
    }

    @Then("the All Songs page should display a list of songs")
    public void thePageShouldDisplayAListOfSongs() {
        int songCount = allSongsPage.getAllSongsCount();
        softAssert.assertTrue(songCount > 0, "No songs are displayed on the All Songs page.");
        Reporter.log("Step: Songs are displayed. Total songs: " + songCount, true);
    }

    @And("the total count of songs should be displayed")
    public void theTotalCountOfSongsShouldBeDisplayed() throws InterruptedException {
        int totalSongs = allSongsPage.getAllSongsCount();
        softAssert.assertTrue(totalSongs > 0, "Total songs count is not displayed correctly.");
        Thread.sleep(2000);
        Reporter.log("Step: Total count of songs: " + totalSongs, true);
    }

    @And("the total duration of all songs should be displayed")
    public void theTotalDurationOfAllSongsShouldBeDisplayed() throws InterruptedException {
        String totalDuration = allSongsPage.getAllSongsDuration();
        softAssert.assertFalse(totalDuration.isEmpty(), "Total duration of songs is not displayed.");
        Thread.sleep(2000);
        Reporter.log("Step: Total duration of all songs: " + totalDuration, true);
    }
    @And("each song should have an ID")
    public void eachSongShouldHaveAnID() {
        // Initialize counters
        int songsWithID = 0;
        int songsWithoutID = 0;

        // Get the total number of songs from the page
        int totalSongs = allSongsPage.getAllSongsCount();

        // Loop through each song index to check for IDs
        for (int i = 1; i <= totalSongs; i++) {
            try {
                // Check if the song has an ID using the updated songsHaveID method
                boolean hasID = allSongsPage.songsHaveID(i);
                if (hasID) {
                    songsWithID++;
                } else {
                    songsWithoutID++;
                    Reporter.log("Song at index " + i + " is missing an ID.", true);
                }
            } catch (Exception e) {
                // Handle any exceptions and consider them as missing ID cases
                songsWithoutID++;
                Reporter.log("Exception while checking ID for song at index " + i + ": " + e.getMessage(), true);
            }
        }

        // Log summary
        Reporter.log("Total songs with ID: " + songsWithID, true);
        Reporter.log("Total songs without ID: " + songsWithoutID, true);

        // Assert that there should be no songs without an ID
        softAssert.assertEquals(songsWithoutID, 0,
                "This many songs have IDs: " + songsWithID + ", and this many don't: " + songsWithoutID + ", from a total of " + totalSongs);

    }


    @And("each song should have a Title")
    public void eachSongShouldHaveATitle() {
        // Initialize counters
        int songsWithTitle = 0;
        int songsWithoutTitle = 0;

        // Initialize SoftAssert for non-blocking assertions
        SoftAssert softAssert = new SoftAssert();

        // Get the total number of songs from the page
        int totalSongs = allSongsPage.getAllSongsCount();

        // Loop through each song index to check for Titles
        for (int i = 1; i <= totalSongs; i++) {
            try {
                // Get the song title using the updated getSongTitle method
                String songTitle = allSongsPage.getSongTitle(i);

                // Check if the title is not empty
                if (!songTitle.isEmpty()) {
                    songsWithTitle++;
                } else {
                    songsWithoutTitle++;
                }
            } catch (Exception e) {
                // Handle the exception (e.g., element not found or timeout) and count it as a missing title
                songsWithoutTitle++;
                continue; // Move to the next song
            }
        }

        // Verify all songs have Titles using a soft assertion
        softAssert.assertEquals(songsWithoutTitle, 0,
                "This many songs have Titles: " + songsWithTitle + ", and this many don't: " + songsWithoutTitle + ", from a total of " + totalSongs);
    }


    @And("each song should have an Artist")
    public void eachSongShouldHaveAnArtist() {
        // Initialize counters
        int songsWithArtist = 0;
        int songsWithoutArtist = 0;

        // Get the total number of songs from the page
        int totalSongs = allSongsPage.getAllSongsCount();

        // Loop through each song index to check for Artists
        for (int i = 1; i <= totalSongs; i++) {
            try {
                // Get the song artist using the getSongArtist method
                String songArtist = allSongsPage.getSongArtist(i);

                // Check if the artist name is not empty and not "Unknown Artist"
                if (!songArtist.isEmpty() && !"Unknown Artist".equals(songArtist)) {
                    songsWithArtist++;
                } else {
                    songsWithoutArtist++;
                }
            } catch (Exception e) {
                // Handle the exception (e.g., element not found or timeout) and count it as a missing artist
                songsWithoutArtist++;
                continue; // Move to the next song
            }
        }

        // Verify all songs have Artists using a soft assertion
        softAssert.assertEquals(songsWithoutArtist, 0,
                "This many songs have Artists: " + songsWithArtist + ", and this many don't: " + songsWithoutArtist + ", from a total of " + totalSongs);

    }

    @And("each song should have an Album")
    public void eachSongShouldHaveAnAlbum() {
        // Initialize counters
        int songsWithAlbum = 0;
        int songsWithoutAlbum = 0;

        // Get the total number of songs from the page
        int totalSongs = allSongsPage.getAllSongsCount();

        // Loop through each song index to check for album titles
        for (int i = 1; i <= totalSongs; i++) {
            try {
                // Get the album title using the updated getSongAlbum method
                String albumTitle = allSongsPage.getSongAlbum(i);

                // Check if the album title is not empty and not "Unknown Album"
                if (!albumTitle.isEmpty() && !"Unknown Album".equals(albumTitle)) {
                    songsWithAlbum++;
                } else {
                    songsWithoutAlbum++;
                    Reporter.log("Song at index " + i + " is missing an Album title.", true);
                }
            } catch (Exception e) {
                // Handle the exception (e.g., element not found or timeout) and count it as a missing album
                songsWithoutAlbum++;
                Reporter.log("Exception while checking Album for song at index " + i + ": " + e.getMessage(), true);
            }
        }

        // Log summary
        Reporter.log("Total songs with Album: " + songsWithAlbum, true);
        Reporter.log("Total songs without Album: " + songsWithoutAlbum, true);

        // Verify that there should be no songs without an Album title using a soft assertion
        softAssert.assertEquals(songsWithoutAlbum, 0,
                "This many songs have Albums: " + songsWithAlbum + ", and this many don't: " + songsWithoutAlbum + ", from a total of " + totalSongs);

    }

    @And("each song should have a Time Duration")
    public void eachSongShouldHaveATimeDuration() {
        // Initialize counters
        int songsWithDuration = 0;
        int songsWithoutDuration = 0;

        // Get the total number of songs from the page
        int totalSongs = allSongsPage.getAllSongsCount();

        // Loop through each song index to check for duration
        for (int i = 1; i <= totalSongs; i++) {
            try {
                // Get the song duration using the updated getSongDuration method
                String duration = allSongsPage.getSongDuration(i);

                // Check if the duration is not empty
                if (!duration.isEmpty()) {
                    songsWithDuration++;
                } else {
                    songsWithoutDuration++;
                    Reporter.log("Song at index " + i + " is missing a Time Duration.", true);
                }
            } catch (Exception e) {
                // Handle the exception (e.g., element not found or timeout) and count it as a missing duration
                songsWithoutDuration++;
                Reporter.log("Exception while checking Time Duration for song at index " + i + ": " + e.getMessage(), true);
            }
        }

        // Log summary
        Reporter.log("Total songs with Duration: " + songsWithDuration, true);
        Reporter.log("Total songs without Duration: " + songsWithoutDuration, true);

        // Verify that there should be no songs without a duration using a soft assertion
        softAssert.assertEquals(songsWithoutDuration, 0,
                "This many songs have Duration: " + songsWithDuration + ", and this many don't: " + songsWithoutDuration + ", from a total of " + totalSongs);

        // Assert all the soft checks
        softAssert.assertAll();
    }

}

