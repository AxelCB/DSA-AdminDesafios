package ar.edu.unlp.dsa.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="team_id_seq")
	@SequenceGenerator(name="team_id_seq", sequenceName="team_id_seq", allocationSize=1)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Hint> usedHints;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<SolvedChallenge> solvedChallenges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Hint> getUsedHints() {
		return usedHints;
	}

	public void setUsedHints(List<Hint> usedHints) {
		this.usedHints = usedHints;
	}

	public List<SolvedChallenge> getSolvedChallenges() {
		return solvedChallenges;
	}

	public void setSolvedChallenges(List<SolvedChallenge> solvedChallenges) {
		this.solvedChallenges = solvedChallenges;
	}
}
