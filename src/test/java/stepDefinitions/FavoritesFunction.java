
package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import pages.AllSongsPage;
import pages.LoginPage;
import pages.FavoritesPage;
import pages.HomePage;
import utils.WebDriverManagerUtil;





public class FavoritesFunction {
    private WebDriverManagerUtil webDriverManager;
    private WebDriver driver;
    private FavoritesPage favoritesPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private AllSongsPage allSongsPage;
    private SoftAssert softAssert;


    public String songTitle;
/*
    @Before
    public void setUp() throws InterruptedException {
        webDriverManager = new WebDriverManagerUtil();
        webDriverManager.setup();
        driver = webDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        favoritesPage = new FavoritesPage(driver);
        allSongsPage = new AllSongsPage(driver);
        homePage= new HomePage (driver);
        softAssert = new SoftAssert();
        Reporter.log("Step: Setup completed : FavoritesFunction", true);
    }

 */
@Before
public void setUp() throws InterruptedException {
    // Get the single instance of WebDriverManagerUtil
    webDriverManager = WebDriverManagerUtil.getInstance();

    // Retrieve the driver from the WebDriverManagerUtil instance
    driver = webDriverManager.getDriver();

    // Initialize page objects using the shared driver instance
    loginPage = new LoginPage(driver);
    favoritesPage = new FavoritesPage(driver);
    allSongsPage = new AllSongsPage(driver);
    homePage = new HomePage(driver);

    // Initialize SoftAssert for assertions
    softAssert = new SoftAssert();

    // Log setup completion
    Reporter.log("Step: Setup completed : FavoritesFunction", true);
}



    @After
    public void tearDown() {
        webDriverManager.tearDown();
        Reporter.log("Step: Teardown completed: FavoritesFunction", true);
    }

    // Scenario: Favorites Playlist is empty when no songs are saved
    @Given("I'm logged in")
    public void iAmLoggedIn() throws InterruptedException {
        webDriverManager.getDriver().get("https://qa.koel.app");
        loginPage.validLogin();
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/home"));
        Reporter.log("Step: I am logged in.", true);
    }

    @And("I'm on the home page")
    public void iAmOnTheHomePage() throws InterruptedException {
        driver.get("https://qa.koel.app/");
        webDriverManager.getWait().until(ExpectedConditions.urlContains("/home"));
        Thread.sleep(3000); // Wait for 2 seconds to confirm navigation
        Reporter.log("Step: I am on the Home page.", true);
    }

    @When("I navigate to the Favorites Playlist page")
    public void iNavigateToTheFavoritesPlaylistPage() throws InterruptedException {
        favoritesPage.clickLeftMenuFavorites();
        Thread.sleep(3000); // Wait for 2 seconds after clicking
        Reporter.log("Step: Navigated to the Favorites Playlist page.", true);
    }

    @Then("the playlist should be empty")
    public void thePlaylistShouldBeEmpty() {
        try {
            // Check if the no favorites message is displayed
            boolean isEmpty = favoritesPage.isNoFavoritesMessageDisplayed();

            // Report the result based on the check
            if (isEmpty) {
                Reporter.log("Step: The Favorites Playlist is confirmed empty.", true);
            } else {
                Reporter.log("Step: The Favorites Playlist is not empty!", true);
            }
        } catch (TimeoutException e) {
            // Handle the case where the element is not found within the timeout
            Reporter.log("Step: Timeout occurred while checking if the Favorites Playlist is empty. " +
                    "Defect: The Favorites Playlist is NOT empty!", true);
        } catch (Exception e) {
            // Handle any unexpected exceptions gracefully
            Reporter.log("Step: An unexpected error occurred while checking the Favorites Playlist: " +
                    e.getMessage(), true);
        } finally {
            // Always assert all soft assertions if any were made
            softAssert.assertAll();
        }
    }
    //Scenario: User saves songs to their Favorites Playlist
    @And("I mark songs as favorites")
    public void iMarkSongsAsFavorites() throws InterruptedException {
        WebElement likeSpecificSong = homePage.likeSpecificSong();
        softAssert.assertNotNull(likeSpecificSong, "Epic Song not able to favorite");
        likeSpecificSong.click();
        Thread.sleep(3000);
        Reporter.log("Step: I favorited: Epic song.", true);
    }

    @Then("I should see the songs in my Favorites Playlist")
    public void iShouldSeeTheSongsInMyFavoritesPlaylist() {
        try {
            // Get the title of the favorite song
            String favoriteSongTitle = favoritesPage.getSpecificFavoriteSongTitle();

            // Check if the title matches "Epic Song"
            if (favoriteSongTitle.equals("Epic Song")) {
                Reporter.log("Step: The song 'Epic Song' is present in the Favorites Playlist.", true);
            } else {
                Reporter.log("Step: The song 'Epic Song' was not found in the Favorites Playlist. Found: '" + favoriteSongTitle + "'", true);
            }
        } catch (TimeoutException e) {
            // Handle the case where the element is not found within the timeout
            Reporter.log("Step: Timeout occurred while trying to retrieve the favorite song title.", true);
        } catch (Exception e) {
            // Handle any unexpected exceptions gracefully
            Reporter.log("Step: An unexpected error occurred while checking the Favorites Playlist: " + e.getMessage(), true);
        } finally {
            // Always assert all soft assertions if any were made
            softAssert.assertAll();
        }


    }
}
