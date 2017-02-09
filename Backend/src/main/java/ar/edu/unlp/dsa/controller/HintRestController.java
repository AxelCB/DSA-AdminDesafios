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
import ar.edu.unlp.dsa.model.Hint;
import ar.edu.unlp.dsa.repository.HintRepository;

/**
 * Created by axel on 17/10/16.
 */
@RestController
@RequestMapping(Application.API_PREFIX+"/hints")
public class HintRestController {

    private final HintRepository hintRepository;

    @Autowired
    HintRestController(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }

    @RequestMapping(value = "/{hintId}", method = RequestMethod.GET)
    public Hint getHint(@PathVariable Long hintId) {
        return this.getHintRepository().findOne(hintId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Hint> listHints() {
        return this.getHintRepository().findAll();
    }
    
    //TODO throws DataIntegrityViolationException
    @RequestMapping(value = "/{hintId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteHint(@PathVariable Long hintId) {
        this.getHintRepository().delete(hintId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/")
                .buildAndExpand().toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Hint input) {
        Hint result = this.getHintRepository().save(new Hint(input.getDescription(), input.getPointsPercentageCost()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    public HintRepository getHintRepository() {
        return hintRepository;
    }
}