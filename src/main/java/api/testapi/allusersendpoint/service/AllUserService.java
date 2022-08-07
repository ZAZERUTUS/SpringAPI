package api.testapi.allusersendpoint.service;

import api.testapi.DataClases.UserData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.DBConnection.TestDBConnector.closeConnection;
import static api.DBConnection.TestDBConnector.getConnection;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class AllUserService {
    public static String sql_ALL_USERS = "SELECT * FROM users;";
    public static String sql_ALL_EMAILS = "SELECT email FROM users;";
    private static String sql_WITH_ROLE_USER = "SELECT users.id, users.email, users.password, user_permissions.permissions FROM users JOIN user_permissions ON users.id = user_permissions.user_id;";
    private static String sql_FOR_ONE_USER_BY_EMAIL = "SELECT users.id, users.email, users.password, " +
            "user_permissions.permissions FROM users JOIN user_permissions " +
            "ON users.id = user_permissions.user_id WHERE users.email = '%s';";

    public static List<UserData> getAllUsers() {
        List<UserData> lst = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_WITH_ROLE_USER);
            while (resultSet.next()) {
                UserData mp = setDataOnUserDataClass(resultSet);
                lst.add(mp);
//                    System.out.println("User name: " + name + " password: " +password);
            }
            closeConnection();
        } catch (Exception e) {e.printStackTrace();
            closeConnection();}
        return lst;
    }

    public static HashMap<String, List<String>> getRegisteredEmails() {
        List<String> lst = new ArrayList<>();
        HashMap<String, List<String>> mp =new HashMap<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_ALL_EMAILS);
            while (resultSet.next()) {
                lst.add(resultSet.getString(1));
            }
            mp.put("emails", lst);
            closeConnection();
        } catch (Exception e) {e.printStackTrace();
            closeConnection();}
        return mp;
    }

    public static UserData getUserByEmail(String email) {
        UserData userData = new UserData();
        Connection connection = getConnection();
        try {
            String sql = String.format(sql_FOR_ONE_USER_BY_EMAIL, email);
            System.out.println("SQL " + sql);
            ResultSet resultSet = connection
                    .createStatement()
                    .executeQuery(sql);
            resultSet.next();
            log.println("resultset1 - " + resultSet.getString(2));
            userData = setDataOnUserDataClass(resultSet);
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;

    }

    private static UserData setDataOnUserDataClass(ResultSet resultSet) throws SQLException {
        log.println("setDataOnUserDataClass - " + resultSet.getString(1));
        log.println("setDataOnUserDataClass - " + resultSet.getString(2));
        log.println("setDataOnUserDataClass - " + resultSet.getString(3));
        log.println("setDataOnUserDataClass - " + resultSet.getString(4));
        UserData mp = new UserData();
        String id = resultSet.getString(1);
        String name = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);
//        mp.put("id", id);
//        mp.put("name", name);
//        mp.put("password", password);

        mp.setId(id);
        mp.setEmail(name);
        mp.setPassword(password);
        mp.setRole(role);


//        mp.put("role", role);
        return mp;
    }
}
