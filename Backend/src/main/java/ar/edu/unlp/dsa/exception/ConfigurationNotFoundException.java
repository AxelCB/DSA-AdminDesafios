package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConfigurationNotFoundException extends RuntimeException {
	public ConfigurationNotFoundException(Long configurationId) {
		super("could not find configuration '" + configurationId + "'.");
	}

}
