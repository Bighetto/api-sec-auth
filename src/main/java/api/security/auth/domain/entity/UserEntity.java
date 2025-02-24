package api.security.auth.domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserEntity {

    private String name;
    private String email;
    private String document;
    private LocalDateTime registerDate;
    private String phoneNumber;
    private String tipo;
    
}
