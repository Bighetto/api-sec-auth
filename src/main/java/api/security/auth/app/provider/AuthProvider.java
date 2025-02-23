package api.security.auth.app.provider;

import org.springframework.stereotype.Component;

import api.security.auth.app.model.UserModel;
import api.security.auth.app.repository.UserRepository;
import api.security.auth.domain.dataprovider.AuthDataProvider;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthProvider implements AuthDataProvider {

    private final UserRepository userRepository;

    @Override
    public void saveNewUser(UserModel model) {
        this.userRepository.save(model);
    }
    
}
