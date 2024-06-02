package org.example.api.request;

import io.restassured.response.Response;
import org.example.api.request.factory.HttpMethodFactory;
import org.example.api.request.factory.HttpMethodType;


import java.io.File;
import java.util.Map;

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

    public void get(String endpoint) {
        HttpRequest httpGet = new HttpMethodFactory().build(HttpMethodType.GET);
        this.response = httpGet.setToken(jwtToken).setUrl(endpoint).request();
    }
    public void post(String endpoint, File requestPayload) {
        HttpRequest httpPut = new HttpMethodFactory().build(HttpMethodType.POST);
        this.response =
                httpPut.setToken(jwtToken).setUrl(endpoint).setPayload(requestPayload).request();
    }

    public void put(String endpoint, File requestPayload, Map<String, ? extends Object> query) {
        HttpRequest httpPut = new HttpMethodFactory().build(HttpMethodType.PUT);
        this.response =
                httpPut
                        .setToken(jwtToken)
                        .setUrl(endpoint)
                        .setPayload(requestPayload)
                        .setQuery(query)
                        .request();
    }

    public void delete(String endpoint, Map<String, ? extends Object> query) {
        HttpRequest httpDelete = new HttpMethodFactory().build(HttpMethodType.DELETE);
        this.response = httpDelete.setToken(jwtToken).setUrl(endpoint).setQuery(query).request();
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
