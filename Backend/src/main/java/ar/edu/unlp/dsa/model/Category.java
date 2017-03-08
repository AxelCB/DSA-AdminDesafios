package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 16/10/16.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_id_seq")
    @SequenceGenerator(name="category_id_seq", sequenceName="category_id_seq", allocationSize=1)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }
}
