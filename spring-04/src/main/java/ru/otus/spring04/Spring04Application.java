package ru.otus.spring04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.otus.spring04.service.PersonService;

@SpringBootApplication
public class Spring04Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring04Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            PersonService personService = ctx.getBean(PersonService.class);
            personService.init();
        };
    }
}
