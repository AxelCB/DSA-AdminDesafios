package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HintNotFoundException extends RuntimeException {
	public HintNotFoundException(Long hintId) {
		super("could not find hint '" + hintId + "'.");
	}
}