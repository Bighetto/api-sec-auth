package api.security.auth.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.security.auth.app.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{

    Optional<UserModel> findByEmail(String email);
    
}
