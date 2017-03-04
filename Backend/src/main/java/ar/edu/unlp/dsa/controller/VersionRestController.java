package ar.edu.unlp.dsa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.dsa.Application;

/**
 * Created by acollard on 8/1/17.
 */
@RestController
@RequestMapping("/version")
public class VersionRestController {

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(method = RequestMethod.GET)
    public String appVersion() {
        return "{ AppVersion : 0.1 }";
    }
}
