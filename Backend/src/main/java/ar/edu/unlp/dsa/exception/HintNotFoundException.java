package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HintNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062831302384377726L;

	public HintNotFoundException(Long hintId) {
		super("could not find hint '" + hintId + "'.");
	}
}