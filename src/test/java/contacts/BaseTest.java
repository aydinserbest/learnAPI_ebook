package contacts;

import api.auth.Authentication;
import api.config.ConfigLoader;
import io.restassured.response.Response;
import org.example.api.request.RequestProcessor;
import org.example.api.response.ResponseProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {
    final Logger log = LoggerFactory.getLogger(BaseTest.class);

    private String jwtToken;
    protected static ConfigLoader configLoader;
    private Response response;
    private RequestProcessor requestProcessor;

    @BeforeEach
    public void setup() {
        if (configLoader == null) {
            System.setProperty("env", "dev"); // Environment variable is set here
            configLoader = new ConfigLoader();
        }
        authentication();
    }

    protected void authentication() {
        this.jwtToken = new Authentication().init(configLoader).getJwtToken();
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public static ConfigLoader getConfigLoader() {
        return configLoader;
    }
    protected RequestProcessor request() {
        requestProcessor = new RequestProcessor();
        requestProcessor.setJwtToken(this.jwtToken);
        return requestProcessor;
    }

    protected ResponseProcessor response() {
        ResponseProcessor responseProcessor = new ResponseProcessor();
        this.response = requestProcessor.getResponse();
        responseProcessor.setResponse(this.response);
        return responseProcessor;
    }
}
