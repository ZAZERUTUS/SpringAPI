package api.testapi.loginendpoint;

import api.testapi.DataClases.*;
import api.testapi.allusersendpoint.service.AllUserService;
import api.testapi.loginendpoint.WorkerWithDB.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @RequestMapping(value = "/authentication_by_email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthClass verifyUserIsReg(String email) {
        AuthClass response = new AuthClass();
        String correct_user = LoginService.assertUserInDBByEmail(email);
        response.setCorrect_user(correct_user);
        UserData userData = new UserData();
        userData.setRole(Role.USER.toString());
        return response;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTResponse> login(@RequestBody JwtRequestForLogin jwtRequestForLogin) {
        final JWTResponse token = loginService.login(jwtRequestForLogin);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<JWTResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JWTResponse token = loginService.getAccessToken(request.getRefreshToken());
        return  ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> gerNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JWTResponse token = loginService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
