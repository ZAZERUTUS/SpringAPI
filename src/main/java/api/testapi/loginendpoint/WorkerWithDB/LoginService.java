package api.testapi.loginendpoint.WorkerWithDB;

import api.testapi.DataClases.JWTResponse;
import api.testapi.DataClases.JwtRequestForLogin;
import api.testapi.DataClases.UserData;
import api.testapi.JWTAuthorization.JWTProvider;
import api.testapi.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static api.DBConnection.TestDBConnector.closeConnection;
import static api.DBConnection.TestDBConnector.getConnection;
import static api.testapi.allusersendpoint.service.AllUserService.getUserByEmail;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final Map<String, String> refreshStorage = new HashMap<>();
    private JWTProvider jwtProvider;

    public static String assertUserInDBByEmail(String email) {
        String sql_for_verify_user_by_email = String.format("SELECT COUNT(email) AS count FROM users WHERE email = '%s';", email);
        Connection connection = getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql_for_verify_user_by_email);
            resultSet.next();
            int count = resultSet.getInt(1);
            closeConnection();
            log.println("FROM assertUserInDBByEmail - count - " + count);
            if (count == 0) {
                return "false";
            } else {return "true"; }
        } catch (SQLException e) {
            e.printStackTrace();
            return "true";
        }
    }

    public JWTResponse login(@NonNull JwtRequestForLogin jwtRequestForLogin) {
        if (assertUserInDBByEmail(jwtRequestForLogin.getEmail()).equals("true")) {
            log.println("Email is valid");
            UserData correctData = getUserByEmail(jwtRequestForLogin.getEmail());
            log.println("DATA from user " + correctData.getEmail());
            if (correctData.getPassword().equals(jwtRequestForLogin.getPassword())) {
                log.println("User is valid "+ correctData);
                final String accessToken = jwtProvider.generateAccessToken(correctData);
                final String refreshToken = jwtProvider.generateRefreshToken(correctData);
                refreshStorage.put(correctData.getEmail(), refreshToken);
                return new JWTResponse(accessToken, refreshToken);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public JWTResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken) && assertUserInDBByEmail(login).equals("true")) {
                if (assertUserInDBByEmail(login).equals("true")) {
                    UserData userData = getUserByEmail(login);
                    final String accessToken = jwtProvider.generateAccessToken(userData);
                    return new JWTResponse(accessToken, null);
                }
            }
        }
        return new JWTResponse(null, null);
    }

    public JWTResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken) && assertUserInDBByEmail(login).equals("true")) {
                final UserData userData = getUserByEmail(login);
                final String accessToken = jwtProvider.generateAccessToken(userData);
                final String newRefreshToken = jwtProvider.generateRefreshToken(userData);
                refreshStorage.put(userData.getEmail(), newRefreshToken);
                return new JWTResponse(accessToken, newRefreshToken);
            }
        }
        return null;
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
