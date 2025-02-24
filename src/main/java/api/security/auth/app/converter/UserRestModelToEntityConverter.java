package api.security.auth.app.converter;

import org.springframework.stereotype.Component;

import api.security.auth.app.restmodel.UserRestModel;
import api.security.auth.domain.entity.UserEntity;
import api.security.auth.domain.utils.abstractClasses.ConvertCase;

@Component
public class UserRestModelToEntityConverter extends ConvertCase<UserEntity, UserRestModel> {

    @Override
    public UserRestModel convertToModel(UserEntity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToModel'");
    }

    @Override
    public UserEntity convertToEntity(UserRestModel model) {
        return UserEntity
        .builder()
        .document(model.getDocument())
        .name(model.getName())
        .email(model.getEmail())
        .phoneNumber(model.getPhoneNumber())
        .build();
    }
    
}
