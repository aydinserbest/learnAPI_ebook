package api.auth;

import api.config.ConfigLoader;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Authentication {
    private String jwtToken;

    public String getJwtToken() {
        return this.jwtToken;
    }

    public Authentication init(ConfigLoader configLoader) {
        String url = configLoader.getProperty("url") + configLoader.getEndpoint("login");
        String requestPayload = "src/test/resources/authentication/adminPayload.json";

        // Dosyanın var olup olmadığını kontrol edelim
        File payloadFile = new File(requestPayload);
        if (!payloadFile.exists()) {
            throw new RuntimeException("Payload file not found: " + requestPayload);
        }

        try {
            Response response =
                    given()
                            .body(payloadFile)
                            .contentType("application/json")
                            .when()
                            .post(url);
            this.jwtToken = response.getHeader("Authorization");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Authentication failed", e);
        }
        return this;
    }
}
