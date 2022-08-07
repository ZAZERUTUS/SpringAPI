package api.testapi.registrationendpoint;

import api.testapi.DataClases.AuthClass;
import api.testapi.DataClases.Role;
import api.testapi.DataClases.UserData;
import api.testapi.registrationendpoint.WorkerWithDB.RegistrationWorker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthClass registration(String email, String password) {
        UserData userData = new UserData();
        userData.setEmail(email);
        userData.setPassword(password);
        userData.setRole(Role.USER.toString());
        AuthClass authClass = RegistrationWorker.AddNewUserInDB(userData);
        return authClass;
    }

}
