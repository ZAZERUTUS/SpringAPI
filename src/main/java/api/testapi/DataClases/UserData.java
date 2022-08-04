package api.testapi.DataClases;

import java.util.HashMap;

public class UserData extends HashMap<String, String> {


    private int id;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.put("id", Integer.toString(this.id));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.put("email", this.email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwors) {
        this.password = passwors;
        this.put("password", this.password);
    }

    public UserData getUser() {
        return this;
    }
}
