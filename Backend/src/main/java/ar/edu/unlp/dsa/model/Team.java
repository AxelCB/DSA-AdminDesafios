package ar.edu.unlp.dsa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany
	private List<Hint> usedHints;

	@ManyToMany
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
