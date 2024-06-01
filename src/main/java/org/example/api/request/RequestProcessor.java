package org.example.api.request;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RequestProcessor {
    private String jwtToken;
    private Response response;

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Response getResponse() {
        return response;
    }

    public void sendGetRequest(String url) {
        this.response = given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get(url);
    }

    public void sendPostRequest(String url, File payload) {
        this.response = given()
                .header("Authorization", "Bearer " + jwtToken)
                .contentType("application/json")
                .body(payload)
                .when()
                .post(url);
    }
}
