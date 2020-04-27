package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.AuthorController;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.repository.AuthorRepository;

import java.util.List;

@Controller
public class AuthorControllerImpl implements AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorControllerImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> readAllAuthors() {
        return authorRepository.findAll();
    }

}
