package ru.otus.bookdbmongo.controller;

import ru.otus.bookdbmongo.domain.Book;

import java.util.List;

public interface BookController {
    void addBook(String title, String author, String genre);

    List<Book> findAll();

    void deleteBookById(String id);

    void updateBook(String id, String title, String author, String genre);

    Book getById(String id);
}
