package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;


public class Configuration {
    private static final Properties properties = new Properties();

    public static final String BASE_MYCREDO_URL;
    public static final String DB_HOST;
    public static final String DB_USERNAME;
    public static final String DB_PASSWORD;
    public static final String MYCREDO_USER_PASSWORD;
    public static final Integer DEFAULT_WAIT_TIME;
    public static final Integer ZOOM_PERCENTAGE;
    public static final String ENVIRONMENT;

    static {
        loadProperties();

        ENVIRONMENT = getRequiredProperty("environment");
        BASE_MYCREDO_URL = String.format(getRequiredProperty("base.mycredo.url"), ENVIRONMENT);
        DB_HOST = getRequiredProperty("db.host");
        DB_USERNAME = getRequiredProperty("db.username");
        DB_PASSWORD = getRequiredProperty("db.password");
        MYCREDO_USER_PASSWORD = getRequiredProperty("mycredo.user.password");
        DEFAULT_WAIT_TIME = Integer.parseInt(getRequiredProperty("default.wait.time"));
        ZOOM_PERCENTAGE = Integer.parseInt(getRequiredProperty("resolution"));
    }

    private static void loadProperties() {
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration file", e);
        }
    }

    private static String getRequiredProperty(String key) {
        return Optional.ofNullable(System.getenv(key))
                .or(() -> Optional.ofNullable(properties.getProperty(key)))
                .filter(value -> !value.trim().isEmpty())
                .orElseThrow(() -> new IllegalStateException("Missing required value for key: " + key));
    }
}
