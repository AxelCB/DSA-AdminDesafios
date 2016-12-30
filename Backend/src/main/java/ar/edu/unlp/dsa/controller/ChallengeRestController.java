package ar.edu.unlp.dsa.controller;

import ar.edu.unlp.dsa.model.Challenge;
import ar.edu.unlp.dsa.model.Hint;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.HintRepository;
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
@RequestMapping("/challenges")
public class ChallengeRestController {
    private final ChallengeRepository challengeRepository;

    @Autowired
    ChallengeRestController(ChallengeRepository challengeRepository) {
        this.challengeRepository=challengeRepository;
    }

    @RequestMapping(value = "/{challengeId}", method = RequestMethod.GET)
    public Challenge getChallenge(@PathVariable Long challengeId) {
        return this.getChallengeRepository().findOne(challengeId);
    }

    @RequestMapping(value = "/{challengeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteChallenge(@PathVariable Long challengeId) {
        this.getChallengeRepository().delete(challengeId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/")
                .buildAndExpand().toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Challenge> listChallenges() {
        return this.getChallengeRepository().findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Challenge input) {
        //TODO: Add Category
        //Todo maybe should persist hints separately?
        //Todo file upload
        Challenge result = this.getChallengeRepository().save(new Challenge(input));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    public ChallengeRepository getChallengeRepository() {
        return challengeRepository;
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super("could not find user '" + categoryId + "'.");
    }
}