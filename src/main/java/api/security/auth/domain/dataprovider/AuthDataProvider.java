package api.security.auth.domain.dataprovider;

import api.security.auth.app.model.UserModel;

public interface AuthDataProvider {

    void saveNewUser(UserModel model);
    
}
