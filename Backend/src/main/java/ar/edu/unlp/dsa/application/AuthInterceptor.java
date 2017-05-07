package ar.edu.unlp.dsa.application;

import ar.edu.unlp.dsa.utils.TokenAuthenticationService;
import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by acollard on 6/5/17.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TokenAuthenticationService.HEADER_STRING);
        if( request.getRequestURI().equals("/api/auth/authenticate") || request.getRequestURI().equals("/api/auth/logout") || request.getMethod().equalsIgnoreCase("options")){
            return true;
        } else if (token == null || StringUtils.isEmpty(token)){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.sendError(Response.SC_FORBIDDEN);
            return false;
        } else {
            if (tokenAuthenticationService.getUserFromJWT(token) != null) {
                return true;
            } else {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.sendError(Response.SC_FORBIDDEN);
                return false;
            }
        }
    }
}
