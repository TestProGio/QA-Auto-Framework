package apiKoel;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class LikeSongTest {

    // Base URL of the API
    private static final String BASE_URL = "https://qa.koel.app";

    // Bearer token (replace with a valid token)
    private String authToken;

    @BeforeClass
    public void setup() {
        // Create an instance of KoelLogin (assuming koelLogin method returns auth token)
        KoelLogin login = new KoelLogin();
        authToken = login.koelLogin();
        Assert.assertNotNull(authToken, "Auth token should not be null");

        // Set RestAssured base URI globally
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void likeOrUnlikeSong() {
        SoftAssert softAssert = new SoftAssert();
        String songId = "3dfc5899aed1fb7edba2ce89d8d8ec45";
        String requestBody = "{ \"song\": \"" + songId + "\" }";

        // Perform the like action
        Response response = given()
                .basePath("/api/interaction/like")
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post();

        // Log the response for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check for success status code
        softAssert.assertEquals(response.getStatusCode(), 200, "Error: Received status code");

        // Extract the liked status
        Boolean isLiked = response.jsonPath().getBoolean("liked");
        softAssert.assertNotNull(isLiked, "Expected 'liked' field to be present in response but found null");

        // Assert the expected value
        softAssert.assertTrue(isLiked, "The song should be marked as liked");

        // Perform the unlike action
        response = given()
                .basePath("/api/interaction/like")
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post();

        // Log the response for debugging
        System.out.println("Unlike Response Status Code: " + response.getStatusCode());
        System.out.println("Unlike Response Body: " + response.getBody().asString());

        // Check for success status code again
        softAssert.assertEquals(response.getStatusCode(), 200, "Error: Received status code on unlike");

        // Extract the liked status again
        isLiked = response.jsonPath().getBoolean("liked");
        softAssert.assertNotNull(isLiked, "Expected 'liked' field to be present in response but found null after unliking");

        // Assert the expected value after unliking
        softAssert.assertFalse(isLiked, "The song should not be marked as liked after unliking");

        // Report all soft assertions
        softAssert.assertAll();
    }
}
