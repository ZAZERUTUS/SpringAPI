package api.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDBConnector {
    private static final String url = "jdbc:mysql://127.0.0.1:3309/TestDB";
//    private static final String url = "jdbc:mysql://localhost:3307/Test_Base";
    private static final String user = "root";
    private static final String password_ = "Qaz123wsx@";
    private Connection connection;
    public static Connection returnConnection = null;

    private TestDBConnector(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            this.connection = DriverManager.getConnection(url, user, password_);
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (returnConnection == null) {
                TestDBConnector connector = new TestDBConnector();
                returnConnection = connector.connection;
            } else if (returnConnection.isClosed()) {
                TestDBConnector connector = new TestDBConnector();
                returnConnection = connector.connection;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return  returnConnection;
    }

    public static void closeConnection() {
        try {
            returnConnection.close();
            returnConnection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
