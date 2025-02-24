package api.security.auth.domain.service;

import org.springframework.stereotype.Component;

import api.security.auth.domain.dataprovider.AuthDataProvider;
import api.security.auth.domain.entity.UserEntity;
import api.security.auth.domain.usecase.SearchUserByDocumentUseCase;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SearchUserByDocumentService implements SearchUserByDocumentUseCase {

    private final AuthDataProvider provider;

    @Override
    public UserEntity execute(String document) {

        return this.provider.findByDocument(document);

    }
    
}
