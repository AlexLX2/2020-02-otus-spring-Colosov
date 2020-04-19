package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> getByID(long id);

    List<Book> readAllBooks();

    int count();

    void deleteBookByID(long id);

    void addBook(Book book);

    void updateBook(Book book);
}
