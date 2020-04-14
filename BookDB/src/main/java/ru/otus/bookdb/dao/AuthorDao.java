package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getByID(long id);

    Author getOrCreateAuthorByName(String author);
}
