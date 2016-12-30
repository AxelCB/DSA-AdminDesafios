package ar.edu.unlp.dsa.application;

import ar.edu.unlp.dsa.repository.CategoryRepository;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.HintRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by axel on 16/10/16.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application{
//    @Bean
//    CommandLineRunner init(HintRepository hintRepository, CategoryRepository categoryRepository,
//                           ChallengeRepository challengeRepository) {
//        return (evt) -> Arrays.asList(
//                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//                .forEach(
//                        a -> {
//                            Account account = accountRepository.save(new Account(a,
//                                    "password"));
//                            bookmarkRepository.save(new Bookmark(account,
//                                    "http://bookmark.com/1/" + a, "A description"));
//                            bookmarkRepository.save(new Bookmark(account,
//                                    "http://bookmark.com/2/" + a, "A description"));
//                        });
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

