/*
package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class AccountCreation {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("The user is on the Registration page")
    public void theUserIsOnTheRegistrationPage() {
        driver.get("https://qa.koel.app/registration"); // Update with actual URL
    }

    @When("The user enters a valid email {string}")
    public void theUserEntersAValidEmail(String email) throws InterruptedException {
        //WebElement emailInput = driver.findElement(By.id("email")); // Update with actual locator
        //emailInput.sendKeys(email);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
        System.out.println("Email Entered");
        Thread.sleep(2000);

    }

    @And("The user enters a valid password {string}")
    public void theUserEntersAValidPassword(String password) {
        WebElement passwordInput = driver.findElement(By.id("password")); // Update with actual locator
        passwordInput.sendKeys(password);
    }

    @And("The user submits the registration form")
    public void theUserSubmitsTheRegistrationForm() {
        WebElement submitButton = driver.findElement(By.id("submit")); // Update with actual locator
        submitButton.click();
    }

    @Then("A confirmation message should be displayed {string}")
    public void aConfirmationMessageShouldBeDisplayed(String message) {
        WebElement confirmationMessage = driver.findElement(By.id("confirmationMessage")); // Update with actual locator
        Assert.assertEquals(confirmationMessage.getText(), message);
    }

    @Then("The password should be sent to the user's email box")
    public void thePasswordShouldBeSentToTheUsersEmailBox() {
        // This step requires email verification which might need integration with email testing tools or mocks
        Assert.fail("Email verification is not implemented");
    }

    @Then("The user should be able to log in using the registered email and password")
    public void theUserShouldBeAbleToLogInUsingTheRegisteredEmailAndPassword() {
        // Navigate to login page
        driver.get("https://qa.koel.app/login"); // Update with actual URL
        // Enter email and password
        WebElement emailInput = driver.findElement(By.id("email")); // Update with actual locator
        emailInput.sendKeys("test@testpro.io"); // Use actual email for test
        WebElement passwordInput = driver.findElement(By.id("password")); // Update with actual locator
        passwordInput.sendKeys("password123!"); // Use actual password for test
        WebElement loginButton = driver.findElement(By.id("login")); // Update with actual locator
        loginButton.click();
        // Verify login
        WebElement userAvatar = driver.findElement(By.id("userAvatar")); // Update with actual locator
        Assert.assertTrue(userAvatar.isDisplayed());
    }

    @Then("The user data should be correctly saved in the database with encrypted password")
    public void theUserDataShouldBeCorrectlySavedInTheDatabaseWithEncryptedPassword() {
        // Database verification logic should be implemented here, possibly using JDBC or another method
        Assert.fail("Database verification is not implemented");
    }

    @When("The user enters an invalid email {string}")
    public void theUserEntersAnInvalidEmail(String email) {
        WebElement emailInput = driver.findElement(By.id("email")); // Update with actual locator
        emailInput.sendKeys(email);
    }

    @Then("An error message should be displayed {string}")
    public void anErrorMessageShouldBeDisplayed(String errorMessage) {
        WebElement errorElement = driver.findElement(By.id("errorMessage")); // Update with actual locator
        Assert.assertEquals(errorElement.getText(), errorMessage);
    }

    @Given("The email {string} is already registered")
    public void theEmailIsAlreadyRegistered(String email) {
        // Precondition setup to ensure email is already registered
        // This might involve direct database access or a specific API call
        Assert.fail("Email registration setup is not implemented");
    }
}
*/
