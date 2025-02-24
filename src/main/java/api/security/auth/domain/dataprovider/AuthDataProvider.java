package api.security.auth.domain.dataprovider;

import api.security.auth.app.model.UserModel;
import api.security.auth.domain.entity.UserEntity;

public interface AuthDataProvider {

    void saveNewUser(UserModel model);

    UserEntity findByDocument(String document);
    
}
