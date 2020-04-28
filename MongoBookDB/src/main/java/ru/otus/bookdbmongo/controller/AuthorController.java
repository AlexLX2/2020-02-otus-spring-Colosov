package ru.otus.bookdbmongo.controller;

import ru.otus.bookdbmongo.domain.Author;

import java.util.List;

public interface AuthorController {
    List<Author> findAll();
}
