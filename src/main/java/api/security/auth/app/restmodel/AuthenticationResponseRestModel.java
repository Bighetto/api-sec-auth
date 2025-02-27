package api.security.auth.app.restmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponseRestModel {

    private String email;
    private String nome;
    private String token;
    private String role;
    
}
