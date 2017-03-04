package ar.edu.unlp.dsa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.CategoryNotFoundException;
import ar.edu.unlp.dsa.model.Category;
import ar.edu.unlp.dsa.repository.CategoryRepository;

/**
 * Created by axel on 17/10/16.
 */
@RestController
@RequestMapping(Application.API_PREFIX+"/categories")
public class CategoryRestController {
    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable Long categoryId) {
    	Category category = this.getCategoryRepository().findOne(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException(categoryId);
		}
		return category;
    }

    //TODO throws DataIntegrityViolationException
    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
    	Category category = this.getCategoryRepository().findOne(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException(categoryId);
		}
        this.getCategoryRepository().delete(categoryId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/")
                .buildAndExpand().toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Category> listCategories() {
        return this.getCategoryRepository().findAll();
    }
    
    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@RequestBody Category input, @PathVariable Long categoryId) {
		Category category = this.getCategoryRepository().findOne(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException(categoryId);
		}
		input.setId(categoryId);
		this.getCategoryRepository().save(input);
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Category input) {
        Category result = this.getCategoryRepository().save(new Category(input.getName()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
}

