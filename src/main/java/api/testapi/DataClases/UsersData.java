package api.testapi.DataClases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
