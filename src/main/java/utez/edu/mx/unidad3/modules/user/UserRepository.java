package utez.edu.mx.unidad3.modules.user;

import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Resource
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
