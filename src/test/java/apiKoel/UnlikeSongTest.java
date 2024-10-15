
package apiKoel;

import apiKoel.requests.LoginRequest;
import apiKoel.requests.SongRequest;
import apiKoel.responses.SongResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UnlikeSongTest {

    // Base URL of the API
    private static final String BASE_URL = System.getenv("KOEL_BASE_URL") != null ?
            System.getenv("KOEL_BASE_URL") :
            "https://qa.koel.app"; // Default URL

    private String authToken;
    private String validEmail;
    private String validPassword;
    private SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        // Set RestAssured base URI globally
        RestAssured.baseURI = BASE_URL;

        // Initialize valid credentials and SoftAssert
        validEmail = "giovanna.silva@testpro.io"; // Replace with a valid test email
        validPassword = "2024Sprint3!"; // Replace with a valid test password
        softAssert = new SoftAssert();

        // Perform login to get the auth token
        LoginRequest loginRequest = new LoginRequest(validEmail, validPassword);
        Response response = loginRequest.performLogin(); // Assuming performLogin() method handles the API call

        // Print response details
        System.out.println("Login Response Status Code: " + response.getStatusCode());
        System.out.println("Login Response Body: " + response.getBody().asString());

        // Check the response status code and token
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid credentials but found " + response.getStatusCode());
        authToken = response.jsonPath().getString("token");

        // Ensure the token is not null
        assertNotNull(authToken, "Token should not be null for valid credentials");
    }

    @Test
    public void likeAndUnlikeSongTestWithPOJO() {
        // Create request body using POJO
        String songId = "3dfc5899aed1fb7edba2ce89d8d8ec45";
        SongRequest songRequest = new SongRequest(songId);

        // Perform the like action
        Response likeResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(songRequest)
                .post("/api/interaction/like");

        // Print the response for debugging
        System.out.println("Like Response Status Code: " + likeResponse.getStatusCode());
        System.out.println("Like Response Body: " + likeResponse.getBody().asString());
        System.out.println("Like Response Content-Type: " + likeResponse.getContentType());

        // Check if the response status is 200 and the Content-Type is JSON
        if (likeResponse.getStatusCode() == 200 && likeResponse.getContentType().contains("application/json")) {
            SongResponse songResponse = likeResponse.as(SongResponse.class);

            // Verifications - Like action
            Assert.assertTrue(songResponse.isLiked(), "The song should be marked as liked");
        } else {
            // Log the error or handle it
            Assert.fail("Failed to like the song. Received non-JSON response or error. Content-Type: "
                    + likeResponse.getContentType());
        }

        // Perform the unlike action
        Response unlikeResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(songRequest)
                .post("/api/interaction/like");

        // Print the response for debugging
        System.out.println("Unlike Response Status Code: " + unlikeResponse.getStatusCode());
        System.out.println("Unlike Response Body: " + unlikeResponse.getBody().asString());
        System.out.println("Unlike Response Content-Type: " + unlikeResponse.getContentType());

        // Check if the response status is 200 and the Content-Type is JSON
        if (unlikeResponse.getStatusCode() == 200 && unlikeResponse.getContentType().contains("application/json")) {
            SongResponse songResponse = unlikeResponse.as(SongResponse.class);

            // Verifications - Unlike action
            Assert.assertFalse(songResponse.isLiked(), "The song should not be marked as liked after unliking");
        } else {
            // Log the error or handle it
            Assert.fail("Failed to unlike the song. Received non-JSON response or error. Content-Type: "
                    + unlikeResponse.getContentType());
        }
    }

}

