package ar.edu.unlp.dsa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by acollard on 8/1/17.
 */
@RestController
@RequestMapping("/version")
public class VersionRestController {

    public String appVersion() {
        return "{ AppVersion : 0.1 }";
    }
}
