package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.AuthorController;
import ru.otus.bookdb.dao.AuthorDao;
import ru.otus.bookdb.domain.Author;

import java.util.List;

@Controller
public class AuthorControllerImpl implements AuthorController {
    private final AuthorDao authorDao;

    public AuthorControllerImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> readAllAuthors() {
        return authorDao.readAllAuthors();
    }

}
