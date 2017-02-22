package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5416411554390867959L;

	public PlayerNotFoundException(Long playerId) {
		super("could not find player '" + playerId + "'.");
	}
}
