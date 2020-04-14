package ru.otus.bookdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookDbApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BookDbApplication.class);
    }

}
