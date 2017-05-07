package ar.edu.unlp.dsa.controller;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.model.Admin;
import ar.edu.unlp.dsa.repository.AdminRepository;
import ar.edu.unlp.dsa.utils.PasswordUtils;
import ar.edu.unlp.dsa.utils.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by axel on 17/10/16.
 */
@RestController
@RequestMapping(Application.API_PREFIX+"/auth")
public class AuthRestController {
    private final AdminRepository adminRepository;
    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    AuthRestController(AdminRepository adminRepository,TokenAuthenticationService tokenAuthenticationService) {
        this.adminRepository = adminRepository;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody Admin input) {
    	Admin admin = this.getAdminRepository().findByUsername(input.getUsername());
    	if (admin != null &&PasswordUtils.validatePassword(admin.getPassword(),input.getPassword())){
    	    return new ResponseEntity<>(this.getTokenAuthenticationService().createJWT(admin),null,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ResponseEntity<?> validate(@RequestHeader(value = "Authorization") String token) {
        if (tokenAuthenticationService.getUserFromJWT(token)!=null) {
            return new ResponseEntity<>(null, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization") String token) {
        if(token != null)
            this.getTokenAuthenticationService().removeUserSession(token);
        return new ResponseEntity<>(null,null,HttpStatus.OK);
    }



    public AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public TokenAuthenticationService getTokenAuthenticationService() {
        return tokenAuthenticationService;
    }
}

