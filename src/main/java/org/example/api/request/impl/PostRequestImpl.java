package org.example.api.request.impl;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.request.HttpRequest;

public class PostRequestImpl extends HttpRequest {
    @Override
    public Response sendRequest() {
        return RestAssured.given()
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .body(getPayload())
                .when()
                .post(getUrl())
                .then()
                .extract()
                .response();
    }
}
