package api.security.auth.app.restmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChangePasswordRestModel {

    private String email;
    private String password;
    
}
