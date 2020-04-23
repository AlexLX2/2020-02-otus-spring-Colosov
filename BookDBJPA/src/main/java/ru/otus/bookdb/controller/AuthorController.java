package ru.otus.bookdb.controller;

import ru.otus.bookdb.domain.Author;

import java.util.List;

public interface AuthorController {


    List<Author> readAllAuthors();
}
