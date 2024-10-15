package apiKoel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class UnlikeMultipleSongs {

    private String authToken;

    @BeforeClass
    public void setup() {
        // Create an instance of KoelLogin (assuming koelLogin method returns auth token)
        KoelLogin login = new KoelLogin();
        authToken = login.koelLogin();
    }

    @Test
    public void unlikeMultipleSongs() {
        // Song IDs to unlike (including some invalid IDs for error testing)
        String[] songIds = {
                "06cd19b77127f1e7f889ecad54376b30", // valid
                "08116cdc269c9f19964369e4bb1ab343", // valid
                "0aedf0c1c02188e8b73483d1bf2eacab", // valid
                "nonexistentid1", // invalid
                "nonexistentid2"  // invalid
        };

        // Convert songIds array to a JSON format for the request body
        String requestBody = "{ \"songs\": " + toJsonArray(songIds) + " }";

        try {
            // Send POST request to unlike the songs
            Response response = RestAssured.given()
                    .baseUri("https://qa.koel.app")  // Set the base URI
                    .basePath("/api/interaction/batch/unlike")  // Set the base path for the POST request
                    .header("Authorization", "Bearer " + authToken)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body(requestBody)
                    .when()
                    .post()  // Use post without arguments as the path is set by basePath
                    .then()
                    .extract()
                    .response();

            // Log the response details
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Body: " + response.getBody().asString());

            // Validate response code
            int statusCode = response.statusCode();
            if (statusCode == 204) {
                // 204 No Content: Expected behavior, no body to check
                System.out.println("Songs have been successfully unliked.");
            } else if (statusCode == 404) {
                // 404 Not Found: Check if the error message indicates non-existent songs
                String errorMessage = response.jsonPath().getString("message");
                assertEquals(errorMessage, "Requested songs don’t exist", "Expected error message for non-existent songs");
            } else {
                // Handle unexpected status codes
                fail("Unexpected status code received: " + statusCode);
            }

        } catch (Exception e) {
            // Log and fail the test if an exception occurs
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    // Helper method to convert songIds array into a JSON array format
    private String toJsonArray(String[] songIds) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < songIds.length; i++) {
            sb.append("\"").append(songIds[i]).append("\"");
            if (i < songIds.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}