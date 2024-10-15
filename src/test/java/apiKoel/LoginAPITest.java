/*
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

 */
package apiKoel;

import apiKoel.requests.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        // Create a new instance of LoginRequest with valid credentials
        LoginRequest loginRequest = new LoginRequest(validEmail, validPassword);
        Response response = loginRequest.performLogin(); // No need to pass parameters here

        // Print the email, password, response status code, and response body
        System.out.println("Email: " + validEmail);
        System.out.println("Password: " + validPassword);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check the response status code and token
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid credentials but found " + response.getStatusCode());
        String token = response.jsonPath().getString("token");

        // Print the token
        System.out.println("Token: " + token);

        assertNotNull(token, "Token should not be null for valid credentials");
    }


    @Test
    public void testLoginWithInvalidEmail() {
        LoginRequest loginRequest = new LoginRequest("xyz@testpro.io", validPassword);
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid email but found " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithInvalidPassword() {
        LoginRequest loginRequest = new LoginRequest(validEmail, "wrongPassword");
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid password but found " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailAndPassword() {
        LoginRequest loginRequest = new LoginRequest("invalid@testpro.io", "invalidPassword");
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for invalid credentials but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithNoEmailAndNoPassword() {
        LoginRequest loginRequest = new LoginRequest("", ""); // Pass empty credentials
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing email and password but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithEmailNoPassword() {
        LoginRequest loginRequest = new LoginRequest(validEmail, ""); // Pass valid email and empty password
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing password but got " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test
    public void testLoginWithNoEmailValidPassword() {
        LoginRequest loginRequest = new LoginRequest("", validPassword); // Pass empty email and valid password
        Response response = loginRequest.performLogin(); // No need to pass parameters here
        softAssert.assertEquals(response.getStatusCode(), 401, "DEFECT: Expected status code 401 for missing email but got " + response.getStatusCode());
        softAssert.assertAll();
    }
}

