package org.example.api.request.factory;

import org.example.api.request.HttpRequest;

public class HttpMethodFactory {
    public HttpRequest build(HttpMethodType type) {
        return type.createHttpRequest();
    }
}
