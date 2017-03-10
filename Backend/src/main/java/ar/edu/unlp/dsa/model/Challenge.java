package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 16/10/16.
 */
@Entity
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private Category category;

    private Long points;

    private String description;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Hint hint1;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Hint hint2;

    private String attachedFileUrl;
    
    private String validAnswer;

    private String answerDescription;

    //TODO: avoid recursion (id only)
    @OneToOne
    private Challenge nextChallenge;

    public Challenge(String title, Category category, Long points, String description, Hint hint1, Hint hint2, String attachedFileUrl, String validAnswer, String answerDescription) {
        this.title = title;
        this.category = category;
        this.points = points;
        this.description = description;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.attachedFileUrl = attachedFileUrl;
        this.validAnswer = validAnswer;
        this.answerDescription = answerDescription;
    }

    public Challenge() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hint getHint1() {
        return hint1;
    }

    public void setHint1(Hint hint1) {
        this.hint1 = hint1;
    }

    public Hint getHint2() {
        return hint2;
    }

    public void setHint2(Hint hint2) {
        this.hint2 = hint2;
    }

    public String getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(String validAnswer) {
        this.validAnswer = validAnswer;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachedFileUrl() {
        return attachedFileUrl;
    }

    public void setAttachedFileUrl(String attachedFileUrl) {
        this.attachedFileUrl = attachedFileUrl;
    }

    public Challenge getNextChallenge() {
        return nextChallenge;
    }

    public void setNextChallenge(Challenge nextChallenge) {
        this.nextChallenge = nextChallenge;
    }
}
