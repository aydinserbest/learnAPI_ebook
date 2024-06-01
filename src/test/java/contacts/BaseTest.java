package contacts;

import api.auth.Authentication;
import api.config.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    private String jwtToken;
    protected static ConfigLoader configLoader;

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
}
