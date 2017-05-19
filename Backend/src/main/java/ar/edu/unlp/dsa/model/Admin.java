package ar.edu.unlp.dsa.model;

import javax.persistence.*;

/**
 * Created by axel on 16/10/16.
 */
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admin_id_seq")
    @SequenceGenerator(name="admin_id_seq", sequenceName="admin_id_seq", allocationSize=1)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    public Admin() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
