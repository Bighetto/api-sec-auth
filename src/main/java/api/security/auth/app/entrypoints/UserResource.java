package api.security.auth.app.entrypoints;

import org.springframework.http.ResponseEntity;

import api.security.auth.app.restmodel.UserRestModel;

public interface UserResource {

    ResponseEntity<String> registerUser(UserRestModel restModel);
    
}
