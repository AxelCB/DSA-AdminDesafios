package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 23/10/16.
 */
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="player_id_seq")
    @SequenceGenerator(name="player_id_seq", sequenceName="player_id_seq", allocationSize=1)
    private Long id;

	/**
	 * Original id provided by Users Module
	 * As id variable is managed by hibernate, playerId keeps the original one.
	 * It's used only for input and output (not identification or joins)
	 */
	private Long playerId;

    private String username;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
