package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getByID(long id);

    List<Author> readAllAuthors();

    Author getOrCreateAuthorByName(String author);
}
