/*package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavoritesPage extends BasePage {

    public FavoritesPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    // Locators

    public By leftMenuFavorites = By.cssSelector("li.playlist.favorites a");
    public By noFavoritesMessage = By.xpath("//section[@id='favoritesWrapper']/div[@class='screen-placeholder']//div[@class='text']");
    public By favoriteSongTitle = By.xpath("//*[@id='favoritesWrapper']/div/div/div[1]/table/tr[*]/td[2]");
    public By clickToNotFavorite = By.xpath("//button[@data-test='like-btn']");
    public By rightClickPlay = By.cssSelector("li.playback");
    public By rightClickGoToAlbum = By.cssSelector("li.go-to-album");
    public By rightClickGoToArtist = By.cssSelector("li.go-to-artist");
    public By rightClickAddTo = By.cssSelector("li.has-sub");
    public By rightClickDownload = By.cssSelector("li.download");
    public By rightClickCopyURL = By.cssSelector("li.copy-url");

    private WebDriverWait wait;

    public void initializeWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    // Methods to interact with locators

    // Method to click on the Favorites link in the left menu
    public void clickLeftMenuFavorites() {
        // Wait until the Favorites link is visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(leftMenuFavorites));

        // Find the Favorites link and click on it
        driver.findElement(leftMenuFavorites).click();
    }


    // Check if the no favorites message is displayed
    public boolean isNoFavoritesMessageDisplayed() {
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.visibilityOfElementLocated(noFavoritesMessage));
        return driver.findElement(noFavoritesMessage).isDisplayed();
    }

    // Get the title of a favorite song
    public String getFavoriteSongTitle() {
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoriteSongTitle));
        return driver.findElement(favoriteSongTitle).getText();
    }

    // Click to unlike a favorite song
    public void clickToUnlikeFavorite() {
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(clickToNotFavorite));
        driver.findElement(clickToNotFavorite).click();
    }

    // Right-click and select Play option on a song
    public void rightClickPlaySong(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickPlay));
        driver.findElement(rightClickPlay).click();
    }

    // Right-click and go to album
    public void rightClickGoToAlbum(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickGoToAlbum));
        driver.findElement(rightClickGoToAlbum).click();
    }

    // Right-click and go to artist
    public void rightClickGoToArtist(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickGoToArtist));
        driver.findElement(rightClickGoToArtist).click();
    }

    // Right-click and add to playlist
    public void rightClickAddToPlaylist(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickAddTo));
        driver.findElement(rightClickAddTo).click();
    }

    // Right-click and download a song
    public void rightClickDownloadSong(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickDownload));
        driver.findElement(rightClickDownload).click();
    }

    // Right-click and copy URL of the song
    public void rightClickCopySongURL(WebElement songElement) {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        initializeWait();  // Ensure wait is initialized
        wait.until(ExpectedConditions.elementToBeClickable(rightClickCopyURL));
        driver.findElement(rightClickCopyURL).click();
    }

    // Additional methods for interacting with elements can be added here
}

 */
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavoritesPage extends BasePage {

    private WebDriverWait wait;

    public FavoritesPage(WebDriver givenDriver) {
        super(givenDriver);
        // Initialize WebDriverWait in the constructor
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    public By leftMenuFavorites = By.cssSelector("li.playlist.favorites a");
    public By noFavoritesMessage = By.xpath("//section[@id='favoritesWrapper']/div[@class='screen-placeholder']//div[@class='text']");
    public By favoriteSongTitle = By.xpath("//*[@id='favoritesWrapper']/div/div/div[1]/table/tr[*]/td[2]");
    public By clickToNotFavorite = By.xpath("//button[@data-test='like-btn']");
    public By rightClickPlay = By.cssSelector("li.playback");
    public By rightClickGoToAlbum = By.cssSelector("li.go-to-album");
    public By rightClickGoToArtist = By.cssSelector("li.go-to-artist");
    public By rightClickAddTo = By.cssSelector("li.has-sub");
    public By rightClickDownload = By.cssSelector("li.download");
    public By rightClickCopyURL = By.cssSelector("li.copy-url");

    // Method to click on the Favorites link in the left menu
    public void clickLeftMenuFavorites() throws InterruptedException {
        // Wait until the Favorites link is visible and clickable
        wait.until(ExpectedConditions.elementToBeClickable(leftMenuFavorites));
        // Find the Favorites link and click on it
        driver.findElement(leftMenuFavorites).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Check if the no favorites message is displayed
    public boolean isNoFavoritesMessageDisplayed() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noFavoritesMessage));
        Thread.sleep(2000); // Pause for 2 seconds
        return driver.findElement(noFavoritesMessage).isDisplayed();
    }

    // Get the title of a favorite song
    public String getSpecificFavoriteSongTitle() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoriteSongTitle));
        Thread.sleep(2000); // Pause for 2 seconds
        return driver.findElement(favoriteSongTitle).getText();
    }



    // Click to unlike a favorite song
    public void clickToUnlikeFavorite() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(clickToNotFavorite));
        driver.findElement(clickToNotFavorite).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and select Play option on a song
    public void rightClickPlaySong(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickPlay));
        driver.findElement(rightClickPlay).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and go to album
    public void rightClickGoToAlbum(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickGoToAlbum));
        driver.findElement(rightClickGoToAlbum).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and go to artist
    public void rightClickGoToArtist(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickGoToArtist));
        driver.findElement(rightClickGoToArtist).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and add to playlist
    public void rightClickAddToPlaylist(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickAddTo));
        driver.findElement(rightClickAddTo).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and download a song
    public void rightClickDownloadSong(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickDownload));
        driver.findElement(rightClickDownload).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

    // Right-click and copy URL of the song
    public void rightClickCopySongURL(WebElement songElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.contextClick(songElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(rightClickCopyURL));
        driver.findElement(rightClickCopyURL).click();
        Thread.sleep(2000); // Pause for 2 seconds
    }

}

