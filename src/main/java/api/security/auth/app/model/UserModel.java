package api.security.auth.app.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @Column(nullable = false, unique = true)
    private String document;

    private String name;
    private String email;
    private String phoneNumber;
    private String tipo;

    @Column(name = "registerDate", updatable = false)
    private LocalDateTime registerDate = LocalDateTime.now();

}

