package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChallengeNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2171048220321541162L;

	public ChallengeNotFoundException(Long hintId) {
		super("could not find challenge '" + hintId + "'.");
	}
}