package api.testapi.loginendpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    public static class RestResponse{
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse restResponse(String email, String password) {
        RestResponse response = new RestResponse();
        response.setEmail(email.toUpperCase());
        response.setPassword(password);
        return response;
    }
}
