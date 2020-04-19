package ru.otus.bookdb.controller;

import ru.otus.bookdb.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookController {
    void addBook(String title, String author, String genre);

    List<Book> readAllBooks();

    void deleteBookById(long id);

    void updateBook(long id, String title, String genre, String author);

    Optional<Book> getBookByID(long id);
}
