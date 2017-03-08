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

    private String username;

    @ManyToOne
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
