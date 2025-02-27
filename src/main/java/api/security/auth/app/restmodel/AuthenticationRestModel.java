package api.security.auth.app.restmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRestModel {

    private String email;
    private String password;
    
}
