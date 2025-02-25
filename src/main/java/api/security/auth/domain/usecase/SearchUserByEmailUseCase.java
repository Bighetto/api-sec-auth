package api.security.auth.domain.usecase;

import api.security.auth.domain.entity.UserEntity;

public interface SearchUserByEmailUseCase {
    
    UserEntity execute(String email);
}
