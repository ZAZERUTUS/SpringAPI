package api.testapi.DataClases;

import java.util.HashMap;
import java.util.List;

public class UsersData {
    private List<UserData> users;
    public List<UserData> getUsers() {
        return users;
    }

    public void addInUsers(UserData mp) {
        this.users.add(mp);
    }

    public void addUsers(List users) {
        this.users = users;
    }
}
