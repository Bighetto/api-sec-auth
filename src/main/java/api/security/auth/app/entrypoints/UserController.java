package api.security.auth.app.entrypoints;

import org.springframework.web.bind.annotation.RestController;

import api.security.auth.app.converter.UserRestModelToEntityConverter;
import api.security.auth.app.model.UserModel;
import api.security.auth.app.restmodel.AuthenticationResponseRestModel;
import api.security.auth.app.restmodel.AuthenticationRestModel;
import api.security.auth.app.restmodel.ChangePasswordRestModel;
import api.security.auth.app.restmodel.UserRestModel;
import api.security.auth.app.security.SecurityConfig;
import api.security.auth.app.security.TokenService;
import api.security.auth.domain.entity.UserEntity;
import api.security.auth.domain.usecase.RegisterNewUserUseCase;
import api.security.auth.domain.usecase.SearchUserByEmailUseCase;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
@CrossOrigin
public class UserController implements UserResource {

    private final SearchUserByEmailUseCase searchUserByEmailUseCase;
    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final UserRestModelToEntityConverter userRestModelToEntityConverter;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SecurityConfig securityConfig;

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> registerUser(@RequestBody UserRestModel restModel) {

        try{
            UserEntity entity = this.userRestModelToEntityConverter.convertToEntity(restModel);

            String encryptedPassword = this.securityConfig.passwordEncoder().encode(entity.getDocument());
            
            UserModel model = new UserModel(entity.getDocument(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), "cliente", encryptedPassword, LocalDateTime.now());

            this.registerNewUserUseCase.execute(model);
            
            return ResponseEntity.ok().body("The new user has been registered!");
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        
    }

    @Override
    @PostMapping("/update/password")
    public ResponseEntity<String> changeUserPassword(@RequestBody ChangePasswordRestModel restmodel) {
        
        try{
            UserEntity entity = this.searchUserByEmailUseCase.execute(restmodel.getEmail());

            String encryptedPassword = this.securityConfig.passwordEncoder().encode(restmodel.getPassword());

            UserModel model = new UserModel(entity.getDocument(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), restmodel.getTipo(), encryptedPassword, LocalDateTime.now());

            this.registerNewUserUseCase.execute(model);

            return ResponseEntity.ok().body("Passowrd updated successfully");

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseRestModel> createAuthenticationToken(@RequestBody AuthenticationRestModel restModel) {
        
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(restModel.getEmail(), restModel.getPassword());

            var auth = this.authenticationManager.authenticate(usernamePassword);

            var userDetails = (UserModel)auth.getPrincipal();
            var nome = userDetails.getName();
            var email = userDetails.getEmail();
            var role = userDetails.getTipo();
    
            final String jwt = tokenService.generateToken(userDetails);

            AuthenticationResponseRestModel responseRestModel = new AuthenticationResponseRestModel(nome, email, jwt, role);
    
            return ResponseEntity.ok(responseRestModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
}
