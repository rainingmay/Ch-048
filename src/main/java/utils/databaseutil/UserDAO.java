package utils.databaseutil;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
/**
 * Created by Evgen on 07.04.2017.
 */
public class UserDAO {

    public static List<String> getUserFromDatabaseByEmail(String email) {
        List<String> result = new LinkedList<>();
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
            String sql = "SELECT * FROM users  INNER JOIN userdetail ON users.userdetails_id = userdetail.id WHERE email=?";
            statement.getConnection().setAutoCommit(false);
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = statement.executeQuery(preparedStatement.toString());
            if (resultSet.next()) {
                result.add(resultSet.getString("id"));
                result.add(resultSet.getString("email"));
                result.add(resultSet.getString("firstname"));
                result.add(resultSet.getString("lastname"));
                result.add(getRoleById(resultSet.getString("id")));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static String getRoleById(String id) {
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role_users WHERE role_users.users_id=" + id );
            String roleId =  "";
            if (resultSet.next()) roleId = resultSet.getString("role_id");
            resultSet = statement.executeQuery("SELECT role.type FROM role WHERE role.id =" + roleId);
            if (resultSet.next()) return resultSet.getString("type");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static List<String> getEnableUsersEmails() {
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT users.email FROM users WHERE users.enabled = TRUE ");
            List<String> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(resultSet.getString("email"));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAllEvents(){
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
            statement.executeUpdate("TRUNCATE  events");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean getStatusByEmail(String email) {
        List<String> list = getEnableUsersEmails();
        if (list.contains(email)) return true;
        return false;
    }
}
