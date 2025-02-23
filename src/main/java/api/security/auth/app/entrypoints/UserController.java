package api.security.auth.app.entrypoints;

import org.springframework.web.bind.annotation.RestController;

import api.security.auth.app.restmodel.UserRestModel;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController(value = "/user")
@AllArgsConstructor
public class UserController implements UserResource {

    @PostMapping("/create")
    @Override
    public ResponseEntity<String> registerUser(@RequestBody UserRestModel entity) {
        
        
        return ResponseEntity.ok().body("usuario registrado com sucesso!");
    }
    
    
}
