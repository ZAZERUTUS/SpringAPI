package api.testapi.registrationendpoint.WorkerWithDB;

import api.testapi.DataClases.AuthClass;
import api.testapi.DataClases.UserData;
import api.testapi.DataClases.UsersData;
import api.testapi.loginendpoint.WorkerWithDB.LoginWorker;
import api.testapi.validators.UserDataValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static api.DBConnection.TestDBConnector.closeConnection;
import static api.DBConnection.TestDBConnector.getConnection;

public class RegistrationWorker {

    public static AuthClass AddNewUserInDB(UserData userData){
        AuthClass authClass = new AuthClass();
        String sql_ADD_NEW_USER = String.format("INSERT INTO users (email, password) VALUES ('%s', '%s');",
                userData.getEmail(), userData.getPassword());

        if (!VerifyingUserData(userData)) {
            authClass.setError_text("Incorrect input data");
            return authClass;
        }

        AuthClass authClass1 = ValidationPasswordAndEmail(userData);
        if (authClass1 != null) {return authClass1;}

        try {
            if (LoginWorker.assertUserInDBByEmail(userData.getEmail()).equals("true")) {
                authClass.setError_text("User already registered");
                return authClass;
            }
            Connection connection = getConnection();
            int rows = connection.createStatement().executeUpdate(sql_ADD_NEW_USER);
            closeConnection();
            System.out.println("Added rows - " + rows);
            if (rows != 1) {
                authClass.setError_text("User not added in DB");
            } else {
                authClass.setError_text("User success registered");}

        } catch (Exception e) {
            e.printStackTrace();
            authClass.setError_text("error");
        }
        return authClass;
    }

    public static boolean VerifyingUserData(UserData userData) {
        if (userData.getEmail() == null | userData.getPassword() == null) {
            return false;
        }
        return true;

    }

    public static AuthClass ValidationPasswordAndEmail(UserData userData) {
        StringBuilder stringBuilder = new StringBuilder();
        AuthClass authClass = new AuthClass();
        if (!UserDataValidator.isValidEmail(userData.getEmail())) {
            stringBuilder.append("Invalid email.");}
        if (stringBuilder.length() == 0) {return null;}
        authClass.setError_text(stringBuilder.toString());
        return authClass;
    }
}
