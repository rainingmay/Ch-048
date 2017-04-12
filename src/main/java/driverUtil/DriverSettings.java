package driverUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverSettings {
    private static String driver;
    private static String path;
    private static String profile;



    private static void getProperties() {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("src/main/resources/drivers.properties");
            properties.load(inputStream);

            driver = properties.getProperty("driver.type");
            path = properties.getProperty("driver.path");
            profile = properties.getProperty("profile.name");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}