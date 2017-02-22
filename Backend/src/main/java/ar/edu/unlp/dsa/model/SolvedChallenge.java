package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 23/10/16.
 */
@Entity
public class SolvedChallenge {
    @Id
    @GeneratedValue
    private Long id;

    private Long obtainedScore;

    @ManyToOne
    private Challenge challenge;

    @ManyToOne
    private Player solver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObtainedScore() {
        return obtainedScore;
    }

    public void setObtainedScore(Long obtainedScore) {
        this.obtainedScore = obtainedScore;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Player getSolver() {
        return solver;
    }

    public void setSolver(Player solver) {
        this.solver = solver;
    }
}
