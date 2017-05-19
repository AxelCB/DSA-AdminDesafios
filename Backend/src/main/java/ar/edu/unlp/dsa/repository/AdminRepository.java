package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by axel on 17/10/16.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {

    public Admin findByUsername(String username);

}
