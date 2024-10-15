package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverManagerUtil;

public class PositiveTests {

    private WebDriverManagerUtil webDriverManager;
    private HomePage homePage;
    private LoginPage loginPage;
/*
    @Before
    public void setUp() throws InterruptedException {
        // Initialize WebDriverManagerUtil and set up WebDriver
        webDriverManager = new WebDriverManagerUtil();
        webDriverManager.setup();

        // Initialize page objects using the WebDriver from WebDriverManagerUtil
        homePage = new HomePage(webDriverManager.getDriver());
        loginPage = new LoginPage(webDriverManager.getDriver());
    }

 */
@Before
public void setUp() throws InterruptedException {
    // Initialize WebDriverManagerUtil and set up WebDriver
    webDriverManager = WebDriverManagerUtil.getInstance();

    // Initialize page objects using the WebDriver from WebDriverManagerUtil
    homePage = new HomePage(webDriverManager.getDriver());
    loginPage = new LoginPage(webDriverManager.getDriver());
}
    @And("I open Koel Login Page")
    public void iOpenKoelLoginPage() throws InterruptedException {
        webDriverManager.getDriver().get("https://qa.koel.app/");
        System.out.println("Browser opened the Koel login page.");
        Thread.sleep(2000);
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) throws InterruptedException {
        loginPage.provideEmail(email);
        System.out.println("Email entered: " + email);
        Thread.sleep(2000);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) throws InterruptedException {
        loginPage.providePassword(password);
        System.out.println("Password entered: " + password);
        Thread.sleep(2000);
    }

    @And("I click submit")
    public void iClickSubmit() throws InterruptedException {
        loginPage.clickSubmit();
        System.out.println("Clicked Submit Button.");
        Thread.sleep(2000);
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() throws InterruptedException {
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed(), "User avatar is not displayed. Login might have failed.");
        System.out.println("Successfully logged in.");
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        // Use WebDriverManagerUtil's tearDown method to quit the driver
        webDriverManager.tearDown();
    }
}