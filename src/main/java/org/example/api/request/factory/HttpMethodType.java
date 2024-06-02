package org.example.api.request.factory;

import org.example.api.request.HttpRequest;
import org.example.api.request.impl.DeleteRequestImpl;
import org.example.api.request.impl.GetRequestImpl;
import org.example.api.request.impl.PostRequestImpl;
import org.example.api.request.impl.PutRequestImpl;

public enum HttpMethodType {
    GET {
        @Override
        public HttpRequest createHttpRequest() {
            return new GetRequestImpl();
        }
    },
    POST {
        @Override
        public HttpRequest createHttpRequest() {
            return new PostRequestImpl();
        }
    },
    PUT {
        @Override
        public HttpRequest createHttpRequest() {
            return new PutRequestImpl();
        }
    },
    DELETE {
        @Override
        public HttpRequest createHttpRequest() {
            return new DeleteRequestImpl();
        }
    };

    public abstract HttpRequest createHttpRequest();
}
