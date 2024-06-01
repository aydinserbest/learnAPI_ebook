package contacts;

import org.example.api.request.RequestProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactManagementTest extends BaseTest {
    private RequestProcessor requestProcessor;

    @BeforeEach
    public void setup() {
        super.setup();
        requestProcessor = new RequestProcessor();
        requestProcessor.setJwtToken(getJwtToken());
    }

    @Test
    @DisplayName("Create Contact")
    public void createContact() {
        String addContact = "src/test/resources/contact.json"; // Contact data file
        String url = getConfigLoader().getProperty("url") + getConfigLoader().getEndpoint("contact.add"); // Full URL

        requestProcessor.sendPostRequest(url, new File(addContact));
        assertEquals(201, requestProcessor.getResponse().getStatusCode());
    }
}
