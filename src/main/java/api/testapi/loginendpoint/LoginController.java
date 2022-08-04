package api.testapi.loginendpoint;

import api.testapi.DataClases.AuthClass;
import api.testapi.DataClases.UserData;
import api.testapi.loginendpoint.WorkerWithDB.LoginWorker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/authentication_by_email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthClass verifyUserIsReg(String email) {
        AuthClass response = new AuthClass();
        String correct_user = LoginWorker.assertUserInDBByEmail(email);
        response.setCorrect_user(correct_user);
        return response;
    }
}
