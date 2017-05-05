package utils.databaseutil;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.*;
import java.util.Properties;


/**
 * Created by Evgen on 07.04.2017.
 */
public class DatabaseOperations {
    private static Connection connection;
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


    public static void restore(String filename) {
        try {

            Statement statement = connectToDatabase().createStatement();
            statement.execute("DROP SCHEMA  public CASCADE");
            statement.execute("CREATE SCHEMA public");
            connection.close();


            Properties properties = new Properties();
            String filepath = "src\\main\\resources\\" + filename;
            InputStream inputStream = new FileInputStream("src/main/resources/detailedDatabaseProperties.properties");
            properties.load(inputStream);

            Runtime runtime = Runtime.getRuntime();
            /*ProcessBuilder processBuilder = new ProcessBuilder(
                    "pg_restore",
                    "--host=" + properties.getProperty("db.host"),
                    "--port=" + properties.getProperty("db.port"),
                    "--username=postgres",
                    "--dbname=" + properties.getProperty("db.name"),
                    "--role=postgres",
                    "--no-password",
                    "--verbose",
                    filepath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();*/

            String[] cmd = {
                    "pg_restore",
                    "--host=" + properties.getProperty("db.host"),
                    "--port=" + properties.getProperty("db.port"),
                    "--username=postgres",
                    "--dbname=" + properties.getProperty("db.name"),
                    "--role=postgres",
                    "--no-password",
                    "--verbose",
                    filepath
            };
            Process process = runtime.exec(cmd);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public static void backup(String filename) {
        try {
            Properties properties = new Properties();
            String filepath = "src\\main\\resources\\" + filename;
            InputStream inputStream = new FileInputStream("src\\main\\resources\\detailedDatabaseProperties.properties");
            properties.load(inputStream);

            Runtime runtime = Runtime.getRuntime();;
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "pg_dump",
                    "--host", properties.getProperty("db.host"),
                    "--port", properties.getProperty("db.port"),
                    "--username", properties.getProperty("db.username"),
                    "--no-password",
                    "--format", "custom",
                    "--blobs",
                    "--verbose", "--file", filepath, properties.getProperty("db.name"));
            Process process = processBuilder.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
