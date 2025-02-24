package api.security.auth.app.entrypoints;

import org.springframework.web.bind.annotation.RestController;

import api.security.auth.app.converter.UserRestModelToEntityConverter;
import api.security.auth.app.model.UserModel;
import api.security.auth.app.restmodel.UserRestModel;
import api.security.auth.domain.entity.UserEntity;
import api.security.auth.domain.usecase.RegisterNewUserUseCase;
import api.security.auth.domain.usecase.SearchUserByDocumentUseCase;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController implements UserResource {

    private final SearchUserByDocumentUseCase searchUserByDocumentUseCase;
    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final UserRestModelToEntityConverter userRestModelToEntityConverter;

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> registerUser(@RequestBody UserRestModel restModel) {

        try{
            UserEntity entity = this.userRestModelToEntityConverter.convertToEntity(restModel);

            String encryptedPassword = new BCryptPasswordEncoder().encode(entity.getDocument());
            
            UserModel model = new UserModel(entity.getDocument(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), "cliente", encryptedPassword, LocalDateTime.now());

            this.registerNewUserUseCase.execute(model);
            
            return ResponseEntity.ok().body("The new user has been registered!");
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    
}
