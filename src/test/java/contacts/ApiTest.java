package contacts;

import io.restassured.response.Response;
import org.example.api.request.RequestProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    @Test
    public void testGetRequest() {
        // Create RequestProcessor instance
        RequestProcessor processor = new RequestProcessor();

        // Initialize token and set endpoint
       // processor.initializeToken();
        String endpoint = "/api/v1/contacts/1000"; // Endpoint contains only the path

        // Send GET request
        processor.sendGetRequest(endpoint);

        // Get the response
        Response response = processor.getResponse();
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testPostRequest() {
        // Create RequestProcessor instance
        RequestProcessor processor = new RequestProcessor();

        // Initialize token and set endpoint
       // processor.initializeToken();
        String endpoint = "/api/v1/contacts"; // Endpoint contains only the path
        File payload = new File("src/test/resources/contact.json"); // Payload for POST request

        // Send POST request
        processor.sendPostRequest(endpoint, payload);

        // Get the response
        Response response = processor.getResponse();
        assertEquals(201, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

}
