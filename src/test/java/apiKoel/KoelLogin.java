package apiKoel;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class KoelLogin {
    // Base URL for the API
    private static final String BASE_URL = "https://qa.koel.app/";
    // Your pre-existing authentication token
    private static final String AUTH_TOKEN = "338367|M1BSl3bI1kUtbHfTZOdvprhhqPvh0DULZHhB6ngy";

    /**
     * Method to return the auth token.
     * @return the authentication token.
     */
    public String koelLogin() {
        // Normally, you'd do the login process and extract the token here.
        // Since you already have a static token, we will return it directly.
        return AUTH_TOKEN;
    }

    /**
     * Test to verify an API call using the existing token.
     */
    @Test
    public void verifyLogin() {
        // Endpoint for the API
        String endpoint = "/api/me";

        // Make an authenticated GET request
        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + AUTH_TOKEN)  // Use your provided token
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)  // Expecting a 200 OK status
                .extract()
                .response();

        // Print the response for debugging purposes
        System.out.println("Response: " + response.asString());

        // Add assertions as needed to verify the content of the response
        String expectedEmail = "giovanna.silva@testpro.io";  // Replace with the expected email
        String actualEmail = response.jsonPath().getString("email");
        assertEquals(actualEmail, expectedEmail, "User email should match");
    }
}