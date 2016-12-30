package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by axel on 17/10/16.
 */
public interface ChallengeRepository extends JpaRepository<Challenge,Long> {
}
