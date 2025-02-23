package api.security.auth.app.restmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRestModel {
    private String document;
    private String name;
    private String email;
    private String phoneNumber;
}

