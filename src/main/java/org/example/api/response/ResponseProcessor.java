package org.example.api.response;

import org.example.api.response.factory.ResponseAbstractFactory;
import org.example.api.response.factory.ResponseFactoryCreator;
import org.example.model.Contact;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * ResponseProcessor class is used for processing the http response.
 *
 * <p>This class is used for processing the response types with ResponseFactoryCreator.
 *
 * @author Jagdeep Jain
 */
public class ResponseProcessor {
  final Logger log = LogManager.getLogger(this.getClass());
  ResponseAbstractFactory responseFactory = ResponseFactoryCreator.getFactory();
  private Response response;

  public void setResponse(Response response) {
    this.response = response;
  }

  public Response getResponse() {
    return response;
  }

  public Contact getResponseContact() {
    return responseFactory.getContactResponse().setResponse(this.response).getContact();
  }

  public List<Contact> getResponseContacts() {
    return responseFactory.getContactListResponse().setResponse(this.response).getContacts();
  }
}
