package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> getByID(long id);

    List<Genre> readAllGenres();

    Genre getOrCreateGenreByName(String genre);

}
