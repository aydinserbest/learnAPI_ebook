package org.example.api.request.factory;

import org.example.api.request.HttpRequest;
import org.example.api.request.impl.GetRequestImpl;
import org.example.api.request.impl.PostRequestImpl;

public enum HttpMethodType {
    GET {
        @Override
        public HttpRequest createRequest() {
            return new GetRequestImpl();
        }
    },
    POST {
        @Override
        public HttpRequest createRequest() {
            return new PostRequestImpl();
        }
    };

    public abstract HttpRequest createRequest();
}
