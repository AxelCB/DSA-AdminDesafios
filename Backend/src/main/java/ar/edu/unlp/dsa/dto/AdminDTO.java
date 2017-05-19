package ar.edu.unlp.dsa.dto;

/**
 * Created by acollard on 22/3/17.
 */
public class AdminDTO {
    private String username;
    private String token;

    public AdminDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
