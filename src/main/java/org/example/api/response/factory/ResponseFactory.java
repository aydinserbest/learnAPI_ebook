package org.example.api.response.factory;

import org.example.api.response.ContactListResponse;
import org.example.api.response.ContactResponse;
import org.example.api.response.impl.ContactListResponseImpl;
import org.example.api.response.impl.ContactResponseImpl;

/**
 * ResponseFactory implements ResponseAbstractFactory.
 *
 * <p>This class help in providing the different response type objects.
 *
 */

public class ResponseFactory implements ResponseAbstractFactory {
    @Override
    public ContactResponse getContactResponse() {
        return new ContactResponseImpl();
    }

    @Override
    public ContactListResponse getContactListResponse() {
        return new ContactListResponseImpl();
    }
}
