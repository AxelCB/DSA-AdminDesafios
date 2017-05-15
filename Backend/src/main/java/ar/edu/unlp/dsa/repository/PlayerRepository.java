package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by axel on 17/10/16.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
	public Player findByPlayerId(Long player_id);
}
