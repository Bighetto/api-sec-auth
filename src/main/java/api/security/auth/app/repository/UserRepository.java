package api.security.auth.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.security.auth.app.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{

    UserModel findByEmail(String email);
    
}
