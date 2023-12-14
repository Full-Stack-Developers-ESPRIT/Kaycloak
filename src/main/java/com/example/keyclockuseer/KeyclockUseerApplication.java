package com.example.keyclockuseer;

import Entity.User;
import Repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins="*")
public class KeyclockUseerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyclockUseerApplication.class, args);
    }
   /*
    private UserRepo repository;

    @Bean
    ApplicationRunner init() {
        return (args) -> {
            repository.save(new User(1L, "nom" ,"Ch", "ma@esprit.tn", "123"));
            repository.save(new User(2L, "nom","ab", "sa@esprit.tn" ,"123"));
            repository.save(new User (3L,"nom","ba","mo@esprit.tn" ,"123")); // fetch
            repository.findAll().forEach (System.out::println);
        };
    }

    */
}
