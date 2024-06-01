package contacts;

import api.config.ConfigLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class TestWithConfigFile {

    private static ConfigLoader configLoader;
    private static String token;

    @BeforeAll
    public static void setup() {
        System.setProperty("env", "dev"); // Environment variable is set here
        configLoader = new ConfigLoader();
        token = getJWTToken(); // Assuming you have a method to get JWT token
    }

    @Test
    @DisplayName("Create Contact")
    public void createContact() {
        String addContact = "src/test/resources/contact.json"; // Contact data file
        String url = configLoader.getProperty("url") + configLoader.getEndpoint("contact.add"); // Full URL with endpoint

        given()
                .body(new File(addContact)) // Set request body
                .header("Authorization", "Bearer " + token) // Set authorization header with space
                .contentType("application/json") // Set content type
                .when()
                .post(url) // Make POST request
                .then()
                .statusCode(201); // Check response status
    }

    private static String getJWTToken() {
        // Method to get JWT token
        String url = configLoader.getProperty("url") + configLoader.getEndpoint("login");
        String adminCredentials = "src/test/resources/adminPayload.json"; // Admin credentials file

        return given()
                .body(new File(adminCredentials)) // Set request body
                .contentType("application/json") // Set content type
                .when()
                .post(url) // Make POST request
                .then()
                .extract()
                .header("Authorization"); // Extract token from header
    }
}
