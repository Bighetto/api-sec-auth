package api.security.auth.domain.utils.abstractClasses;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ConvertCase<E, M> {
    
    public abstract M convertToModel(E entity);

    public abstract E convertToEntity(M model);

    public List<M> convertToDtoList(List<E> entityList) {
        return entityList.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public List<E> convertToEntityFromRestModelList(List<M> dtoList) {
        return dtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
