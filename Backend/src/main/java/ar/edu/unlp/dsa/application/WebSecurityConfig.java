package ar.edu.unlp.dsa.application;

import ar.edu.unlp.dsa.utils.TokenAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by acollard on 5/5/17.
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    @Scope("singleton")
    public TokenAuthenticationService tokenAuthenticationService() {
        return new TokenAuthenticationService();
    }
}