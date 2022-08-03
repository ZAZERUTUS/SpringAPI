package api.testapi.DataClases;

import java.util.HashMap;
import java.util.List;

public class UsersData {
    private List<HashMap<String, String>> users;
    public List<HashMap<String, String>> getUsers() {
        return users;
    }

    public void addInUsers(HashMap<String, String> mp) {
        this.users.add(mp);
    }

    public void addUsers(List users) {
        this.users = users;
    }
}
