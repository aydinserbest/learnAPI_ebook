package org.example.api.response.factory;
/**
 * ResponseFactoryCreator does the object creation.
 *
 * <p>This class helps in getting the ResponseFactory implementation.

 */
public class ResponseFactoryCreator {
    private ResponseFactoryCreator() {}

    public static ResponseAbstractFactory getFactory() {
        return new ResponseFactory();
    }
}
