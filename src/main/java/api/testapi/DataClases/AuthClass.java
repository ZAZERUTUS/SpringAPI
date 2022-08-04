package api.testapi.DataClases;

import java.util.HashMap;

public class AuthClass extends HashMap<String, String> {
    private String token;
    private String correct_user;
    private String error_text;


    public String getError_text() {
        return this.error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
        this.put("error_text", this.error_text);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
        this.put("token", this.token);
    }

    public String getCorrect_user() {
        return this.correct_user;
    }

    public void setCorrect_user(String correct_user) {
        this.correct_user = correct_user;
        this.put("correct_user", this.correct_user);
    }

    public AuthClass getAuthClass() {return this;}
}
