package ar.edu.unlp.dsa.controller;

import ar.edu.unlp.dsa.model.Category;
import ar.edu.unlp.dsa.model.Challenge;
import ar.edu.unlp.dsa.repository.CategoryRepository;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by axel on 17/10/16.
 */
@RestController
@RequestMapping("/categories")
public class CategoryRestController {
    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable Long categoryId) {
        return this.getCategoryRepository().findOne(categoryId);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        this.getCategoryRepository().delete(categoryId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/")
                .buildAndExpand().toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Category> listCategories() {
        return this.getCategoryRepository().findAll();
    }

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

