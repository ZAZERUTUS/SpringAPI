package api.testapi.allusersendpoint;

import api.testapi.DataClases.UsersData;
import api.testapi.allusersendpoint.service.AllUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static api.testapi.allusersendpoint.service.AllUserService.getRegisteredEmails;


@RestController
public class AllUsersController {

    @RequestMapping(value = "/all_users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersData restResponse() {
        UsersData response = new UsersData();
        response.addUsers(AllUserService.getAllUsers());
        return response;
    }

    @RequestMapping(value = "/registered_emails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, List<String>> getRegisteredEmail() {
        return getRegisteredEmails();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultResponse() {
        return "resources/static/instructionForAPI.html";
    }
}
