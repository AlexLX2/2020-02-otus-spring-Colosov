package ru.otus.bookdb.controller;

import ru.otus.bookdb.domain.Genre;

import java.util.List;

public interface GenreController {
    List<Genre> readAllGenres();

    void addGenre(String genreName);
}
