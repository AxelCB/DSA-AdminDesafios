package ar.edu.unlp.dsa.utils;

import ar.edu.unlp.dsa.dto.AdminDTO;
import ar.edu.unlp.dsa.model.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by acollard on 5/5/17.
 */

public class TokenAuthenticationService {
    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    private Map<String,AdminDTO> usersSession = new HashMap<>();

    public AdminDTO createJWT(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setToken(TOKEN_PREFIX+Jwts.builder()
                .setSubject(admin.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact());
        usersSession.put(adminDTO.getToken(),adminDTO);
        return adminDTO;
    }

    public AdminDTO getUserFromJWT(String token) {
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            AdminDTO adminDTO = usersSession.get(token);
            if( adminDTO != null && user != null ){
                return adminDTO;
            }
            return null;
        }
        return null;
    }

    public void removeUserSession(String token){
        usersSession.remove(token);
    }
}