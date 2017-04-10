package testDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by Evgen on 07.04.2017.
 */
public class DatabaseConnection {
    private static  Connection connection;
    private static String driver;
    private static String username;
    private static String password;
    private static String url;


    public static Connection connectToDatabase() {
        try {
            if (connection == null || connection.isClosed()) {
                getProperties();
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static void getProperties() {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(inputStream);

            driver = properties.getProperty("db.driver");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            url = properties.getProperty("db.url");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
