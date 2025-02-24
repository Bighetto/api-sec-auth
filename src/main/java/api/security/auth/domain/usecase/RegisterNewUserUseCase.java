package api.security.auth.domain.usecase;

import api.security.auth.app.model.UserModel;

public interface RegisterNewUserUseCase {
    
    void execute(UserModel model);
}
