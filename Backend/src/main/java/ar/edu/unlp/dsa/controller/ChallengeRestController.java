package ar.edu.unlp.dsa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.CategoryNotFoundException;
import ar.edu.unlp.dsa.exception.HintNotFoundException;
import ar.edu.unlp.dsa.model.Category;
import ar.edu.unlp.dsa.model.Challenge;
import ar.edu.unlp.dsa.model.Hint;
import ar.edu.unlp.dsa.repository.CategoryRepository;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.HintRepository;

/**
 * Created by axel on 17/10/16.
 */
@RestController
@RequestMapping(Application.API_PREFIX + "/challenges")
public class ChallengeRestController {
	private final ChallengeRepository challengeRepository;
	private final CategoryRepository categoryRepository;
	private final HintRepository hintRepository;

	@Autowired
	ChallengeRestController(ChallengeRepository challengeRepository, CategoryRepository categoryRepository,
			HintRepository hintRepository) {
		this.challengeRepository = challengeRepository;
		this.categoryRepository = categoryRepository;
		this.hintRepository = hintRepository;
	}

	@RequestMapping(value = "/{challengeId}", method = RequestMethod.GET)
	public Challenge getChallenge(@PathVariable Long challengeId) {
		return this.getChallengeRepository().findOne(challengeId);
	}

	//TODO should delete hints as well?
	@RequestMapping(value = "/{challengeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteChallenge(@PathVariable Long challengeId) {
		this.getChallengeRepository().delete(challengeId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand().toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Challenge> listChallenges() {
		return this.getChallengeRepository().findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Challenge input) {
		// TODO: Add Category - done
		// Todo maybe should persist hints separately? - should
		// Todo file upload
		validateChategory(input.getCategory());
		validateHint(input.getHint1());
		validateHint(input.getHint2());
		Challenge result = this.getChallengeRepository().save(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	private void validateHint(Hint inputHint) {
		if (inputHint != null) {
			Hint hint = this.getHintRepository().findOne(inputHint.getId());
			if (hint == null) {
				throw new HintNotFoundException(inputHint.getId());
			}
		}
	}

	private void validateChategory(Category inputCategory) {
		if (inputCategory != null) {
			Category category = this.getCategoryRepository().findOne(inputCategory.getId());
			if (category == null) {
				throw new CategoryNotFoundException(inputCategory.getId());
			}
		}
	}

	public ChallengeRepository getChallengeRepository() {
		return challengeRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public HintRepository getHintRepository() {
		return hintRepository;
	}

}