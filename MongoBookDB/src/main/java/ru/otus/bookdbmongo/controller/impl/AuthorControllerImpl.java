package ru.otus.bookdbmongo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.otus.bookdbmongo.controller.AuthorController;
import ru.otus.bookdbmongo.domain.Author;
import ru.otus.bookdbmongo.repository.AuthorRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
