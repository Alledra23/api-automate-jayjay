package apiauto.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException ignored) {}
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
