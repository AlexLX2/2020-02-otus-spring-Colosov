package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Book;

import java.util.List;

public interface BookDao {
    Book getByID(long id);

    List<Book> readAllBooks();

    int count();

    void deleteBookByID(long id);

    void addBook(Book book);

    void updateBook(Book book);

}
