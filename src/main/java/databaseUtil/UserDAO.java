package databaseUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
}
