package tests.AdminSideTest;

import testDAO.DatabaseConnection;
import testDAO.UserDAO;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Evgen on 10.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println(UserDAO.getUserFromDatabaseByEmail("a@a.com"));
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
