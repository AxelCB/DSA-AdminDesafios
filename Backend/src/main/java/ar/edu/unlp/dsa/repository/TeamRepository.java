package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
