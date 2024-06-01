package org.example.api.request;

import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

/**
 * HttpGet is an abstract class.
 *
 * <p>This class serves the basic building blocks of the http request method. It has url,
 * authentication token, query parameter and the payload which is being used by the http GET, POST,
 * PUT, DELETE methods.
 */

public abstract class HttpRequest {

    private String url;
    private String token;
    private Map<String, Object> queryParams;
    private File payload;

    // Getters ve Setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Map<String, Object> getQueryParams() { return queryParams; }
    public void setQueryParams(Map<String, Object> queryParams) { this.queryParams = queryParams; }
    public File getPayload() { return payload; }
    public void setPayload(File payload) { this.payload = payload; }

    // Soyut metod
    public abstract Response sendRequest();
}
