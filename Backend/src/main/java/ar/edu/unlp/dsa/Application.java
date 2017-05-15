package ar.edu.unlp.dsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by axel on 16/10/16.
 */
@SpringBootApplication
public class Application{
	public static final String API_PREFIX = "/api";
	public static final String BACKEND_URL = "http://localhost:8080";
	public static final String FRONTEND_URL = "http://localhost:4200";
	public static final String USER_ADMIN_URL = "http://localhost:2222";
	public static final String CONTROL_URL = "http://localhost:3333";
	public static final String SCOREBOARD_URL = "http://localhost:9999";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

