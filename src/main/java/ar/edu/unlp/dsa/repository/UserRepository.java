package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by axel on 17/10/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
