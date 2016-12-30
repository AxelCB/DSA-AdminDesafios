package ar.edu.unlp.dsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by axel on 23/10/16.
 */
@Entity
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String groupName;

    private String vmIp;

    @OneToMany(mappedBy = "user")
    private List<SolvedChallenge> solvedChallenges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVmIp() {
        return vmIp;
    }

    public void setVmIp(String vmIp) {
        this.vmIp = vmIp;
    }

    public List<SolvedChallenge> getSolvedChallenges() {
        return solvedChallenges;
    }

    public void setSolvedChallenges(List<SolvedChallenge> solvedChallenges) {
        this.solvedChallenges = solvedChallenges;
    }
}
