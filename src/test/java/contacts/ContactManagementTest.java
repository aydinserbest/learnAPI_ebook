package contacts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ContactManagementTest {
    private final String app ="http://localhost:8080/app"; // Base URL

    private String getJWTToken (){ // Method to get JWT token
        String admin = "src/test/resources/token.json"; // Admin credentials file
        String url = app + "/auth/authenticate"; // Authentication endpoint
        return
                given().
                        body(new File(admin)). // Set request body
                        contentType("application/json").  // Set content type
                        when().
                        post(url). // Make POST request
                        getHeader("Authorization"); // Extract token from header
        /*
        then().extract().header("Authorization");
         */
    }
    @Test
    @DisplayName("Create Contact")
    public void createContact() {
        String addContact = "src/test/resources/contact.json"; // Contact data file
        String url = app + "/api/v1/contacts"; // Contacts API endpoint
        String token = getJWTToken();
        given().
                body(new File(addContact)). // Set request body
                header("Authorization", "Bearer " + token). // Set authorization header with space
                contentType("application/json"). // Set content type
                when().
                post(url). // Make POST request
                then().
                statusCode(201); // Check response status

    }
}
