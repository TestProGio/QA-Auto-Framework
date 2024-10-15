/*package apiKoel.responses;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("errorMessage")
    private String errorMessage;
    // No-argument constructor (required for Jackson)
    public LoginResponse() {
    }

    // Constructor with arguments
    public LoginResponse(String token, String errorMessage) {
        this.token = token;
        this.errorMessage = errorMessage;
    }

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for errorMessage
    public String getErrorMessage() {
        return errorMessage;
    }

    // Setter for errorMessage
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

 */

package apiKoel.responses;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("errorMessage")
    private String errorMessage;

    // No-argument constructor (required for Jackson)
    public LoginResponse() {
    }

    // Constructor for successful login
    public LoginResponse(String token) {
        this.token = token; // Set the token for successful login
        this.errorMessage = null; // No error message for successful login
    }

    // Constructor for failed login with a specific error message
    public LoginResponse(String errorMessage, boolean isError) {
        if (isError) {
            this.token = null; // No token for failed login
            this.errorMessage = errorMessage; // Set the error message
        } else {
            throw new IllegalArgumentException("Use the appropriate constructor for successful login.");
        }
    }

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for errorMessage
    public String getErrorMessage() {
        return errorMessage;
    }

    // Setter for errorMessage
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
