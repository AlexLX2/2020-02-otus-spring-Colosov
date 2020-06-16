package ru.otus.bookdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookDbMVCApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BookDbMVCApp.class);
    }

}
