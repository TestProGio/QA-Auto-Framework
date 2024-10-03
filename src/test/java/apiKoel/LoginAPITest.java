/*package apiKoel;

import apiKoel.requests.LoginRequest; // Import your LoginRequest class
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert; // Import SoftAssert

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginAPITest {

    // Declare a constant for the base URL of the API
    private static final String BASE_URL = "https://qa.koel.app";
    // Declare variables for valid email and password for testing
    private String validEmail; // Variable to hold a valid email for testing
    private String validPassword; // Variable to hold a valid password for testing
    private SoftAssert softAssert; // Declare a SoftAssert object

    // Setup method to initialize RestAssured and test data
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL; // Set the base URI for RestAssured
        // Initialize valid email and password variables

        // Initialize test data
        validEmail = "giovanna.silva@testpro.io"; // Replace with a valid test email
        validPassword = "2024Sprint3!"; // Replace with a valid test password

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    }

    @Test
    public void testSuccessfulLogin() {
        // Use the valid credentials initialized in the setUp() method
        System.out.println("Testing with Email: " + validEmail + " and Password: " + validPassword);

        // Create login request
        LoginRequest loginRequest = new LoginRequest(validEmail, validPassword);

        // Send login request
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure this endpoint is correct
                .then()
                .extract()
                .response();

        // Log the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check for successful response
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid credentials but found " + response.getStatusCode());

        // Optionally, check for token presence
        String token = response.jsonPath().getString("token");
        assertNotNull(token, "Token should not be null for valid credentials");
    }

    // Test method for login with an invalid email
    @Test
    public void testLoginWithInvalidEmail() {
        // Create a LoginRequest object with an invalid email and a valid password
        LoginRequest loginRequest = new LoginRequest("xyz@testpro.io", validPassword);

        // Send a POST request to the login endpoint
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the response status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid email but found " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

    // Test method for login with an invalid password
    @Test
    public void testLoginWithInvalidPassword() {
        // Create a LoginRequest object with a valid email and an invalid password
        LoginRequest loginRequest = new LoginRequest(validEmail, "abc88");

        // Send POST request to login endpoint and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid password but found " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

    // Test method for login with both an invalid email and an invalid password
    @Test
    public void testLoginWithInvalidEmailAndPassword() {
        // Create a LoginRequest object with an invalid email and invalid password
        LoginRequest loginRequest = new LoginRequest("invalid@testpro.io", "invalidPassword");

        // Send a POST request to the login endpoint
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the response status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid credentials but got " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

    // Test method for login with no email and no password
    @Test
    public void testLoginWithNoEmailAndNoPassword() {
        // Create a LoginRequest object with no email and no password (null or empty)
        LoginRequest loginRequest = new LoginRequest("", "");

        // Send a POST request to the login endpoint
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the response status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing email and password but got " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

    // Test method for login with email and no password
    @Test
    public void testLoginWithEmailNoPassword() {
        // Create a LoginRequest object with a valid email but no password
        LoginRequest loginRequest = new LoginRequest(validEmail, "");

        // Send a POST request to the login endpoint
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the response status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing password but got " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

    // Test method for login with no email and valid password
    @Test
    public void testLoginWithNoEmailValidPassword() {
        // Create a LoginRequest object with no email and a valid password
        LoginRequest loginRequest = new LoginRequest("", validPassword);

        // Send a POST request to the login endpoint
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me") // Ensure endpoint is specified
                .then()
                .extract()
                .response();

        // Assert that the response status code is 401 Unauthorized
        softAssert.assertEquals(response.getStatusCode(), 401, "Defect: Expected status code 401 for missing email but got " + response.getStatusCode());

        // Assert all soft assertions
        softAssert.assertAll();
    }

}

 */
package apiKoel;

import apiKoel.requests.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginAPITest {

    private static final String BASE_URL = "https://qa.koel.app";
    private String validEmail;
    private String validPassword;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        validEmail = "giovanna.silva@testpro.io"; // Replace with a valid test email
        validPassword = "2024Sprint3!"; // Replace with a valid test password
        softAssert = new SoftAssert();
    }

    @Test
    public void testSuccessfulLogin() {
        LoginRequest loginRequest = new LoginRequest(validEmail, validPassword);
        Response response = sendLoginRequest(loginRequest);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid credentials but found " + response.getStatusCode());
        String token = response.jsonPath().getString("token");
        assertNotNull(token, "Token should not be null for valid credentials");
    }

    @Test
    public void testLoginWithInvalidEmail() {
        LoginRequest loginRequest = new LoginRequest("xyz@testpro.io", validPassword);
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid email but found " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithInvalidPassword() {
        LoginRequest loginRequest = new LoginRequest(validEmail, "wrongPassword");
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid password but found " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailAndPassword() {
        LoginRequest loginRequest = new LoginRequest("invalid@testpro.io", "invalidPassword");
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid credentials but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithNoEmailAndNoPassword() {
        LoginRequest loginRequest = new LoginRequest("", "");
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing email and password but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithEmailNoPassword() {
        LoginRequest loginRequest = new LoginRequest(validEmail, "");
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing password but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithNoEmailValidPassword() {
        LoginRequest loginRequest = new LoginRequest("", validPassword);
        Response response = sendLoginRequest(loginRequest);
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing email but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    private Response sendLoginRequest(LoginRequest loginRequest) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/me")
                .then()
                .extract()
                .response();
    }
}


