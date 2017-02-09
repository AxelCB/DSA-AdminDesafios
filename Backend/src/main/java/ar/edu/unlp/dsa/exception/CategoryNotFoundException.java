package ar.edu.unlp.dsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(Long categoryId) {
		// TODO: output sanitization?
		super("could not find category '" + categoryId + "'.");
	}
}