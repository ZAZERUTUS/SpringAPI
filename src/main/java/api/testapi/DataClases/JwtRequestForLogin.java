package api.testapi.DataClases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestForLogin {

    private String email;
    private String password;
}
