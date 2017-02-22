package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConfigurationNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1941752965705348819L;

	public ConfigurationNotFoundException(Long configurationId) {
		super("could not find configuration '" + configurationId + "'.");
	}

}
