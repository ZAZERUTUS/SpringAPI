package api.testapi.allusersendpoint;

import api.testapi.DataClases.UserData;
import api.testapi.DataClases.UsersData;
import api.DBConnection.GetterFromDB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static api.DBConnection.GetterFromDB.getRegisteredEmails;


@RestController
public class AllUsersController {

    @RequestMapping(value = "/all_users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersData restResponse() {
        UsersData response = new UsersData();
        response.addUsers(GetterFromDB.getAllUsers());
        return response;
    }

    @RequestMapping(value = "/registered_emails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, List<String>> getRegisteredEmail() {
        return getRegisteredEmails();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String defaultResponse() {
        return "This is default endpoint. Ints only text";
    }
}
