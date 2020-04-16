package ru.otus.bookdb.controller;

import ru.otus.bookdb.domain.Book;

import java.util.List;

public interface BookController {
    void addBook(String title, String author, String genre);

    List<Book> readAllBooks();

    void deleteBookById(Long id);

    void updateBook(Long id, String title, String genre, String author);
}
