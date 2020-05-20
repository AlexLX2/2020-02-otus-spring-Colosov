package ru.otus.bookdb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface BookController {

    @GetMapping("/book")
    String readAllBooks(Model model);

    @GetMapping("/book/delete")
    String deleteBookById(long id);

    @GetMapping("/book/add")
    String addBook(Model model);

    @PostMapping("/book/add")
    String addBook(String title, String authorName, String genreName);

    @GetMapping("/book/edit")
    String editBook(long id, Model model);

    @PostMapping("/book/edit")
    String saveBook(long id, String title, String genreName, String authorName, Model model);
}
