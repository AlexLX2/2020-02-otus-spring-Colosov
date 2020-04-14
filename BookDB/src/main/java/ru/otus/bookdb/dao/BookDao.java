package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> getByID(long id);

    List<Book> readAllBooks();

    int count();

    void updateBook(long id, String title, Author author, Genre genre);

    void deleteBookByID(long id);

    Long addBook(String title, Author author, Genre genre);
}
