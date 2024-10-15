/*package apiKoel.requests;

public class LoginRequest {
    private String email;
    private String password;

    // Constructor
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}

 */
/*
package apiKoel.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginRequest {
    private static final String BASE_URL = "https://qa.koel.app";

    public Response performLogin(String email, String password) {
        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }"; // Use actual email and password

        // Perform the POST request to the login endpoint
        Response response = given()
                .baseUri(BASE_URL) // Set the base URI
                .contentType("application/json") // Set content type to JSON
                .body(requestBody) // Set the request body
                .when()
                .post("/api/me/login"); // Ensure this is the correct login endpoint

        return response; // Return the response
    }
}

 */
package apiKoel.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import static io.restassured.RestAssured.given;

public class LoginRequest {
    // Fields for email and password
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    // Constructor
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to perform login
    public Response performLogin() {
        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        // Make the POST request and extract the response
        return given()
                .contentType(ContentType.JSON) // Specify the content type
                .accept(ContentType.JSON) // Specify the accepted response type
                .body(requestBody) // Add the request body
                .when()
                .post("/api/me") // Use the correct login endpoint
                .then()
                .extract()
                .response(); // Extract the response
    }
}



