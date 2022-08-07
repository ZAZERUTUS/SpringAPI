package api.testapi.DataClases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserData {


    private String id;
    private String email = null;
    private String password = null;
    private String role;

}


//public class UserData extends HashMap<String, String> {
//
//
//    private int id;
//    private String email = null;
//    private String password = null;
//    private String role;
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//        this.put("id", Integer.toString(this.id));
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//        this.put("email", this.email);
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String passwors) {
//        this.password = passwors;
//        this.put("password", this.password);
//    }
//
//    public UserData getUser() {
//        return this;
//    }
//}
