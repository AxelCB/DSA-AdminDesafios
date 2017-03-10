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
import ar.edu.unlp.dsa.exception.ChallengeNotFoundException;
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

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{challengeId}", method = RequestMethod.GET)
	public Challenge getChallenge(@PathVariable Long challengeId) {
		Challenge challenge = this.getChallengeRepository().findOne(challengeId);
		if (challenge == null) {
			throw new ChallengeNotFoundException(challengeId);
		}
		return challenge;
	}

	//TODO should delete hints as well?
	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{challengeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteChallenge(@PathVariable Long challengeId) {
		Challenge challenge = this.getChallengeRepository().findOne(challengeId);
		if (challenge == null) {
			throw new ChallengeNotFoundException(challengeId);
		}
		this.getChallengeRepository().delete(challengeId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand().toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Challenge> listChallenges() {
		return this.getChallengeRepository().findAll();
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{challengeId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateChallenge(@RequestBody Challenge input, @PathVariable Long challengeId) {
		Challenge challenge = this.getChallengeRepository().findOne(challengeId);
		if (challenge == null) {
			throw new ChallengeNotFoundException(challengeId);
		}
		input.setId(challengeId);
		Long deletedHint1Id = null;
        if (challenge.getHint1() == null) {
            if (input.getHint1() != null) {
                Hint savedHint = this.getHintRepository().save(input.getHint1());
                input.setHint1(savedHint);
            }
        } else {
            if (input.getHint1() != null) {
                input.getHint1().setId(challenge.getHint1().getId());
                this.getHintRepository().save(input.getHint1());
            } else {
				deletedHint1Id = challenge.getHint1().getId();
				challenge.setHint1(null);
            }
        }
		Long deletedHint2Id = null;
		if (challenge.getHint2() == null) {
			if (input.getHint2() != null) {
				Hint savedHint = this.getHintRepository().save(input.getHint2());
				input.setHint2(savedHint);
			}
		} else {
			if (input.getHint2() != null) {
				input.getHint2().setId(challenge.getHint2().getId());
				this.getHintRepository().save(input.getHint2());
			} else {
				deletedHint2Id = challenge.getHint2().getId();
				challenge.setHint2(null);
			}
		}
		this.getChallengeRepository().save(input);
		//TODO catch "constraint violation"
        if(deletedHint1Id != null ){
			this.getHintRepository().delete(deletedHint1Id);
		}
		if(deletedHint2Id != null ){
			this.getHintRepository().delete(deletedHint2Id);
		}
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Challenge input) {
		// Todo file upload
		validateCategory(input.getCategory());
		validateChallenge(input.getNextChallenge());
		if(input.getHint1() != null ){
			Hint savedHint = this.getHintRepository().save(input.getHint1());
			input.setHint1(savedHint);
		}
		if(input.getHint2() != null ){
			Hint savedHint = this.getHintRepository().save(input.getHint2());
			input.setHint2(savedHint);
		}
		Challenge result = this.getChallengeRepository().save(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	private void validateChallenge(Challenge inputChallenge) {
		if (inputChallenge != null) {
			Challenge challenge = this.getChallengeRepository().findOne(inputChallenge.getId());
			if (challenge == null) {
				throw new ChallengeNotFoundException(inputChallenge.getId());
			}
		}
	}
	
	private void validateHint(Hint inputHint) {
		if (inputHint != null) {
			Hint hint = this.getHintRepository().findOne(inputHint.getId());
			if (hint == null) {
				throw new HintNotFoundException(inputHint.getId());
			}
		}
	}

	private void validateCategory(Category inputCategory) {
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