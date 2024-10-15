package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends BasePage {
   public By header1SearchPage= By.xpath("//*[@id='searchExcerptsWrapper']/header/div[2]/h1/span/strong");
    public By searchInputField = By.cssSelector("div#searchForm > input[name='q']");
    public By searchResultsSong = By.xpath("//*[@id='searchExcerptsWrapper']/div/div/section[1]/ul/article/span[2]/span[1]");
    public By searchResultsArtist = By.xpath("//*[@id='searchExcerptsWrapper']/div/div/section[2]/p");
    public By searchResultsAlbum = By.xpath("//*[@id='searchExcerptsWrapper']/div/div/section[3]/p");
    public By searchNoArtistFoundMessage= By.xpath("//section[@id='searchExcerptsWrapper']//section[@class='artists']/p[.='None found.']");
    public By searchNoAlbumFoundMessage= By.xpath("//section[@id='searchExcerptsWrapper']//section[@class='albums']/p[.='None found.']");
    public By searchNoSongFoundMessage= By.xpath("//section[@id='searchExcerptsWrapper']//section[@class='songs']/p[.='None found.']");

    public SearchPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    // Methods
    public String getH1SearchResults() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
            WebElement headerOneSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(header1SearchPage));
            return headerOneSearch.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            // Return an empty string or a specific message indicating no element found
            return "";
        }
    }
    public String getNoSongSearchResultsText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
            WebElement noSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchNoSongFoundMessage));
            return noSongElement.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            // Return an empty string or a specific message indicating no element found
            return "";
        }
    }

    public String getNoAlbumSearchResultsText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
            WebElement noAlbumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchNoAlbumFoundMessage));
            return noAlbumElement.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            // Return an empty string or a specific message indicating no element found
            return "";
        }
    }

    public String getNoArtistSearchResultsText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
            WebElement noArtistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchNoArtistFoundMessage));
            return noArtistElement.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            // Return an empty string or a specific message indicating no element found
            return "";
        }
    }

    public void enterAndSearchSong(String searchSong) {
        // Input Song Name to search
        WebElement nameInput = findElement(searchInputField);
        nameInput.sendKeys(searchSong);
        nameInput.sendKeys(Keys.RETURN); // Simulate pressing Enter key
    }


    public void enterAndSearchArtist(String searchArtist) {
        // Input Artist Name to search
        WebElement nameInput = findElement(searchInputField);
        nameInput.sendKeys(searchArtist);
        nameInput.sendKeys(Keys.RETURN); // Simulate pressing Enter key
    }
    public void enterAndSearchAlbum(String searchAlbum) {
        // Input Album Name to search
        WebElement nameInput = findElement(searchInputField);
        nameInput.sendKeys(searchAlbum);
        nameInput.sendKeys(Keys.RETURN); // Simulate pressing Enter key
    }
    public WebElement getSongSearchResults() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsSong));
        } catch (NoSuchElementException | TimeoutException e) {
            // Handle the case where the element is not found; return null or throw an exception
            return null; // or you can throw a custom exception
        }
    }

    public WebElement getArtistSearchResults() {

        return findElement(searchResultsArtist);
    }
    public WebElement getAlbumSearchResults() {

        return findElement(searchResultsAlbum);
    }
}
