package org.example.api.response.factory;

import org.example.api.response.ContactListResponse;
import org.example.api.response.ContactResponse;

/**
 * ResponseAbstractFactory implements the Abstract Factory Pattern.
 *
 * <p>This interface help in providing the different response types.
 */

public interface ResponseAbstractFactory {
    ContactResponse getContactResponse();

    ContactListResponse getContactListResponse();
}
