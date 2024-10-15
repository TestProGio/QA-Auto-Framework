package apiKoel;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CreateNewPlaylist {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://104.237.13.60:3306/dbkoel";
    private static final String DB_USERNAME = "dbuser01";
    private static final String DB_PASSWORD = "pa$$01";

    // Base URL for the API
    private static final String BASE_URL = "https://qa.koel.app";
    private String authToken;


    @BeforeClass
    public void setup() {

        // Create an instance of KoelLogin and retrieve the authentication token
        KoelLogin login = new KoelLogin();
        authToken = login.koelLogin();

        // Ensure RestAssured is properly configured with the base URL
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createNewPlaylist() {
        // Define the playlist name you want to create
        String playlistName = "API-gradlePlaylist";

        // Prepare request body
        String requestBody = "{\n" +
                "  \"name\": \"" + playlistName + "\",\n" +
                "  \"rules\": []\n" +
                "}";

        // Send POST request to create a new playlist
        Response response = given()
                .header("Authorization", "Bearer " + authToken)  // Use the token retrieved from koelLogin
                .header("Cache-Control", "no-cache")
                .header("Content-Type", "application/json")  // JSON content type since you're sending a JSON body
                .header("Accept", "application/json")  // Expecting a JSON response
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(requestBody)  // Your JSON body
                .when()
                .post("/api/playlist")  // Your POST endpoint
                .then()
                .statusCode(200)  // Check if the response status is 200
                .extract().response();

        // Assert that the playlist was created successfully
        assertEquals(response.getStatusCode(), 200, "Playlist creation failed!");
        Reporter.log("Playlist creation successful");

        // Extract the playlist name from the response
        String createdPlaylistName = response.jsonPath().getString("name");

        // Verify the playlist name in the response matches the name you submitted
        assertEquals(createdPlaylistName, playlistName, "Playlist name mismatch!");
        Reporter.log("Playlist name verified successfully");

        // Now verify if the playlist is correctly added to the database
        boolean playlistExistsInDB = isPlaylistInDatabase(playlistName);

        // Assert that the playlist exists in the database
        assertTrue(playlistExistsInDB, "Playlist was not found in the database!");
        Reporter.log("Playlist found in the database");
    }

    @Test
    public void verifyPlaylistAccess() {
        // Endpoint for the API
        String endpoint = "/api/playlist";


        // Make an authenticated GET request
        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + authToken)  // Use the token retrieved from koelLogin
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)  // Expecting a 200 OK status
                .extract()
                .response();

        // Print the response for debugging purposes
        Reporter.log("Response: " + response.asString());

        // Pass the test if everything is fine
        Reporter.log("Playlist access verified successfully");
    }

    // Method to check if the playlist exists in the database
    public boolean isPlaylistInDatabase(String playlistName) {
        boolean exists = false;
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create a statement to execute SQL query
            Statement statement = connection.createStatement();

            // Query to check if the playlist exists in the database
            String query = "SELECT * FROM playlists WHERE name = '" + playlistName + "'";

            // Execute the query and get the result
            ResultSet resultSet = statement.executeQuery(query);

            // If the result set is not empty, the playlist exists
            if (resultSet.next()) {
                exists = true;
            }

            // Close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Database connection failed or query execution failed.");
        }
        return exists;
    }

    @AfterClass
    public void tearDown() {

    }
}