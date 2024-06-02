package org.example.api.request.impl;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.request.HttpRequest;
import org.example.exceptions.InvalidRequestException;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class GetRequestImpl extends HttpRequest {
    final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public Response request() {
        Response response;
        try {
            response =
                    given()
                            .header("Authorization", "Bearer " + getToken())
                            .contentType("application/json")
                            .when()
                            .get(getUrl());
        } catch (JSONException | IllegalArgumentException e) {
            log.error("error occurred while requesting " + getUrl());
            throw new InvalidRequestException("there is some problem with the request.", e);
        }
        return response;
    }
}
/*
sablonunu bu test uzerinden inceliyorum:public class ApiTest {
    @Test
    public void testGetRequest() {
        RequestProcessor processor = new RequestProcessor();
        processor.initializeToken();
        String endpoint = "https://jsonplaceholder.typicode.com/posts/1";
        processor.sendGetRequest(endpoint);
        Response response = processor.getResponse();
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
} testte bu satira bakalim:processor.sendGetRequest(endpoint);  burda RequestProcessor sinifindaki sendGetRequest cagriliyor,o siniftaki metot da su:public void sendGetRequest(String endpoint) {
        HttpRequest request = new HttpMethodFactory().createRequest(HttpMethodType.GET);
        request.setToken(jwtToken);
        request.setUrl(endpoint);
        this.response = request.sendRequest();
    } bu metot satir satir ne yapiyor: 1-factory design yardimi ile HttpRequest nesnesi olusturuluyor, bu ornek icin onu extend eden GetRequestImpl nesnesi olusuyor yani 2-token ataniyor 3-url ataniyor 4-getrequestiml sinif nesnesinin sendRequest metodu cagriliyor: @Override
    public Response sendRequest() {
        return RestAssured.given()
                .header("Authorization", "Bearer " + getToken())
                .queryParams(getQueryParams())
                .when()
                .get(getUrl())
                .then()
                .extract()
                .response();
    } bu yani. bu adimlarda kafama oturmayan bir sey var, 1-ana amacimiz GetRequestImpl sinifindaki sendRequest metodunu cagirmak ve statuscode'a bakmak bu kadar yani.
 */
