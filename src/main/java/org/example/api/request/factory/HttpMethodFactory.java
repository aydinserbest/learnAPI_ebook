package org.example.api.request.factory;

import org.example.api.request.HttpRequest;

public class HttpMethodFactory {
    public HttpRequest createRequest(HttpMethodType methodType) {
        return methodType.createRequest();
    }
}
