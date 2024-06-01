package api.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader() {
        String env = System.getProperty("env");
        if (env == null) {
            throw new IllegalArgumentException("Environment variable 'env' not set");
        }
        loadProperties("properties/" + env + ".properties");
        loadProperties("REST-endpoints.properties");
    }

    private void loadProperties(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new FileNotFoundException("Property file '" + fileName + "' not found in the classpath");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new NullPointerException("Property '" + key + "' not found");
        }
        return value;
    }

    public String getHttpMethod(String key) {
        String property = getProperty(key);
        return property.split(",")[0];
    }

    public String getEndpoint(String key) {
        String property = getProperty(key);
        return property.split(",")[1];
    }
}
