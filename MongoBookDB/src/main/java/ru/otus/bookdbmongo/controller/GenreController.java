package ru.otus.bookdbmongo.controller;

import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Genre;

import java.util.List;

public interface GenreController {
    List<Genre> findAll();

    void addGenre(String genreName, List<Book> books);
}
