package testDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgen on 07.04.2017.
 */
public class UserDAO {

    public static List<String> getUserFromDatabaseByEmail(String email) {
        List<String> result = new LinkedList<>();
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT email FROM users WHERE email = \'" + email +
                    "\' INNER JOIN userdetail ON users.userdetails_id = userdetail.id ");
            result.add(resultSet.getString("email"));
            result.add(resultSet.getString("firstname"));
            result.add(resultSet.getString("lastname"));
            result.add(getRoleById(resultSet.getString("id")));
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getRoleById(String id) {
        try {
            Statement statement = DatabaseConnection.connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT role_id FROM role_users WHERE user_id = " + id );
            String roleId =  "";
            if (resultSet.next()) roleId = resultSet.getString("role_id");
            resultSet = statement.executeQuery("SELECT type FROM role WHERE id =" + roleId);
            if (resultSet.next()) return resultSet.getString("role");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
