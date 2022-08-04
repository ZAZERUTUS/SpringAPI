package api.testapi.loginendpoint.WorkerWithDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static api.DBConnection.TestDBConnector.closeConnection;
import static api.DBConnection.TestDBConnector.getConnection;

public class LoginWorker {

    public static String assertUserInDBByEmail(String email) {
        String sql_for_verify_user_by_email = String.format("SELECT COUNT(email) AS count FROM users WHERE email = '%s';", email);
        Connection connection = getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql_for_verify_user_by_email);
            resultSet.next();
            int count = resultSet.getInt(1);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAa " + count);
            closeConnection();
            if (count == 0) {
                return "false";
            } else {return "true"; }
        } catch (SQLException e) {
            e.printStackTrace();
            return "true";
        }
    }
}
