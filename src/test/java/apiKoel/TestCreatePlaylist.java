package apiKoel;

import apiKoel.requests.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TestCreatePlaylist {
    private String token;
    private static final String BASE_URL = "https://qa.koel.app";
    private String validEmail = "giovanna.silva@testpro.io"; // Replace with a valid test email
    private String validPassword = "2024Sprint3!"; // Replace with a valid test password

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL; // Set the base URL for RestAssured

        // Get the token using the LoginRequest class
        LoginRequest loginRequest = new LoginRequest(validEmail, validPassword); // Pass email and password
        Response loginResponse = loginRequest.performLogin(); // Perform login
        token = loginResponse.jsonPath().getString("token"); // Extract token from the response
    }

    @Test
    public void createNewPlaylist() {
        String requestBody = "{ \"name\": \"API New Playlist\", \"rules\": [] }";

        // Make the POST request to create the playlist
        Response response = given()
                .header("Authorization", "Bearer " + token) // Use the token from successful login
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/playlist");

        // Check API Response Status Before Parsing
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Content-Type: " + contentType);

        // Print Response Body for Debugging
        System.out.println("Response Body: " + response.getBody().asString());

        // Handle Non-JSON Responses Gracefully
        if (statusCode == 200 && contentType.contains("application/json")) {
            // Validate the response for playlist creation
            String createdPlaylistName = response.jsonPath().getString("name");
            assertEquals(createdPlaylistName, "API New Playlist", "Playlist name mismatch!");
        } else {
            // If response is not in JSON format or status code is not 200, fail the test
            fail("Failed to create playlist. Expected JSON response but got: " + contentType
                    + ". Status Code: " + statusCode);
        }
    }
}

