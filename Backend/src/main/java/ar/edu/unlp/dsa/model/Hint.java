package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 16/10/16.
 */
@Entity
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hint_id_seq")
    @SequenceGenerator(name="hint_id_seq", sequenceName="hint_id_seq", allocationSize=1)
    private Long id;

    private String description;

    private Long pointsPercentageCost;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPointsPercentageCost() {
        return pointsPercentageCost;
    }

    public void setPointsPercentageCost(Long pointsPercentageCost) {
        this.pointsPercentageCost = pointsPercentageCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hint() {
    }

    public Hint(String description, Long pointsPercentageCost) {
        this.description = description;
        this.pointsPercentageCost = pointsPercentageCost;
    }
}
