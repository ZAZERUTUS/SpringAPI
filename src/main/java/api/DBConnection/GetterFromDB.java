package api.DBConnection;

import api.testapi.DataClases.UserData;
import api.testapi.DataClases.UsersData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.DBConnection.TestDBConnector.getConnection;

public class GetterFromDB {
    public static String sql_ALL_USERS = "SELECT * FROM users;";
    public static String sql_ALL_EMAILS = "SELECT email FROM users;";


    public static List<HashMap<String, String>> getAllUsers() {
        List<HashMap<String, String>> lst = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_ALL_USERS);
            while (resultSet.next()) {
                HashMap<String, String> mp = new HashMap<>();
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String password = resultSet.getString(3);
                mp.put("id", id);
                mp.put("name", name);
                mp.put("password", password);
                lst.add(mp);
//                    System.out.println("User name: " + name + " password: " +password);
            }
        } catch (Exception e) {e.printStackTrace();}
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
        } catch (Exception e) {e.printStackTrace();}
        return mp;
    }
}
