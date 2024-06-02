package org.example.api.request.impl;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.request.HttpRequest;
import org.example.exceptions.InvalidRequestException;
import org.json.JSONException;

import static io.restassured.RestAssured.given;



public class PutRequestImpl extends HttpRequest {
    final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public Response request() {
        Response response;
        try {
            response =
                    given()
                            .body(getPayload())
                            .header("Authorization", "Bearer " + getToken())
                            .contentType("application/json")
                            .when()
                            .put(getUrl(), getQuery());
        } catch (JSONException | IllegalArgumentException e) {
            log.error("error occurred while requesting " + getUrl());
            throw new InvalidRequestException("there is some problem with the request.", e);
        }
        return response;
    }
}
