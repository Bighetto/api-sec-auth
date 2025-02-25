package api.security.auth.domain.service;

import org.springframework.stereotype.Component;

import api.security.auth.domain.dataprovider.AuthDataProvider;
import api.security.auth.domain.entity.UserEntity;
import api.security.auth.domain.usecase.SearchUserByEmailUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SearchUserByEmailService implements SearchUserByEmailUseCase{
    
    private final AuthDataProvider provider;
    
    @Override
    public UserEntity execute(String email) {

        return this.provider.findByEmail(email);
    }
    
}
