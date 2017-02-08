package ar.edu.unlp.dsa.controller;

import ar.edu.unlp.dsa.Application;
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
@RequestMapping(Application.API_PREFIX+"/{challengeId}/hints")
public class HintRestController {

    private final HintRepository hintRepository;

    private final ChallengeRepository challengeRepository;

    @Autowired
    HintRestController(HintRepository hintRepository,ChallengeRepository challengeRepository) {
        this.hintRepository = hintRepository;
        this.challengeRepository=challengeRepository;
    }

    @RequestMapping(value = "/{hintId}", method = RequestMethod.GET)
    public Hint getHint(@PathVariable Long hintId,@PathVariable Long challengeId) {
        this.validateChallenge(challengeId);
        return this.getHintRepository().findOne(hintId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Hint> listHints(@PathVariable Long challengeId) {
        this.validateChallenge(challengeId);
        return this.getHintRepository().findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable Long challengeId, @RequestBody Hint input) {
        this.validateChallenge(challengeId);
        Challenge challenge =this.getChallengeRepository().findOne(challengeId);

        Hint result = this.getHintRepository().save(new Hint(input.getDescription(), input.getPointsPercentageCost()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    private void validateChallenge(Long challengeId) {
        if(this.getChallengeRepository().findOne(challengeId)==null){
            throw new ChallengeNotFoundException(challengeId);
        }
    }

    public HintRepository getHintRepository() {
        return hintRepository;
    }

    public ChallengeRepository getChallengeRepository() {
        return challengeRepository;
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ChallengeNotFoundException extends RuntimeException {

    public ChallengeNotFoundException(Long challengeId) {
        super("could not find user '" + challengeId + "'.");
    }
}