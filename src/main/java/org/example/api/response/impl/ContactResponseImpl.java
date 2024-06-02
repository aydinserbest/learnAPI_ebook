package org.example.api.response.impl;

import org.example.api.response.ContactResponse;
import org.example.exceptions.InvalidResponseException;
import org.example.model.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ContactResponseImpl implements ContactResponse interface.
 *
 * <p>This class wraps the ObjectMapper Jackson parser for the Contact Model.
 *
 * @author Jagdeep Jain
 */
public class ContactResponseImpl extends ContactResponse {
  final Logger log = LogManager.getLogger(this.getClass());

  @Override
  public Contact getContact() {
    ObjectMapper objectMapper = new ObjectMapper();
    Contact contact;
    try {
      contact = objectMapper.readValue(getResponse().asString(), Contact.class);
    } catch (JsonProcessingException e) {
      log.error("error occurred while reading the response array.");
      throw new InvalidResponseException("there is some problem with the response.", e);
    }
    return contact;
  }
}
