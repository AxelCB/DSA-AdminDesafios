package ar.edu.unlp.dsa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by axel on 23/10/16.
 */
@Entity
public class SolvedChallenge {
    @Id
    @GeneratedValue
    private Long id;

    private Long obtainedScore;

    private Challenge challenge;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
