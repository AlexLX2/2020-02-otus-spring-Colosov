package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Optional<Genre> getByID(long id);

    Genre getOrCreateGenreByName(String genre);
}
