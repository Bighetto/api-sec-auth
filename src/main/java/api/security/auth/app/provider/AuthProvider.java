package api.security.auth.app.provider;

import java.util.Optional;

import org.springframework.stereotype.Component;

import api.security.auth.app.converter.UserModelToEntityConverter;
import api.security.auth.app.model.UserModel;
import api.security.auth.app.repository.UserRepository;
import api.security.auth.domain.dataprovider.AuthDataProvider;
import api.security.auth.domain.entity.UserEntity;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthProvider implements AuthDataProvider {

    private final UserRepository userRepository;
    private final UserModelToEntityConverter userModelToEntityConverter;

    @Override
    public void saveNewUser(UserModel model) {
        this.userRepository.save(model);
    }

    @Override
    public UserEntity findByDocument(String document) {

        Optional<UserModel> model = this.userRepository.findById(document);

        if (model.isEmpty()) {
            throw new RuntimeException();
        }

        return this.userModelToEntityConverter.convertToEntity(model.get());
    }

    @Override
    public UserEntity findByEmail(String email) {
        Optional<UserModel> model = this.userRepository.findByEmail(email);

        if (model.isEmpty()) {
            throw new RuntimeException();
        }

        return this.userModelToEntityConverter.convertToEntity(model.get());
    }
    
}
