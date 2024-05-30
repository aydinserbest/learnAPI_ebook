package contacts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ContactManagementTest {
    private final String app ="http://localhost:8080/app";

    private String getJWTToken (){
        String admin = "src/test/resources/admin.json";
        String url = app + "/auth/authenticate";
        return
                given().
                        body(new File(admin)).
                        contentType("application/json").
                        when().
                        post(url).getHeader("Authorization");
    }
    @Test
    @DisplayName("Create Contact")
    public void createContact() {
        String addContact = "src/test/resources/contact.json";
        String url = app + "/api/v1/contacts";
        System.out.println(getJWTToken());
        given().
                body(new File(addContact)).
                header("Authorization", "Bearer " + getJWTToken()).
                contentType("application/json").
                when().
                post(url).
                then().
                statusCode(201);

    }
}
