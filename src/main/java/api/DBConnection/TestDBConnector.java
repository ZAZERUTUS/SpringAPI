package api.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDBConnector {
    private static final String url = "jdbc:mysql://192.168.43.52:3309/TestDB";
//    private static final String url = "jdbc:mysql://localhost:3307/Test_Base";
    private static final String user = "root";
    private static final String password_ = "Qaz123wsx@";
    private Connection connection;
    public static Connection returnConnection;

    private TestDBConnector(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection(url, user, password_);
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (returnConnection == null){
            TestDBConnector connector = new TestDBConnector();
            returnConnection = connector.connection;
        }
        return  returnConnection;
    }

//    public static void main(String[] args) {
//        try {
////            String url = "jdbc:mysql://localhost:3309/TestDB?autoReconnect=true&useSSL=false";
//            String url = "jdbc:mysql://192.168.43.52:3307/Test_Base";
//            String user = "root";
//            String password_ = "Qaz123wsx@";
//            String sql = "SELECT * FROM users;";
////            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
//            try(Connection conn = DriverManager.getConnection(url, user, password_)) {
//                Statement statement = conn.createStatement();
//                ResultSet resultSet = statement.executeQuery(sql);
////                TableLayout debugView = findViewById(R.id.tableForUsersMySQL);
//                while (resultSet.next()) {
//                    String name = resultSet.getString(2);
//                    String password = resultSet.getString(3);
//                    System.out.println("User name: " + name + " password: " +password);
//                }
//            }
//        }catch (Exception throwables) {
//            throwables.printStackTrace();
//        }
//    }


}
