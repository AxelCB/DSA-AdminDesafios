package ar.edu.unlp.dsa;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by acollard on 20/3/17.
 */
@Configuration
public class AppConfiguration {
    @Bean
    @Scope("singleton")
    public Mapper mapper(){
        return new DozerBeanMapper();
    }

}
