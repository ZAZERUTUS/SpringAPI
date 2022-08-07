package api.testapi.DataClases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTResponse {
    private final String type = "Bearer";
    private String access_token;
    private String refresh_token;

}
